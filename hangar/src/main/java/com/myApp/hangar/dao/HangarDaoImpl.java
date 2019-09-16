package com.myApp.hangar.dao;


import com.myApp.model.Hangar;
import com.myApp.hangar.repository.HangarRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Boolean existHangarByName(String name) {
        return hangarRepository.existHangarByName(name);
    }

	@Override
	public Boolean existHangarByNameAndAddress(String name, String address) { return hangarRepository.existsHangarByNameAndAddress(name, address); }

	@Override
	public Hangar getHangar(Long id) {
		return hangarRepository.getOne(id);
	}

	@Override
	public Hangar createHangar(Hangar hangar) { return hangarRepository.save(hangar); }

	/*@Override
	public Hangar deleteHangar(Long id) {
		Hangar com.myHangar.hangar = hangarRepository.getOne(id);
		if(com.myHangar.hangar != null)
			hangarRepository.delete(com.myHangar.hangar);
		return null;
	}*/

	@Override
	public boolean existHangar(Long id) {
		return hangarRepository.existsById(id);
	}

    @Override
    public Hangar updateHangar(Hangar hangar) {
        return hangarRepository.saveAndFlush(hangar);
    }

}
