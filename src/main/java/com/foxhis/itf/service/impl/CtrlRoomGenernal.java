package com.foxhis.itf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.foxhis.itf.exception.NoHandlerDefException;
import com.foxhis.itf.handler.ICtrlRoomHandler;
import com.foxhis.itf.service.IGenCommon;
import com.foxhis.itf.utils.Utils;


@Service("ctrlroom")
public class CtrlRoomGenernal implements IGenCommon{

	
	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	private ICtrlRoomHandler ctrlroomimpl;
	
	@Override
	public void initialize() throws NoHandlerDefException {
		// TODO Auto-generated method stub
		this.ctrlroomimpl =Utils.getItfInstance(ICtrlRoomHandler.class);
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

	}
}
