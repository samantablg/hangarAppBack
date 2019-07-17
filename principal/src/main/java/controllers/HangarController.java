package controllers;

import hangar.model.Hangar;
import hangar.service.HangarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class HangarController {

	@Autowired
    HangarServiceImpl hangarService;

	@GetMapping(value="/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping(value="/hangars")
	public List<Hangar> getAllHangars() {
		return hangarService.getAllHangars();
	}

	@GetMapping("/hangar/{id}")
	public Hangar getHangarById(@PathVariable Long id) {
		return hangarService.getHangar(id);
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
