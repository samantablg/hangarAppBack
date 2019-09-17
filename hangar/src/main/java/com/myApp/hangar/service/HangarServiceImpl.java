package com.myApp.hangar.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.exception.GeneralException;
import com.myApp.model.Hangar;
import com.myApp.hangar.dao.HangarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HangarServiceImpl implements HangarService {
	
	@Autowired
    private HangarDao hangarDAO;

	@Override
	public List<Hangar> getAllHangars() {
	
		List<Hangar> hangars = hangarDAO.getAllHangars();
		if(!hangars.isEmpty())
			return hangars;
		throw new GeneralException.HangarNotFoundException();
	}

    @Override
    public List<Hangar> getAllHangarsWithName(String name) {

	    List<Hangar> hangars = hangarDAO.findHangarsByName(name);
        if (!hangars.isEmpty())
            return hangars;
        throw new GeneralException.HangarNotFoundException();
    }

    @Override
    public boolean existHangarByName(String name) {
        return hangarDAO.existHangarByName(name);
    }

    @Override
	public boolean hangarExist(Hangar hangar) { //Comprobamos si existe por id
		return hangarDAO.existHangar(hangar.getId());
	}

	@Override
	public Hangar getHangar(long id) {
		if(hangarDAO.existHangar(id))
			return hangarDAO.getHangar(id);
		throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
	}

	@Override
	public Hangar createHangar(Hangar hangar) {
		if (!hangarDAO.existHangarByNameAndAddress(hangar.getName(), hangar.getAddress()))
			return hangarDAO.createHangar(hangar);
		throw new GeneralException.HangarExistException();
	}

	@Override
	public boolean hangarExistById(long id) {
		return hangarDAO.existHangar(id);
	}

    public Hangar modifyHangar(Hangar update) {
        if (hangarDAO.existHangar(update.getId()))
            return hangarDAO.updateHangar(update);
        throw new GeneralException.HangarExistException();
    }

    @Override
    public Hangar updateState(Long id) {

        if(hangarDAO.existHangar(id)) {
            Hangar hangar = hangarDAO.getHangar(id);
            hangar.setState(!hangar.isState());
            return hangarDAO.updateHangar(hangar);
        }
        throw new GeneralException.HangarNotFoundException(id);
    }

    /*public Hangar deleteHangar(long id) {
		if (hangarDAO.existHangar(id))
			return hangarDAO.deleteHangar(id);
		return null;
	}*/

}
