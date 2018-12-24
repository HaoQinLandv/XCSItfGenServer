package com.foxhis.itf.service.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.foxhis.itf.dao.IVod_GrdMapper;
import com.foxhis.itf.entity.Vod_Grd;
import com.foxhis.itf.exception.NoHandlerDefException;
import com.foxhis.itf.handler.IVODHandler;
import com.foxhis.itf.service.IGenCommon;
import com.foxhis.itf.utils.Utils;



/**
 * VOD服务的通用方法
 *
 */
@Service("vod")
public class VODGenernal  implements IGenCommon{


	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	@Resource
	private  IVod_GrdMapper vodmapper;
	private IVODHandler vodimpl;

	private Object obj = new Object();


	@Override
	public void initialize() throws NoHandlerDefException {
		// TODO Auto-generated method stub
		this.vodimpl =Utils.getItfInstance(IVODHandler.class);
	}
	
	public VODGenernal() throws NoHandlerDefException{
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			try {
			
				doTask();
			}
			catch (Throwable e) {
				// TODO: handle exception
				LOGGER.error("轮询异常："+e);
			}
			finally {
			
			}
		
	}

    public void doTask( )throws Throwable
    {
		List<Vod_Grd> vodlist = vodmapper.getVodByChanged();
		if(vodlist!=null && !vodlist.isEmpty())
		{
			for (Vod_Grd vodgrd : vodlist) {
                Map<String, Object> input = new HashMap<String, Object>();
                String grd = vodgrd.getGrade();
                String roomno  =vodgrd.getRoomno();
                input.put("roomno", roomno);
                input.put("changed", vodgrd.getChanged());
                input.put("ograde", vodgrd.getOgrade());
                input.put("grade", vodgrd.getGrade());
                input.put("obox_addr", vodgrd.getObox_addr());
                input.put("box_addr", vodgrd.getBox_addr());
                input.put("gst_grd", vodgrd.getGst_grd());
                input.put("gst_name", vodgrd.getGst_name());
                input.put("empno", vodgrd.getEmpno());
                input.put("shift", vodgrd.getShift());
                input.put("date", vodgrd.getDate());
                input.put("logmark", vodgrd.getLogmark());
                LOGGER.info(MessageFormat.format("获取的vodgrd记录：{0}", input));
				if(Utils.isNotBlank(grd))
				{
					 Map<String, Object> re = new HashMap<String, Object>();
                    if("1".equals(grd)) //入住
                    {
                    	re=vodimpl.vodCkin(input);
                    	
                    }
                    else {            //退房
                    	re =vodimpl.vodCkot(input);
                    	
					}
                    if((Boolean)re.get("result"))
                	{
                		vodgrd.setChanged("T");                    		
                	}
                	else {
                		vodgrd.setChanged("N");
                		LOGGER.info(MessageFormat.format("推送失败原因：{0}", re.get("msg")));
					}
                    int i=vodmapper.updateVodChanged(vodgrd);
                    LOGGER.info(MessageFormat.format("更新状态是否成功:{0}",i>=0?true:false));
            		
				}
				
			}
		}
    }
	
}
