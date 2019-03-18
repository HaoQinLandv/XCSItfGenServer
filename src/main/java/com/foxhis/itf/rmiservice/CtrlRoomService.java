package com.foxhis.itf.rmiservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foxhis.itf.dao.ICtrlRoomMapper;
import com.foxhis.itf.entity.Master_temp;
import com.foxhis.itf.handler.ICtrlRoomHandler;
import com.foxhis.itf.utils.Utils;

@Service("ctrlroomserviceimpl")
public class CtrlRoomService implements ICtrlRoomHandler{

	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	
	@Resource
	private ICtrlRoomMapper ctrlRoomMapper;
	
	@Override
	public Map<String, Object> ctrlroomCkin(Map<String, Object> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> ctrlroomContinued(Map<String, Object> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> ctrlroomCkot(Map<String, Object> input) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getRmSta(String cmd) {
		// TODO Auto-generated method stub
		 LOGGER.info("rmi request "+cmd);
		 List<Master_temp> master_temps = ctrlRoomMapper.getRmSta();
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("cmd", "fetchPmsRoomStatus");
		 jsonObject.put("resultCode", 0);
		 jsonObject.put("msg", "操作成功"); 
		 jsonObject.put("pmsRoomStatus", getMasters(master_temps));
		
		 return jsonObject.toJSONString();
	}
	
	private JSONArray getMasters( List<Master_temp> master_temps )
	{
		JSONArray array = new JSONArray();
		int len = master_temps.size();
		for (int i = 0;i<len;i++) {
			try {
				 Master_temp master_temp = master_temps.get(i);
				 LOGGER.info(master_temp.toString());
				 JSONObject masterjson = new JSONObject();
				 String roomno = master_temp.getRoomno().trim();
				 
				 masterjson.put("roomSuiteType",Utils.nullToStr(master_temp.getRef1()));//房型
				 masterjson.put("room",roomno);//房号
				 masterjson.put("checkinTime",getSDFTime(master_temp.getCheckInTime()));//入住时间
				 masterjson.put("expireTime",getSDFTime(master_temp.getCheckOutTime()));//离店时间
				 JSONArray guestarray = new JSONArray();
				 JSONObject guestjson = new JSONObject();
				 guestjson.put("mobile", Utils.nullToStr(master_temp.getMobile()).trim());
				 guestjson.put("lastName", Utils.nullToStr(master_temp.getLastName()).trim());
				 guestjson.put("firstName", Utils.nullToStr(master_temp.getFirstName()).trim());
				 guestjson.put("sex", Utils.nullToStr(master_temp.getGenderName()).trim());
				 guestjson.put("idCard", Utils.nullToStr(master_temp.getGuestId()).trim());
				 guestarray.add(guestjson);
				 for(int j=i+1;j< len ;j++)
				 {
					 Master_temp master_temp2 = master_temps.get(j);
					 if(roomno.equals((Utils.nullToStr(master_temp2.getRoomno()).trim())))
					 {
						 LOGGER.info("#"+roomno+"#"+master_temp2.toString());
						 JSONObject guestjson2 = new JSONObject();
						 guestjson2.put("mobile", Utils.nullToStr(master_temp2.getMobile()).trim());
						 guestjson2.put("lastName", Utils.nullToStr(master_temp2.getLastName()).trim());
						 guestjson2.put("firstName", Utils.nullToStr(master_temp2.getFirstName()).trim());
						 guestjson2.put("sex", Utils.nullToStr(master_temp2.getGenderName()).trim());
						 guestjson2.put("idCard", Utils.nullToStr(master_temp2.getGuestId()).trim());
						 guestarray.add(guestjson2);
						 master_temps.remove(j);
						 len--;
					 }
				 }
				 masterjson.put("guestList", guestarray);
				 array.add(masterjson);
				
			} catch (Exception e) {
				// TODO: handle exception
				LOGGER.error("master_temp循环获取出错",e);
			}
			
		 }
		return array;
	}
	
	private String getSDFTime(Date date)
	{
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	

}
