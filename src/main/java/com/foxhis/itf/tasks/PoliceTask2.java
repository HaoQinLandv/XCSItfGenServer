package com.foxhis.itf.tasks;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.foxhis.itf.dao.IPersonMapper;
import com.foxhis.itf.entity.Person;
import com.foxhis.itf.handler.IPoliceHandler;
import com.foxhis.itf.service.IDoTask;
import com.foxhis.itf.utils.Utils;

/**
 * 湖南的公安
 * @author Administrator
 *
 */
public class PoliceTask2 implements IDoTask<IPoliceHandler>{

	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
    private static final String STA_N = "N";
    private static final String STA_F = "F";
    private static final String STA_T = "T";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
	@Resource
	private IPersonMapper pmap ;
	
	public PoliceTask2() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void doTask(IPoliceHandler policeimpl) {
		// TODO Auto-generated method stub
		List<Person> plists = pmap.getPersonBySta(STA_N);
		if(plists!=null  && !plists.isEmpty())
		{
			LOGGER.info(MessageFormat.format("获取状态为N的客人记录条数{0}", plists.size()));
			for(Person plist :plists)
			{
				//同步map
				Map<String,Object> input= new ConcurrentHashMap<String,Object>();
				input.put("fh", plist.getFh());
				input.put("rzsj", plist.getRzsj());
				input.put("zwxm", plist.getZwxm());
				input.put("zjhm", plist.getZjhm());
				input.put("zjlx", plist.getZjlx());
				input.put("xb", plist.getXb());
				input.put("csrq", plist.getCsrq());
				input.put("mz", plist.getMz());
				input.put("jg", plist.getJg());
				input.put("address", plist.getAddress());
				input.put("idcardzp", plist.getIdcardzp());
				input.put("camerazp", plist.getCamerazp());
				LOGGER.info(MessageFormat.format("当前推送记录{0}", input.toString()));
				
				Map<String, Object> reMap=policeimpl.exeUpLoadPersonInfo(input);
				LOGGER.info(MessageFormat.format("返回结果{0}", reMap.toString()));
				if((Boolean) reMap.get("result"))
				{
					plist.setSta(STA_T);
					plist.setChangetime(sdf.format(new Date()));
					int i = pmap.updatePersonChanged(plist);
					if(i>0)
					{
						LOGGER.info(MessageFormat.format("{0}更新记录{1}成功", input.get("fh"),STA_T));
					}
				}
				else
				{
					plist.setSta(STA_F);
					plist.setChangetime(sdf.format(new Date()));
					int i = pmap.updatePersonChanged(plist);
					if(i>0)
					{
						LOGGER.info(MessageFormat.format("{0}更新记录{1}成功", input.get("fh"),STA_F));
					}
				}
			}
		}
	
	}

}
