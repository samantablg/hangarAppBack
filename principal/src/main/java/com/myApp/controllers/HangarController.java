package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.hangar.builder.HangarDtoBuilder;
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

import javax.validation.Valid;
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
	public List<Hangar> getAllHangars() {
		return hangarService.getAllHangars();
	}

	@GetMapping("/hangars/{page}/{items}")
    public Page<Hangar> hangarList(@PathVariable("page") int page, @PathVariable("items") int items) {

        Pageable itemsToPage = PageRequest.of(page, items);
        Page<Hangar> allHangars = hangarRepository.findByStateTrue(itemsToPage);

        Page<Hangar> result = new PageImpl<Hangar>(
                allHangars.getContent().stream()
                .collect(Collectors.toList()),
                itemsToPage,
                allHangars.getTotalElements());

        return result;
    }

	@GetMapping("/hangar/{id}")
	public ResponseEntity<HangarDto> getHangarById(@PathVariable long id) {
		if(id<=0)
			throw new ControllerException.idNotAllowed(id);

		final Hangar hangar = hangarService.getHangar(id);
		return new ResponseEntity<HangarDto>(
			new HangarDtoBuilder(hangar).getHangarDto(),
			HttpStatus.OK
		);
	}

	@GetMapping("/basicDataHangars")
    public List<Object> getBasicDataOfHangars() {
	    return hangarService.getColumnIdAndName();
    }

	@PostMapping("/hangar")
	public Hangar createHangar(@Valid @RequestBody Hangar hangar) {
		Hangar newHangar = new Hangar();
		newHangar.setName(hangar.getName());
		newHangar.setAddress(hangar.getAddress());
		return hangarService.createHangar(newHangar);
	}

	/*Este método ya no se usa, se cambia el estado de activo a inactivo
	@DeleteMapping("/hangar/{id}")
	public Hangar deleteHangar(@PathVariable Long id) {
		return hangarService.deleteHangar(id);
	}*/

	@PutMapping("/hangar")
    public Hangar updateHangar(@RequestBody Hangar update) {
	    return hangarService.modifyHangar(update);
    }

    //Logic Delete
    @PutMapping("/hangar/{id}")
    public Hangar updateState(@PathVariable Long id) {
        if(id<=0)
            throw new ControllerException.idNotAllowed(id);
        return hangarService.updateState(id);
    }

}
