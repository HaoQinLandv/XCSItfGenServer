package com.foxhis.itf.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;

@Component("logframe")
public class LogFrame {

	private JFrame frame;
    private JScrollPane logScrollPane;  
    private JTextArea logTextArea;  
    //private LogReader logReaderRunable ;
    private Map<String, Long> cacheMap =new  ConcurrentHashMap<String, Long>();
    
    private static final File logDir = new File(System.getProperty("user.dir")+"\\logs\\");
    //private static final File logFile = new File(System.getProperty("user.dir")+"\\logs\\foxerror.log");
    private static final String KEY = "lastTimeFileSize";
    private static final String[] extensions = {"log"};
    private boolean isMonitorStar;
    private ReentrantLock lock = new ReentrantLock();
    

	  /**
     * 主窗口大小
     */
    private final static int MAIN_WINDOW_X = 240;
    private final static int MAIN_WINDOW_Y = 100;
    private final static int MAIN_WINDOW_WIDTH = 885;
    private final static int MAIN_WINDOW_HEIGHT = 636;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				LogFrame logFrame = new LogFrame();
				logFrame.initLog();
				logFrame.logFileMonitorStart();
				logFrame.frame.setVisible(true);
			}
		});

	}
	private static class InnerSingleton{
		public static LogFrame logFrame  = new LogFrame();
	}
	
	public static LogFrame getInstance()
	{
		
		return InnerSingleton.logFrame;
	}
	
	private FileAlterationMonitor fileMonitor;
	private LogFrame() {
		// TODO Auto-generated constructor stub
		initialze();
		initLog();
		
		
	}
	
//	public  LogReader getLogReader() {
//		return logReaderRunable;
//	}
	
	public  JFrame getFrame() {
		return frame;
	}
	
	public void dispose()
	{
		if(frame!=null)
			frame.dispose();
//		if(logReaderRunable!=null)
//			logReaderRunable.setFlg(false);
	}
	

	public void cleanCache()
	{
		cacheMap.clear();
	}
	
	
	public void initialze()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		frame = new JFrame();
		frame.setTitle("日志");
		frame.setBounds(MAIN_WINDOW_X, MAIN_WINDOW_Y, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);  
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("logo.png")));
		JPanel mainPanel = new JPanel(true);
		Dimension maxDim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension defaultDim = new Dimension(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
		mainPanel.setPreferredSize(defaultDim);
		mainPanel.setMaximumSize(maxDim);
		mainPanel.setMinimumSize(defaultDim);
		mainPanel.setLayout(new BorderLayout());
		
		logTextArea =new JTextArea();
		/*logTextArea.setPreferredSize(defaultDim);
		logTextArea.setMaximumSize(maxDim);
		logTextArea.setMinimumSize(defaultDim);*/
		
		logScrollPane = new JScrollPane();
		logScrollPane.setViewportView(logTextArea);
		logScrollPane.setPreferredSize(defaultDim);
		logScrollPane.setMaximumSize(maxDim);
		logScrollPane.setMinimumSize(defaultDim);
		
		mainPanel.add(logScrollPane, BorderLayout.CENTER);
		frame.add(mainPanel);
		frame.pack();
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				//logReaderRunable.setFlg(false);
				logFileMonitorInterrupt();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public boolean isMonitorStar() {
		return isMonitorStar;
	}
	
	public void logFileMonitorInterrupt()
	{
		try {
			fileMonitor.stop();
			isMonitorStar = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initLog()
	{
		long lastTimeFileSize = 0; // 初始文件大小
		cacheMap.put(KEY, lastTimeFileSize);
		FileFilterImpl filterImpl = new FileFilterImpl(extensions);
		FileAlterationObserver logFileObserver=new FileAlterationObserver(logDir, filterImpl );
		logFileObserver.addListener(new FileListener());
		fileMonitor = new FileAlterationMonitor(3000, logFileObserver);
		//默认启动
		logFileMonitorStart();
		
	}
	
	public void logFileMonitorStart()
	{
		try {
			fileMonitor.start();
			isMonitorStar = true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 文件监听类
	 * @author Administrator
	 *
	 */
	
	class FileListener extends FileAlterationListenerAdaptor{
		
		/**
	     * 文件创建时执行的动作
	     */
	    @Override
	    public void onFileCreate(File file) {
	        // To do something
	        System.out.println("Create file: "+file.getName());
	    }
	    
	    /**
	     * 文件删除（转移）时执行的动作
	     */
	    @Override
	    public void onFileDelete(File file) {
	        // To do something
	        System.out.println("Delete file: "+file.getName());
	    }
	    
	    /**
	     * 文件内容改变时执行的动作
	     */
	    @Override
	    public void onFileChange(File file) {
	    	// To do something 
	    	//加上安全锁，避免文件更新太快，显示出现重复
	    	ReentrantLock nLock = lock;
	    	nLock.lock();
	    	RandomAccessFile randomFile=null;
	    	long lastTimeFileSize = cacheMap.get(KEY).longValue();
	    	try {
	    		randomFile = new RandomAccessFile(file, "r");
	    		long nowTimeFileSize = randomFile.length();
	    		if(nowTimeFileSize!=lastTimeFileSize)
	    		{
	    			randomFile.seek(lastTimeFileSize);
	    			cacheMap.put(KEY, nowTimeFileSize);
		    		String tmp = null;
		    		while ((tmp = randomFile.readLine()) != null) {
		    			logTextArea.append(new String(tmp.getBytes("iso-8859-1"), "utf-8"));
		    			logTextArea.append("\n");
		    		}
	    		}
		
	    	} catch (Exception e) {
	    		// TODO Auto-generated catch block
	    	}
	    	finally {
	    		try {
	    			randomFile.close();
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    	}
	    	//System.out.println("Change file: "+file.getName());
	    	nLock.unlock();
	    	
	    }

	    /**
	     * 开始执行监听时执行的动作
	     */
	    @Override
	    public void onStart(FileAlterationObserver observer) {
	        // To do something
	    	super.onStart(observer);
	    }
	    
	    /**
	     * 停止监听时执行的动作
	     */
	    @Override
	    public void onStop(FileAlterationObserver observer) {
	        // To do something
	    	super.onStop(observer);
	    }
		
	}

    
	class FileFilterImpl  implements FileFilter{

		private String[] extensions;
		
		public FileFilterImpl(String...extensions) {
			// TODO Auto-generated constructor stub
			this.extensions = extensions;
		}
		@Override
		public boolean accept(File pathname) {
			// TODO Auto-generated method stub
			return FilenameUtils.isExtension(pathname.getName(), extensions);
		}
		
		public String[] getExtensions()
		{
			return extensions;
		}
	}

}
