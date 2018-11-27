package com.foxhis.itf.main;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.foxhis.itf.handler.ISMSHandler;
import com.foxhis.itf.service.IGenCommon;
import com.foxhis.itf.utils.Utils;


/**
 * 程序启动入口类
 * @author tq
 *
 */
public class Main {

	//日志类
	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	//定义定时任务
	private static ScheduledExecutorService  scheduledpools;
	//private static SqlSessionFactory sqlSessionFactory ;
	//private static LogDialog logDemoFrame;
	private static LogFrame logFrame;
	private static final String SEVNAME = "门锁";
	private static String handler;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		if(checkRunning()){
			try {
				EventQueue.invokeAndWait(new Runnable() {

					@Override
					public void run() {
						JOptionPane.showMessageDialog(null, "程序已经运行");
						System.exit(-1);
					}
				});
			} catch (Exception e) {
				System.exit(-1);
			}
		}

		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");
		System.out.println(System.getProperty("user.dir") + "\\log4j.properties");

		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(new File(System.getProperty("user.dir"),"system.properties")));
		} catch (Exception e) {
			LOGGER.error("加载配置文件失败",e);
			System.exit(-1);
		}
		try {
			
			scheduledpools = Executors.newScheduledThreadPool(1);
            //轮询时长
			String runtime = properties.getProperty("time");
			//接口类型句柄
			String itfhandler = properties.getProperty("handler");
			handler = itfhandler==null?null:itfhandler.toLowerCase();
		
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			IGenCommon genCommon=(IGenCommon)context.getBean(handler);
			if(genCommon!=null)
			{
				genCommon.initialize();
				scheduledpools.scheduleAtFixedRate(genCommon, 0, parseTime(runtime),TimeUnit.SECONDS);
				LOGGER.info(MessageFormat.format("打开循环调度器成功,循环时间为:{0}秒", runtime));
			}
			else {
				LOGGER.info("获取Gen公共实例为NULL,请检查Handler配置是否正确");
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("连接数据库失败或者加载实例失败",e);
		}
	    //初始化日志窗口
		logFrame = new LogFrame();

        //加上系统托盘
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				createSystemTray();
			}
		});


	}

	private static void createSystemTray(){
		PopupMenu popupMenu = new PopupMenu();
		//关于菜单
		MenuItem aboutItem = new MenuItem();
		aboutItem.setLabel("关于");
		aboutItem.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				//to do 关闭数据库
				JOptionPane.showMessageDialog(null, MessageFormat.format("这是{0}独立平台服务端V1.0", SEVNAME),"关于",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		popupMenu.add(aboutItem);

		//日志菜单
		MenuItem logItem = new MenuItem();
		logItem.setLabel("日志");
		logItem.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				
				if(!logFrame.logReader.isFlg())
				{
					logFrame.logReader.setFlg(true);
					logFrame.startLogThread();
				}
				logFrame.frame.setVisible(true);
				
			}
		});
		popupMenu.add(logItem);
		if(Utils.isNotBlank(handler) && "sms".equalsIgnoreCase(handler))
		{
			//查询余额菜单
			MenuItem balanceItem = new MenuItem();
			balanceItem.setLabel("查询短信余额");
			balanceItem.addActionListener(new ActionListener() {		
				public void actionPerformed(ActionEvent e) {
					ISMSHandler ismsHandler=null;
					try {
						ismsHandler = Utils.getItfInstance(ISMSHandler.class);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(ismsHandler!=null)
					{
						Map<String, Object> re=ismsHandler.getBalance();
						if((Boolean)re.get("result"))
						{
							String balance = (String)re.get("balance");
							JOptionPane.showMessageDialog(null, "当前短信余额为："+balance);
							LOGGER.info("当前短信余额为："+balance);
						}
						else {
							String msg = (String)re.get("msg");
							JOptionPane.showMessageDialog(null, "查询余额错误："+msg);
							LOGGER.info("查询余额错误："+msg);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "获取短信对象异常，请查看日志文件");
					}
				}
			});
			popupMenu.add(balanceItem);
		}
		
		//退出菜单
		MenuItem exitItem = new MenuItem();
		exitItem.setLabel("退出");
		exitItem.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				//to do 关闭数据库
				if(scheduledpools!=null && !scheduledpools.isShutdown())
					scheduledpools.shutdown();
				if(logFrame!=null) 
					 logFrame.dispose();
				LOGGER.info("服务已正常退出..");
				System.exit(0);
			}
		});
		popupMenu.add(exitItem);

		//加载logo
		Image image = null;
		try {
			image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("logo.png"))).getImage();
		} catch (Exception e){
		}
		TrayIcon trayIcon = new TrayIcon(image, MessageFormat.format("{0}接口服务器", SEVNAME));
		trayIcon.setPopupMenu(popupMenu);
		try {
			SystemTray.getSystemTray().add(trayIcon);
		} catch (AWTException e) {
			LOGGER.error("系统不支持系统托盘");
			System.exit(-1);
		}

	}

	private static boolean checkRunning(){
		try {
			@SuppressWarnings("resource")
			RandomAccessFile raf = new RandomAccessFile(System.getProperty("user.dir") + "\\mutex.lock", "rw");
			return raf.getChannel().tryLock() == null;
		} catch (Exception e) {
			return false;
		}

	}

	private static long parseTime(String time){
		long port = 6666;
		try {
			port = Long.parseLong(time);
		} catch (NumberFormatException e) {
			LOGGER.warn("时间轮询转化long失败", e);
		}
		return port;
	}

}
