package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.hangar.builder.DtoBuilder;
import com.myApp.hangar.builder.HangarBuilder;
import com.myApp.hangar.dto.HangarDto;
import com.myApp.hangar.model.Hangar;
import com.myApp.hangar.repository.HangarRepository;
import com.myApp.hangar.service.HangarServiceImpl;
import com.myApp.security.model.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class HangarController {

    @Autowired
    HangarRepository hangarRepository;

	@Autowired
    HangarServiceImpl hangarService;

	@GetMapping("/hangars")
	public ResponseEntity<List<HangarDto>> getAllHangars() {
		 List<Hangar> hangars = hangarService.getAllHangars();

        return new ResponseEntity<>(
                hangars.stream().map(
                        hangar -> new DtoBuilder(hangar).getHangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
	}

	//TODO implementar a lo largo de todas las capas
	@GetMapping("/hangars/{page}/{items}")
    public Page<Hangar> hangarList(@PathVariable("page") int page, @PathVariable("items") int items) {

        Pageable itemsToPage = PageRequest.of(page, items);
        Page<Hangar> allHangars = hangarRepository.findByStateTrue(itemsToPage);

        Page<Hangar> result = new PageImpl<Hangar>(
                new ArrayList<>(allHangars.getContent()),
                itemsToPage,
                allHangars.getTotalElements());

        return result;
    }

    public ResponseEntity<HangarDto> getHangarById(@PathVariable long id) {
        if(id<=0)
            throw new ControllerException.idNotAllowed(id);

        final Hangar hangar = hangarService.getHangar(id);
        return new ResponseEntity<>(
                new DtoBuilder(hangar).getHangarDto(),
                HttpStatus.OK
        );
    }

    //TODO es realmente necesario...?
    @GetMapping("/basicDataHangars")
    public List<Object> getBasicDataOfHangars() {
	    return hangarService.getColumnIdAndName();
    }

	@PostMapping("/hangar")
	public ResponseEntity<Hangar> createHangar(@RequestBody HangarDto hdto) {

	    if(hdto.getName()!= null && hdto.getAddress() != null) {
            Hangar hangar = new HangarBuilder(hdto).getHangar();
            return new ResponseEntity<>(hangarService.createHangar(hangar),  HttpStatus.OK);
        }
        throw new ControllerException.hangarEmptyException();

	}

	/*Este método ya no se usa, se cambia el estado de activo a inactivo
	@DeleteMapping("/hangar/{id}")
	public Hangar deleteHangar(@PathVariable Long id) {
		return hangarService.deleteHangar(id);
	}*/

	@PutMapping("/hangar")
    public ResponseEntity<Hangar> updateHangar(@RequestBody HangarDto update) {
	    if(update.getName()!= null && update.getAddress()!= null) {
            Hangar hangar = new HangarBuilder(update).getHangar();
            return new ResponseEntity<>(hangarService.modifyHangar(hangar),  HttpStatus.OK);
        }
	    throw new ControllerException.hangarEmptyException();
    }

    //Logic Delete
    @PutMapping("/hangar/{id}")
    public ResponseEntity<Hangar> updateState(@PathVariable Long id) {
        if(id<=0)
            throw new ControllerException.idNotAllowed(id);
        return new ResponseEntity<>(hangarService.updateState(id), HttpStatus.OK);
    }

}
