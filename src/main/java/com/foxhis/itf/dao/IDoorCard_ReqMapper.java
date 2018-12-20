package com.foxhis.itf.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;


import com.foxhis.itf.entity.DoorCard_Req;

@MapperScan
public interface IDoorCard_ReqMapper {
	
	public DoorCard_Req getDoorCard_ReqBySta(@Param("sta")String sta,@Param("pc_id")String pc_id);
	
	public int updateWriteData(DoorCard_Req dReq);
	
	public int updateReadData(DoorCard_Req dReq);
	
	public int updateEraseData(DoorCard_Req dReq);
}
