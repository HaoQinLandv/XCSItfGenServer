package com.foxhis.itf.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import com.foxhis.itf.entity.Message_Send;


@MapperScan
public interface IMessage_SendMapper  {
	
	/**
	 * 查询所有符合发送请求的数据
	 * @return
	 */
	public List<Message_Send> getMs_SendBySta();
	
	
	/**
	 * 更新发送后成功与否的状态
	 * @return
	 */
	public void updateMs_Send(Message_Send ms);
	
	
	/**
	 * 更新过时的短信数据
	 * @return
	 */
	public void updateOldMsg();

}
