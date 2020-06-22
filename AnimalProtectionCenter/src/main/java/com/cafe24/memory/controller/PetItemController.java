package com.cafe24.memory.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.memory.domain.CompanionAnimalRegister;
import com.cafe24.memory.domain.PetFood;
import com.cafe24.memory.service.AnimalTypeService;
import com.cafe24.memory.service.CompanionAnimalRegisterService;
import com.cafe24.memory.service.PetItemService;

@Controller
public class PetItemController {
	
	@Autowired
	private PetItemService petItemService;
	
	@Autowired
	private AnimalTypeService animalTypeService;
	
	@Autowired
	private CompanionAnimalRegisterService companionAnimalRegisterService; 
	
	@GetMapping("/petGoodsUpdate")
	public String petGoodsUpdate() {
		
		return "petitem/petGoodsUpdate";
	}
	
	@PostMapping("/petGoodsList")
	public String petGoodsList(Model model) {
		
		return "petitem/petGoodsList";
	}
	
	@GetMapping("/petGoodsList")
	public String petGoodsList() {
		
		return "petitem/petGoodsList";
	}
	
	@GetMapping("/petFoodDelete")
	public String petFoodDelete(
			@RequestParam(name = "foodCode", required = false) String foodCode) {
		petItemService.deletePetFood(foodCode);
		
		return "redirect:/petFoodList";
	}
	
	@GetMapping("/petFoodUpdate")
	public String petFoodUpdate(Model model, 
			@RequestParam(value = "foodCode", required = false) String foodCode) {
		PetFood petFood = petItemService.selectPetFoodByPetFoodCode(foodCode);
		List<Map<String, Object>> animalTypeCnt = animalTypeService.selectAnimalCnt();
		model.addAttribute("aniTypeCnt", animalTypeCnt);
		model.addAttribute("petFood", petFood);
		
		System.out.println(animalTypeCnt + " <-- animalTypeCnt petFoodUpdate()");
		System.out.println(petFood  + " <-- petFood petFoodUpdate()");
		
		return "petitem/petFoodUpdate"; 
	}
	
	@PostMapping("/petFoodUpdate")
	public String petFoodUpdate(PetFood petFood,
			@RequestParam(value = "usingfoodAmount", required = false) String usingfoodAmount,
			@RequestParam(value = "buyfoodAmount", required = false) String buyfoodAmount) {
		System.out.println(usingfoodAmount + " <-- usingfoodAmount petFoodUpdate()");
		System.out.println(buyfoodAmount + " <-- buyfoodAmount petFoodUpdate()");
		
		if(buyfoodAmount != null && !"".equals(buyfoodAmount)) {
			int amount = petFood.getFoodAmount() + Integer.parseInt(buyfoodAmount);
			int remain = petFood.getFoodRemain() + Integer.parseInt(buyfoodAmount);
			petFood.setFoodAmount(amount);			
			petFood.setFoodRemain(remain);
		}
		if(usingfoodAmount != null && !"".equals(usingfoodAmount)) {
			int remain = petFood.getFoodRemain() - Integer.parseInt(usingfoodAmount);
			petFood.setFoodRemain(remain);
		}
		
		System.out.println(petFood + " <-- petFood petFoodUpdate()");
		petItemService.updatePetFood(petFood);
		
		return "redirect:/petFoodList";
	}
	
	@GetMapping("/petFoodList")
	public String petFoodList(Model model,
			@RequestParam(name = "sendType", required = false) String sendType) {
		List<PetFood> petFoodList = petItemService.selectPetFood();
		List<Map<String, Object>> petFoodTypeCnt = petItemService.selectPetFoodTypeCount();
		
		System.out.println(petFoodTypeCnt + " <-- petFoodTypeCnt");
		System.out.println(sendType + " <-- sendType");
		System.out.println(petFoodList);
		
		int totalCount = 0;
		totalCount = petFoodList.size();
		
		if(sendType != null) {
			if("강아지".equals(sendType)) {
				petFoodList = petItemService.selectPetFoodByFoodType("강아지");
			}else if ("고양이".equals(sendType)) {
				petFoodList = petItemService.selectPetFoodByFoodType("고양이");
			}else {
				petFoodList = petItemService.selectPetFoodByFoodType("기타");
			}
		}else {
			
		}

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("petFoodTypeCnt", petFoodTypeCnt);
		model.addAttribute("petFoodList", petFoodList);
		
		return "petitem/petFoodList";
	}
	
	@GetMapping("/petFoodInsert")
	public String petFoodInsert(Model model) {
		List<Map<String, Object>> animalTypeCnt = animalTypeService.selectAnimalCnt();
		model.addAttribute("aniTypeCnt", animalTypeCnt);
		
		return "petitem/petFoodInsert";
	}
	
	@PostMapping("/petFoodInsert")
	public String petFoodInsert(PetFood petFood) {
		System.out.println(petFood + " <-- petFood petFoodInsert()");
		petItemService.insertPetFood(petFood);
		
		return "redirect:/petFoodList";
	}
	
}
