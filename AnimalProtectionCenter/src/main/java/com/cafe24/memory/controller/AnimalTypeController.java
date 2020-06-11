package com.cafe24.memory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnimalTypeController {
	//animal type
	@GetMapping("/animaltypelist")
	public String listAnimalType() {
		return "animaltype/animalTypeList";
	}
	@GetMapping("/animaltypeinsert")
	public String insertAnimalType() {
		return "animaltype/animalTypeInsert";
	}
	@GetMapping("/animaltypeupdate")
	public String updateAnimalType() {
		return "animaltype/animalTypeUpdate";
	}
	@GetMapping("/animaltypedelete")
	public String deleteAnimalType() {
		//경로 delete
		return "animaltype/animalTypeList";
	}
}
