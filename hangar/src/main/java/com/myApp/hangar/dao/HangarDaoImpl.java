package com.myApp.hangar.dao;


import com.myApp.model.Hangar;
import com.myApp.hangar.repository.HangarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HangarDaoImpl implements HangarDao {
	
	@Autowired
	private HangarRepository hangarRepository;
	
	public List<Hangar> getAllHangars() { return hangarRepository.findAllWithTrueState(); }

    @Override
    public List<Hangar> findHangarsByName(String name) {
	    return hangarRepository.findByNameWithTrueState(name);
    }

	@Override
	public Page<Hangar> findByStateTrue(Pageable pageable) { return hangarRepository.findByStateTrue(pageable);	}

	@Override
	public Hangar getHangar(Long id) { return hangarRepository.getOne(id); }

	@Override
	public Hangar createHangar(Hangar hangar) { return hangarRepository.save(hangar); }

	@Override
	public Hangar updateHangar(Hangar hangar) {
		return hangarRepository.saveAndFlush(hangar);
	}

	@Override
	public boolean isHangarById(Long id) {
		return hangarRepository.existsById(id);
	}

	@Override
	public boolean isHangarByName(String name) {
		return hangarRepository.existsByName(name);
	}

	@Override
	public boolean isHangarByNameAndAddress(String name, String address) { return hangarRepository.existsByNameAndAddress(name, address); }

    /*@Override
	public Hangar deleteHangar(Long id) {
		Hangar hangar = hangarRepository.getOne(id);
		return hangarRepository.delete(hangar);
	}*/

}
