package com.myApp.hangar.dao;

import com.myApp.hangar.model.Hangar;

import java.util.List;

public interface HangarDao {
	
	List<Hangar> getAllHangars();

	Hangar getHangar(Long id);
	
	Hangar createHangar(Hangar reqHangar);
	
	//Hangar deleteHangar(Long id);

	boolean existHangar(Long id);

    List<Object> getBasicDataHangars();

}
