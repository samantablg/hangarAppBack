package com.myApp.hangar.dao;

import com.myApp.model.Hangar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HangarDao {
	
	List<Hangar> getAllHangars();

    List<Hangar> findHangarsByName(String name);

	Page<Hangar> findByStateTrue(Pageable pageable);

    Hangar updateHangar(Hangar hangar);

	Hangar getHangar(Long id);

	Hangar createHangar(Hangar reqHangar);

	boolean isHangarByName(String name);

	boolean isHangarByNameAndAddress(String name, String address);

	boolean isHangarById(Long id);

	//Hangar deleteHangar(Long id);
}
