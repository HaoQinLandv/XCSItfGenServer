package com.foxhis.itf.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.foxhis.itf.entity.Internet_pms;
import com.foxhis.itf.entity.Master_temp;
import com.foxhis.itf.entity.Pms_building;

@MapperScan
public interface ICtrlRoomMapper {
	
	@Deprecated
	public List<Internet_pms> getInternetPmsByTag(String tag);

	@Deprecated
	public int updateInternetPmsByTag(Internet_pms pms);
	
	/**
	 * 通过账号获取mastertemp表数据
	 * @param accnt
	 * @return
	 */
	public Master_temp getMasterTempByAccnt(String accnt);
	
	public List<Pms_building> getPmsBuildingByType(String type);
	
	public int updatePmsBuildingByTag(Pms_building pms);
	
	public List<Master_temp> getRmSta();

}
