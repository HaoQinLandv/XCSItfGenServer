package com.foxhis.itf.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.foxhis.itf.dao.IMessage_SendMapper;
import com.foxhis.itf.entity.Message_Send;
import com.foxhis.itf.entity.SM_Sendplus;
import com.foxhis.itf.exception.NoHandlerDefException;
import com.foxhis.itf.handler.ISMSHandler;
import com.foxhis.itf.service.IGenCommon;
import com.foxhis.itf.utils.Utils;




/**
 * 发送短信类
 *
 */
@Service("sms")
public class SMSGenernal implements IGenCommon
{

	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	@Resource
	private IMessage_SendMapper ms ;
	private ISMSHandler smsimpl;
	//短信长度分割
	private int length;
	private Object obj = new Object();
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	
	@Override
	public void initialize() throws NoHandlerDefException {
		// TODO Auto-generated method stub
		this.smsimpl=Utils.getItfInstance(ISMSHandler.class);
		//先更新过期的数据
		ms.updateOldMsg();
		LOGGER.info("更新过期数据...");
	}
	
	public SMSGenernal() throws NoHandlerDefException{
	
		
	}

	public boolean isRegularlySend(Message_Send message_Send)
	{
		Calendar calendar=Calendar.getInstance();
		//calendar.add(Calendar.SECOND, 5);//时间往后延续5秒
		Date now = calendar.getTime();
		Date sendtime = message_Send.getSendtime();
		if(sendtime==null)
			return false;
		long snr = sendtime.getTime()-now.getTime();
		if(message_Send.getSendstate()==2 && (snr<0))
		{
			return true;
		}
		else{
			return false;
		}
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (obj) {
			
			try {
				List<Message_Send> msds = ms.getMs_SendBySta();
			
				if(msds!=null && !msds.isEmpty() && smsimpl!=null)
				{
					for (Message_Send message_Send : msds) {
						//获取每条记录做推送,将手机号码内容封装成map类型
						if(isRegularlySend(message_Send)) 
							continue;
						Map<String,Object> reMap = new HashMap<String,Object>();
						reMap.put("mobile", message_Send.getShortmsgno());//号码
						
						StringBuffer context = new StringBuffer();
						//byte[] body = message_Send.getShortmsgbody();
						String body = message_Send.getShortmsgbody();
						//String bodys = new String(body,"GBK");
					    context.append(Utils.nullToStr(body));
						SM_Sendplus sendplus = message_Send.getSm_sendplus();
						if(sendplus!=null)
						{
							//String plus1=new String(sendplus.getShortmsgbody1(),"GBK");
							//String plus2=new String(sendplus.getShortmsgbody2(),"GBK");
							//String plus3=new String(sendplus.getShortmsgbody3(),"GBK");
							//String plus4=new String(sendplus.getShortmsgbody4(),"GBK");
							//String plus5=new String(sendplus.getShortmsgbody5(),"GBK");

							String plus1=sendplus.getShortmsgbody1();
							String plus2=sendplus.getShortmsgbody2();
							String plus3=sendplus.getShortmsgbody3();
							String plus4=sendplus.getShortmsgbody4();
							String plus5=sendplus.getShortmsgbody5();
							
							context.append(Utils.nullToStr(plus1));
							context.append(Utils.nullToStr(plus2));
							context.append(Utils.nullToStr(plus3));
							context.append(Utils.nullToStr(plus4));
							context.append(Utils.nullToStr(plus5));
						}
						String contexts = context.toString();
						//contexts = new String(contexts.getBytes("ISO-8859-1"),"GBK");
						int len = contexts.length();
						int tem =0;

						Map<String,Object> result =new HashMap<String, Object>();
						do
						{
							String str1="";
							if((tem+length)>len)
							{
								str1 = contexts.substring(tem);
							}
							else
							{
								str1 = contexts.substring(tem, tem+length);

							}
							LOGGER.info("发送内容:"+str1);
							tem += length;
							reMap.put("content", str1);//内容
							result = smsimpl.send(reMap);
							LOGGER.info("返回结果:"+result);
							if(!(Boolean)result.get("result"))
							{
								break;
							}
						}
						while(len-tem>0);
						//发送成功，修改状态
						LOGGER.info("最后返回结果:"+result);
						if((Boolean)result.get("result"))
						{
							message_Send.setSendstate(5);
							message_Send.setResultdescrib((String)result.get("msg"));
							ms.updateMs_Send(message_Send);
							
						}
						//发送失败，修改日志
						else{
							message_Send.setSendstate(-1);
							message_Send.setResultdescrib((String)result.get("msg"));
							ms.updateMs_Send(message_Send);
							
							//LOGGER.info("发送短信失败:"+result.get("msg"));
						}

					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error("轮询异常：",e);
			}
			finally{
			
			}
		}
	}

	
}
