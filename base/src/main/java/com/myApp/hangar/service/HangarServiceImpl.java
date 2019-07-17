package com.myApp.hangar.service;

import com.myApp.hangar.exceptions.HangarException;
import com.myApp.hangar.model.Hangar;
import com.myApp.hangar.dao.HangarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HangarServiceImpl implements HangarService {
	
	@Autowired
    HangarDao hangarDAO;

	@Override
	public List<Hangar> getAllHangars() {
	
		List<Hangar> hangars = hangarDAO.getAllHangars();
		if(hangars != null)
			return hangars;
		throw new HangarException.HangarNotFoundException();
	}

	@Override
	public boolean hangarExist(Hangar hangar) { //Comprobamos si existe por id
		return hangarDAO.existHangar(hangar.getId());
	}

	@Override
	public Hangar getHangar(Long id) {

		if (id > 0 && id <= MaxValueId())
			return hangarDAO.getHangar(id);
		throw new HangarException.HangarNotFoundException(id);
	}

	@Override
	public Hangar createHangar(Hangar hangar) {

		Hangar newHangar = hangarDAO.createHangar(hangar);
		if (newHangar != null)
			return newHangar;
		throw new HangarException.HangarExistException();
	}

	/*public Hangar deleteHangar(Long id) {
		if (hangarDAO.existHangar(id))
			return hangarDAO.deleteHangar(id);
		return null;
	}*/

	@Override
	public boolean hangarExistById(Long id) {
		return hangarDAO.existHangar(id);
	}
	
	/*public boolean hangarExistByName(Hangar com.myHangar.hangar) {
		
		List<Hangar> hangars = hangarDAO.getAllHangars();

		int cont = 0;


		for (Hangar h: hangars) {
			if (h.getName().equals(com.myHangar.hangar.getName()))
				cont++;
		}
		if(cont > 0)
			return true;
		return false;
	}*/
	
	private long MaxValueId() {
		
		List<Hangar> hangars = hangarDAO.getAllHangars();
		
		long max = 0;
		for (Hangar h: hangars) {
			if(h.getId() > max)
				max = h.getId();
		}
		return max;
	}

}
