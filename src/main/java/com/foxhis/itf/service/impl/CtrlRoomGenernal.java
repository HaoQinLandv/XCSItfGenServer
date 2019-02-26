package com.foxhis.itf.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.foxhis.itf.dao.ICtrlRoomMapper;
import com.foxhis.itf.entity.Internet_pms;
import com.foxhis.itf.entity.Master_temp;
import com.foxhis.itf.exception.NoHandlerDefException;
import com.foxhis.itf.handler.ICtrlRoomHandler;
import com.foxhis.itf.service.IGenCommon;
import com.foxhis.itf.utils.Utils;


@Service("ctrlroom")
public class CtrlRoomGenernal implements IGenCommon{

	
	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	private ICtrlRoomHandler ctrlRoomImpl;
	@Resource
	private ICtrlRoomMapper ctrlRoomMapper;
	private final String ckin = "1";
	private final String ckot = "0";
	
	@Override
	public void initialize() throws NoHandlerDefException {
		// TODO Auto-generated method stub
		this.ctrlRoomImpl =Utils.getItfInstance(ICtrlRoomHandler.class);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			doTask();
		}
		catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("轮询异常：",e);
		}
		finally {
		
		}
	}

	public synchronized void doTask( )throws Exception
	{
		List<Internet_pms> internet_pmses = ctrlRoomMapper.getInternetPmsByTag("F");
		LOGGER.info("获取internet_pms待推送数量："+internet_pmses.size());
		for (Internet_pms internet_pms : internet_pmses) {
			LOGGER.info(internet_pms.toString());
			String accnt = internet_pms.getAccnt();
			String tag = internet_pms.getTag();
			Master_temp master_temp = ctrlRoomMapper.getMasterTempByAccnt(accnt);
			LOGGER.info(master_temp.toString());
			if(master_temp!=null && Utils.isNotBlank(master_temp.getRoomno()))
			{
				Map<String, Object> input = new HashMap<String, Object>();
				input.put("roomno", master_temp.getRoomno());
				input.put("accnt", master_temp.getAccnt());
				input.put("hno", master_temp.getHno());
				input.put("checkintime", master_temp.getCheckInTime());
				input.put("checkouttime", master_temp.getCheckOutTime());
				input.put("groupid", master_temp.getGroupId());
				input.put("groupname", master_temp.getGroupName());
				input.put("guestname", master_temp.getGuestName());
				input.put("guesttitle", master_temp.getGuestTitle());
				input.put("idcardtypecode", master_temp.getIdCardTypeCode());
				input.put("idcardtypename", master_temp.getIdCardTypeName());
				input.put("guestid", master_temp.getGuestId());
				input.put("gendercode", master_temp.getGenderCode());
				input.put("gendername", master_temp.getGenderName());
				input.put("birth", master_temp.getBirth());
				input.put("nationalitycode", master_temp.getNationalityCode());
				input.put("nationalityname", master_temp.getNationalityName());
				Map<String, Object> reMap = new HashMap<String, Object>();
				if(ckin.equals(tag))
				{
					 reMap= ctrlRoomImpl.ctrlroomCkin(input);
					 LOGGER.info("执行ckin结果："+reMap);
				}
				if(ckot.equals(tag))
				{
					 reMap = ctrlRoomImpl.ctrlroomCkot(input);
					 LOGGER.info("执行ckot结果："+reMap);
				}
				
				if(reMap!=null && !reMap.isEmpty())
				{
					if((Boolean)reMap.get("result"))
					{
						//成功
						internet_pms.setChanged("T");
						
					}
					else {
						//失败
						internet_pms.setChanged("P");
					}
					internet_pms.setSettime(new Date());
					int k=ctrlRoomMapper.updateInternetPmsByTag(internet_pms);
					String re = k>0? "成功" : "失败";
					LOGGER.info("更新Internet_pms结果："+re);
				}
			
			}
		}
	}
}
