package com.foxhis.itf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxhis.itf.exception.NoHandlerDefException;
import com.foxhis.itf.handler.IPoliceHandler;
import com.foxhis.itf.service.IDoTask;
import com.foxhis.itf.service.IGenCommon;
import com.foxhis.itf.utils.Utils;



/**
 * 公安专用类
 * @author tq
 *
 */
@Service("police")
public class PoliceGenernal implements IGenCommon{


	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	private IPoliceHandler policeimpl;
	
	@Autowired
	private IDoTask<IPoliceHandler> doTask;
	//	private String path;
	
	private Object obj = new Object();

	@Override
	public void initialize() throws NoHandlerDefException{
		// TODO Auto-generated method stub
		this.policeimpl = Utils.getItfInstance(IPoliceHandler.class);
	
	}
	
	public PoliceGenernal() throws NoHandlerDefException {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (obj) {
	    	try{
				doTask.doTask(policeimpl);
			}catch (Throwable e) {
				// TODO: handle exception
				LOGGER.error("轮询异常："+e);
			}
			
		}
	}

 /*
	public void doTask() throws Exception
	{	
		//获取公安xml文件
		List<String> xmlfiles = policeimpl.getXMLFile();
		
		for (String xmlfile : xmlfiles) {
			LOGGER.info(MessageFormat.format("获取文件路径：{0}", xmlfile));	
			List<Map<String, Object>> retlist = policeimpl.parseXMLtoMap(xmlfile);
			boolean isexception=true;
			if(retlist!=null && !retlist.isEmpty())
			{
				for (Map<String, Object> retmap : retlist) {
					try {
						int id = ms.getIdsBySysExtraid();//获取流水号
						System.out.println(id);
						id+=1;
						String no = id+"";
						LOGGER.info(MessageFormat.format("获取该文件内容：{0}", retmap));
						
						String id = (String)retmap.get("id");
						String roomno = (String)retmap.get("roomno");
						String guesttype = (String)retmap.get("guesttype");
						String name = (String)retmap.get("name");
						
						String name2 = (String)retmap.get("name2");
						String sex = (String)retmap.get("sex");
						String birthday = (String)retmap.get("birthday");
						String race = (String)retmap.get("race");
						
						String country = (String)retmap.get("country");
						String nation = (String)retmap.get("nation");
						String address = (String)retmap.get("address");
						String prv = (String)retmap.get("prv");
						
						String idtype = (String)retmap.get("idtype");
						String idcode = (String)retmap.get("idcode");
						String scantime = (String)retmap.get("scantime");
						//String idpic = (String)retmap.get("idpic");
						
						Date date1 = (Date)retmap.get("date1");
						Date date2 = (Date)retmap.get("date2");
						Date date3 = (Date)retmap.get("date3");
						String done = (String)retmap.get("done");
						String no = (String)retmap.get("no");
						IdScan_ga idScanga = new IdScan_ga();

						idScanga.setId(id);
						idScanga.setRoomno(roomno);
						idScanga.setGuesttype(guesttype);
						idScanga.setName(name);
						idScanga.setName2(name2);
						idScanga.setSex(sex);
						idScanga.setBirthday(birthday);
						idScanga.setRace(race);
						idScanga.setCountry(country);
						idScanga.setNation(nation);
						idScanga.setAddress(address);
						idScanga.setPrv(prv);
						idScanga.setIdtype(idtype);
						idScanga.setIdcode(idcode);
						idScanga.setScantime(scantime);
						idScanga.setDate1(date1);
						idScanga.setDate2(date2);
						idScanga.setDate3(date3);
						idScanga.setDone(done);
						idScanga.setNo(no);
						//插入到idscan,返回插入条数
						int i = 0;
						if(Utils.isNotBlank(id) && Utils.isNotBlank(idcode)){
							i=ms.insertIdScan_ga(idScanga);
						}

						if(i>=1)
						{
							//如果插入成功,调用存储过程更新字段表
							LOGGER.info(MessageFormat.format("NO:{0}插入idscan_ga表成功", id));
							//Stored procedure 'p_hxm_gong_an_process' may be run only in unchained transaction mode
							//设置p_hxm_gong_an_process过程为anymode
							ms.updateProMode();
							ms.afterUpdate(id);
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						LOGGER.info("读取xml文件异常或插入idscan表异常",e);
						//异常移动文件
						isexception =false;
					}
				}

			}
			if(isexception)
				policeimpl.deleteXMLFile(true,xmlfile);//常规移除文件
			else {
				policeimpl.deleteXMLFile(false,xmlfile);//异常移动文件
			}
		}
		
	}*/

}
