package com.myApp.hangar.service;

import com.myApp.model.Hangar;
import java.util.List;

public interface HangarService {
	
	List<Hangar> getAllHangars();

    List<Hangar> getAllHangarsWithName(String name);
	
	Hangar getHangar(long id);
	
	Hangar createHangar(Hangar hangar);

	boolean existHangarByName(String name);
	
	//Hangar deleteHangar(long id);

	boolean hangarExist(Hangar hangar);

	boolean hangarExistById(long id);

	Hangar modifyHangar(Hangar update);

    Hangar updateState(Long id);
	
}
