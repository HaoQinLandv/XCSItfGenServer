package com.foxhis.itf.utils;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.apache.commons.codec.binary.Base64;

import com.foxhis.itf.handler.IPoliceHandler;
import com.foxhis.itf.handler.ISMSHandler;
import com.foxhis.itf.handler.IVODHandler;

/**
 * 公共方法类
 * @author tq
 *
 */
public class Utils {

	public static final String SERVER_LOGGER_NAME = "server.logger";
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !Utils.isBlank(str);
	}

	/**
	 * 如果str=null转“”
	 * @param str
	 * @return
	 */
	public static String nullToStr(String str)
	{	
		return str==null?"":str;
	}

	public static byte[]  parseStrToByte(String img) {
		// TODO Auto-generated method stub
		return Base64.decodeBase64(img);
	}

	public static IPoliceHandler getPoliceInstance() throws Exception
	{
		IPoliceHandler ipoliceHandler = null;

		ServiceLoader<IPoliceHandler> serviceLoader = ServiceLoader.load(IPoliceHandler.class);
		Iterator<IPoliceHandler> iter = serviceLoader.iterator();
		if(iter.hasNext()){
			ipoliceHandler = iter.next();
		}
		if(ipoliceHandler ==null)
		{
			throw new Exception("获取ipoliceHandler为空");
		}
		return ipoliceHandler;
	}

	public static IVODHandler getVODInstance() throws Exception
	{
		IVODHandler ivodHandler = null;

		ServiceLoader<IVODHandler> serviceLoader = ServiceLoader.load(IVODHandler.class);
		Iterator<IVODHandler> iter = serviceLoader.iterator();
		if(iter.hasNext()){
			ivodHandler = iter.next();
		}
		if(ivodHandler ==null)
		{
			throw new Exception("获取ivodHandler为空");
		}
		return ivodHandler;
	}

	public static ISMSHandler getSMSInstance() throws Exception
	{
		ISMSHandler ismsHandler = null;

		ServiceLoader<ISMSHandler> serviceLoader = ServiceLoader.load(ISMSHandler.class);
		Iterator<ISMSHandler> iter = serviceLoader.iterator();
		if(iter.hasNext()){
			ismsHandler = iter.next();
		}
		if(ismsHandler ==null)
		{
			throw new Exception("获取ismsHandler为空");
		}
		return ismsHandler;
	}

	/**
	 *   采用spi加载第三方写的方法类并获取handler实例
	 * @param <T>
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> T getItfInstance(Class<T> cls) throws NoHandlerDefException{
		T ihandler = null;
		ServiceLoader<T> serviceLoader = ServiceLoader.load(cls);
		Iterator<T> iter = serviceLoader.iterator();
		if(iter.hasNext()){
			ihandler = iter.next();
		}
		if(ihandler ==null)
		{
			throw new NoHandlerDefException("获取handler对象为空");
		}
		return ihandler;
	}

	public static String getServerNameByHandler(String handler)
	{
		String serverName="";
		switch (ServerNames.getServerName(handler.toUpperCase())) {
		case DOORCARD:
			serverName="门锁";
			break;
		case VOD:
			serverName="电视点播";
			break;
		case IDCARD:
			serverName="身份证";
			break;
		case PBX:
			serverName="电话";
			break;
		case CTRLROOM:
			serverName="客控";
			break;
		case POLICE:
			serverName="公安";
			break;
		case SMS:
			serverName="短信";
			break;
		case NOVALUES:
			serverName="未知类型";
			break;
		default:
			break;
		}
		return serverName;
	}
	
	public static enum ServerNames{
		
		DOORCARD,VOD,IDCARD,PBX,CTRLROOM,POLICE,SMS,NOVALUES;
		
		public static ServerNames getServerName(String handler)
		{
			try {
				return valueOf(handler);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return ServerNames.NOVALUES;
			}
		}
	}
	
	public static class NoHandlerDefException extends Exception{
		
		public NoHandlerDefException(String message) {
			super(message);
		}
	}
	
}


