package com.myApp.hangar.dao;

import com.myApp.model.Hangar;
import java.util.List;

public interface HangarDao {
	
	List<Hangar> getAllHangars();

    List<Hangar> findHangarsByName(String name);

    Boolean existHangarByName(String name);

	Boolean existHangarByNameAndAddress(String name, String address);

	Hangar getHangar(Long id);
	
	Hangar createHangar(Hangar reqHangar);
	
	//Hangar deleteHangar(Long id);

	boolean existHangar(Long id);

    Hangar updateHangar(Hangar hangar);


}
