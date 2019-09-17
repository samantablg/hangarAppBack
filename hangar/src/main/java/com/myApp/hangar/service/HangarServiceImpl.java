package com.myApp.hangar.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.model.Hangar;
import com.myApp.hangar.dao.HangarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HangarServiceImpl implements HangarService {
	
	@Autowired
    private HangarDao hangarDAO;

	@Override
	public List<Hangar> getAllHangars() {
	
		List<Hangar> hangars = hangarDAO.getAllHangars();
		if (!hangars.isEmpty())
			return hangars;
		throw new ApplicationException(ApplicationExceptionCause.HANGAR_NOT_FOUND);
	}

    @Override
    public List<Hangar> getAllHangarsWithName(String name) {

	    List<Hangar> hangars = hangarDAO.findHangarsByName(name);
        if (!hangars.isEmpty())
            return hangars;
        throw new ApplicationException(ApplicationExceptionCause.HANGAR_NOT_FOUND);
    }

	@Override
	public Page<Hangar> findByStateTrue(Pageable pageable) {
		Page<Hangar> items = hangarDAO.findByStateTrue(pageable);
		if (!items.isEmpty())
			return items;
		throw new ApplicationException(ApplicationExceptionCause.HANGAR_NOT_FOUND);
	}

	@Override
	public Hangar getHangar(long id) {
		if(hangarDAO.isHangarById(id))
			return hangarDAO.getHangar(id);
		throw new ApplicationException(ApplicationExceptionCause.HANGAR_NOT_FOUND);
	}

	@Override
	public Hangar createHangar(Hangar hangar) {
		if (!hangarDAO.isHangarByNameAndAddress(hangar.getName(), hangar.getAddress()))
			return hangarDAO.createHangar(hangar);
		throw new ApplicationException(ApplicationExceptionCause.HANGAR_CONFLICT);
	}

    public Hangar modifyHangar(Hangar hangar) {
        if (hangarDAO.isHangarById(hangar.getId())) {
			Hangar _hangar = manageUpdateHangar(hangar);
			return hangarDAO.updateHangar(hangar);
		}
        throw new ApplicationException(ApplicationExceptionCause.HANGAR_NOT_FOUND);
    }

    @Override
    public Hangar updateState(long id) {
        if (hangarDAO.isHangarById(id)) {
            Hangar hangar = hangarDAO.getHangar(id);
            hangar.setState(!hangar.isState());
            return hangarDAO.updateHangar(hangar);
        } throw new ApplicationException(ApplicationExceptionCause.HANGAR_NOT_FOUND);
    }

	@Override
	public boolean isHangarById(long id) {
		return hangarDAO.isHangarById(id);
	}

	@Override
	public boolean isHangarByName(String name) {
		return hangarDAO.isHangarByName(name);
	}

	@Override
	public boolean isHangar(Hangar hangar) {
		return hangarDAO.isHangarById(hangar.getId());
	}

	private Hangar manageUpdateHangar(Hangar hangar) { //sería interesante chequear que ha cambiado respecto al hangar guardado y cambiar sólo eso
		Hangar _hangar = hangarDAO.getHangar(hangar.getId());
		_hangar.setName(hangar.getName());
		_hangar.setAddress(hangar.getAddress());
		_hangar.setEmail(hangar.getEmail());
		_hangar.setOwner(hangar.getOwner());
		_hangar.setPhone(hangar.getPhone());
		return _hangar;
	}

    /*public Hangar deleteHangar(long id) {
		if (hangarDAO.existHangar(id))
			return hangarDAO.deleteHangar(id);
		return null;
	}*/

}
