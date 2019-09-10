package com.myApp.hangar.service;

import com.myApp.hangar.exceptions.HangarException;
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
		if(hangars != null)
			return hangars;
		throw new HangarException.HangarNotFoundException();
	}

    @Override
    public List<Hangar> getAllHangarsWithName(String name) {

	    List<Hangar> result = hangarDAO.findHangarsByName(name);
        if (result.size() > 0 )
            return result;
        throw new HangarException.HangarNotFoundException();
    }

    @Override
    public Boolean existHangarByName(String name) {
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
		throw new HangarException.HangarNotFoundException(id);
	}

	@Override
	public Hangar createHangar(Hangar hangar) {

		Hangar newHangar = hangarDAO.createHangar(hangar);
		if (newHangar != null)
			return newHangar;
		throw new HangarException.HangarExistException();
	}

	/*public Hangar deleteHangar(long id) {
		if (hangarDAO.existHangar(id))
			return hangarDAO.deleteHangar(id);
		return null;
	}*/

	@Override
	public boolean hangarExistById(long id) {
		return hangarDAO.existHangar(id);
	}

    public Hangar modifyHangar(Hangar update) {
        if (update != null) {
            return hangarDAO.updateHangar(update);
        }
        throw new HangarException.HangarExistException();
    }

    @Override
    public Hangar updateState(Long id) {

        if(hangarDAO.existHangar(id)) {
            Hangar hangar = hangarDAO.getHangar(id);
            if(hangar.isState()) {
                hangar.setState(false);
            } else {
                hangar.setState(true);
            }
            return hangarDAO.updateHangar(hangar);
        }
        throw new HangarException.HangarNotFoundException(id);
    }

  /*  private long MaxValueId() {
		
		List<Hangar> hangars = hangarDAO.getAllHangars();
		
		long max = 0;
		for (Hangar h: hangars) {
			if(h.getId() > max)
				max = h.getId();
		}
		return max;
	}*/

}