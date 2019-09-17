package com.myApp.hangar.service;

import com.myApp.model.Hangar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HangarService {
	
	List<Hangar> getAllHangars();

    List<Hangar> getAllHangarsWithName(String name);

	Page<Hangar> findByStateTrue(Pageable pageable);
	
	Hangar getHangar(long id);
	
	Hangar createHangar(Hangar hangar);

	Hangar modifyHangar(Hangar update);

	Hangar updateState(long id);

	boolean isHangarByName(String name);

	boolean isHangar(Hangar hangar);

	boolean isHangarById(long id);


	//Hangar deleteHangar(long id);

}
