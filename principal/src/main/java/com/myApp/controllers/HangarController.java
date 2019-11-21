package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.hangar.builder.MinifiedHangarDtoBuilder;
import com.myApp.hangar.builder.DtoBuilder;
import com.myApp.hangar.builder.HangarBuilder;
import com.myApp.hangar.dto.HangarDto;
import com.myApp.model.Hangar;
import com.myApp.hangar.service.HangarServiceImpl;
import com.myApp.model.Minified;
import com.myApp.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class HangarController {

	@Autowired
    private HangarServiceImpl hangarService;

	@Autowired
    private Util util;

    @RequestMapping(value = "/hangars",  method = RequestMethod.GET)
	public ResponseEntity<List<HangarDto>> getAllHangars() {
        final List<Hangar> hangars = hangarService.getAllHangars();
        return new ResponseEntity<>(
                hangars.stream().map(
                        hangar -> new DtoBuilder(hangar).getHangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
	}

    @RequestMapping(value = "/hangars/{page}/{items}",  method = RequestMethod.GET)
    public ResponseEntity<Page<Hangar>> hangarList(@PathVariable("page") int page, @PathVariable("items") int items) {
        util.checkNumber(page);
        util.checkNumber(items);

        Pageable itemsToPage = PageRequest.of(page, items);
        Page<Hangar> hangars = hangarService.findByStateTrue(itemsToPage);

        return new ResponseEntity<>(
                new PageImpl<Hangar>(
                        new ArrayList<>(hangars.getContent()),
                        itemsToPage,
                        hangars.getTotalElements()),
                HttpStatus.OK
        );
    }


    @RequestMapping(value = "/hangar/{id}",  method = RequestMethod.GET)
    public ResponseEntity<HangarDto> getHangarById(@PathVariable long id) {
        util.checkId(id);
        final Hangar hangar = hangarService.getHangar(id);
        return new ResponseEntity<>(
                new DtoBuilder(hangar).getHangarDto(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/basicDataHangars",  method = RequestMethod.GET)
    public ResponseEntity<List<Minified>> getBasicDataOfHangars() {
        final List<Hangar> hangars = hangarService.getAllHangars();
        return new ResponseEntity<>
                (hangars.stream()
                .map(hangar ->
                    new MinifiedHangarDtoBuilder(hangar).getBasicDataHangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
	}

    @RequestMapping(value = "/hangar",  method = RequestMethod.POST)
	public ResponseEntity<HangarDto> createHangar(@Valid @RequestBody HangarDto hangarDto) {
        Hangar hangar = new HangarBuilder(hangarDto).getHangar();
        final Hangar _hangar =  hangarService.createHangar(hangar);
        return new ResponseEntity<>(
                new DtoBuilder(_hangar).getHangarDto(),
                HttpStatus.OK
        );
	}

    @RequestMapping(value = "/hangar",  method = RequestMethod.PUT)
    public ResponseEntity<HangarDto> updateHangar(@Valid @RequestBody HangarDto hangarDto) {
	    Hangar hangar = new HangarBuilder(hangarDto).getHangar();
	    final Hangar _hangar = hangarService.modifyHangar(hangar);
	    return new ResponseEntity<>(
	            new DtoBuilder(_hangar).getHangarDto(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/hangar/{id}",  method = RequestMethod.PUT) //Logic Delete
    public ResponseEntity<HangarDto> updateState(@PathVariable Long id) {
        util.checkId(id);
        final Hangar hangar = hangarService.updateState(id);
        return new ResponseEntity<>(
                new DtoBuilder(hangar).getHangarDto(),
                HttpStatus.OK);
    }
    @RequestMapping(value = "/search",  method = RequestMethod.GET)
    public ResponseEntity<List<HangarDto>> findHangarLikeName(@RequestParam String name) {
	    if (name.length() > 0) {
	        final List<Hangar> hangars = hangarService.getAllHangarsWithName(name);
            return new ResponseEntity<>(
                    hangars.stream().map(
                            hangar -> new DtoBuilder(hangar).getHangarDto()).collect(Collectors.toList()),
                    HttpStatus.OK);
        } throw new ControllerException.searchHangarException();
    }

    @RequestMapping(value ="hangar/exist/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> findByHangarName(@PathVariable String name) {
        if (!hangarService.isHangarByName(name)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } throw new ControllerException.hangarExistException();
    }

    /*Este método ya no se usa, se cambia el estado de activo a inactivo
	@DeleteMapping("/hangar/{id}")
	public Hangar deleteHangar(@PathVariable Long id) {
		return hangarService.deleteHangar(id);
	}*/

}
