package com.foxhis.itf.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.foxhis.itf.entity.Internet_pms;
import com.foxhis.itf.entity.Master_temp;

@MapperScan
public interface ICtrlRoomMapper {
	
	public List<Internet_pms> getInternetPmsByTag(String tag);
	
	public Master_temp getMasterTempByAccnt(String accnt);
	
	public int updateInternetPmsByTag(Internet_pms pms);
	
	

}
