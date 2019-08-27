package com.myApp.hangar.dao;

import com.myApp.hangar.model.Hangar;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HangarDao {
	
	List<Hangar> getAllHangars();

    List<Hangar> findHangarsByName(String name);

    Boolean existHangarByName(String name);

	Hangar getHangar(Long id);
	
	Hangar createHangar(Hangar reqHangar);
	
	//Hangar deleteHangar(Long id);

	boolean existHangar(Long id);

    Hangar updateHangar(Hangar hangar);

    List<Object> getBasicDataHangars();

}
