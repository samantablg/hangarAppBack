package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.hangar.builder.BasicDataHangarDtoBuilder;
import com.myApp.hangar.builder.DtoBuilder;
import com.myApp.hangar.builder.HangarBuilder;
import com.myApp.hangar.dto.BasicDataHangarDto;
import com.myApp.hangar.dto.HangarDto;
import com.myApp.hangar.model.Hangar;
import com.myApp.hangar.repository.HangarRepository;
import com.myApp.hangar.service.HangarServiceImpl;
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
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class HangarController {

    @Autowired
    private HangarRepository hangarRepository;

	@Autowired
    private HangarServiceImpl hangarService;

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
        Page<Hangar> hangars = hangarRepository.findByStateTrue(itemsToPage);

        return new ResponseEntity<>(
                new PageImpl<Hangar>(
                        new ArrayList<>(hangars.getContent()),
                        itemsToPage,
                        hangars.getTotalElements()),
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

    @GetMapping("/basicDataHangars")
    public ResponseEntity<List<BasicDataHangarDto>> getBasicDataOfHangars() {
        List<Hangar> hangars = hangarService.getAllHangars();

        return new ResponseEntity<>(hangars.stream()
                .map(hangar ->
                    new BasicDataHangarDtoBuilder(hangar).getBasicDataHangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
	}

	@PostMapping("/hangar")
	public ResponseEntity<HangarDto> createHangar(@RequestBody HangarDto hdto) {
	    if(!hdto.getName().equals("") && !hdto.getAddress().equals("")) {
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
	    if(!update.getName().equals("") && !update.getAddress().equals("") && !update.getOwner().equals("")) {
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
    public ResponseEntity<List<HangarDto>> findHangarLikeName(@RequestParam String hangarName) {
	    if (hangarName.length()>0) {
	        List<Hangar> result = hangarService.getAllHangarsWithName(hangarName);
            return new ResponseEntity<>(
                    result.stream().map(
                            hangar -> new DtoBuilder(hangar).getHangarDto()).collect(Collectors.toList()),
                    HttpStatus.OK);
        }
        throw new ControllerException.searchHangarException();
    }

    @RequestMapping(value ="hangar/exist/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> findByHangarName(@PathVariable String name) {
        boolean hangarExist = hangarService.existHangarByName(name);
        if (!hangarExist) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        throw new ControllerException.hangarExistException();
    }

}
