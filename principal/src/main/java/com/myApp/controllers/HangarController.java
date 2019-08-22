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
    public ResponseEntity<Page<Hangar>> hangarList(@PathVariable("page") int page, @PathVariable("items") int items) {

        Pageable itemsToPage = PageRequest.of(page, items);
        Page<Hangar> allHangars = hangarRepository.findByStateTrue(itemsToPage);

        Page<Hangar> result = new PageImpl<Hangar>(
                new ArrayList<>(allHangars.getContent()),
                itemsToPage,
                allHangars.getTotalElements());

        return new ResponseEntity<>(
                result,
                HttpStatus.OK
                );
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
	public ResponseEntity<HangarDto> createHangar(@RequestBody HangarDto hdto) {

	    if(hdto.getName()!= null && hdto.getAddress() != null) {
            Hangar hangar = new HangarBuilder(hdto).getHangar();
            Hangar newHangar =  hangarService.createHangar(hangar);
            return new ResponseEntity<>(
                    new DtoBuilder(newHangar).getHangarDto(),
                    HttpStatus.OK);
        }
        throw new ControllerException.hangarEmptyException();

	}

	/*Este método ya no se usa, se cambia el estado de activo a inactivo
	@DeleteMapping("/hangar/{id}")
	public Hangar deleteHangar(@PathVariable Long id) {
		return hangarService.deleteHangar(id);
	}*/

	@PutMapping("/hangar")
    public ResponseEntity<HangarDto> updateHangar(@RequestBody HangarDto update) {
	    if(update.getName()!= null && update.getAddress()!= null) {
            Hangar hangar = new HangarBuilder(update).getHangar();
            Hangar modifyHangar = hangarService.modifyHangar(hangar);
            return new ResponseEntity<>(
                    new DtoBuilder(modifyHangar).getHangarDto(),
                    HttpStatus.OK);
        }
	    throw new ControllerException.hangarEmptyException();
    }

    //Logic Delete
    @PutMapping("/hangar/{id}")
    public ResponseEntity<HangarDto> updateState(@PathVariable Long id) {
        if(id<=0) {
            throw new ControllerException.idNotAllowed(id);
        }
        Hangar updateHangar = hangarService.updateState(id);
        return new ResponseEntity<>(
                new DtoBuilder(updateHangar).getHangarDto(),
                HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<HangarDto>> findHangarLikeName(@RequestParam String name) {
	    if (name.length()>0) {
	        List<Hangar> result = hangarService.getAllHangarsWithName(name);
            return new ResponseEntity<>(
                    result.stream().map(
                            hangar -> new DtoBuilder(hangar).getHangarDto()).collect(Collectors.toList()),
                    HttpStatus.OK);
        }
        throw new ControllerException.searchHangarException();
    }

}
