package com.myApp.controllers;

import com.myApp.hangar.builder.HangarDtoBuilder;
import com.myApp.hangar.dto.HangarDto;
import com.myApp.hangar.model.Hangar;
import com.myApp.hangar.service.HangarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class HangarController {

	@Autowired
    HangarServiceImpl hangarService;

	@GetMapping(value="/hangars")
	public List<Hangar> getAllHangars() {
		return hangarService.getAllHangars();
	}

	@GetMapping("/hangar/{id}")
	public ResponseEntity<HangarDto> getHangarById(@PathVariable Long id) {

		final Hangar hangar = this.hangarService.getHangar(id);
		return new ResponseEntity<HangarDto>(
				new HangarDtoBuilder(hangar).getHangarDto(),
				HttpStatus.OK
		);
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


}
