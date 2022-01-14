package org.springframework.samples.petclinic.feeding;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
	
	private static final String VIEWS_FEEDING_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";

	private final FeedingService feedingService;
	private final PetService petService;
	
	@Autowired
	public FeedingController(FeedingService feedingService, PetService petService) {
		this.feedingService = feedingService;
		this.petService = petService;
	}
	
	@GetMapping(value = "/create")
	public String initCreationForm(ModelMap modelMap) {
		String view = VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
		modelMap.addAttribute("feeding", new Feeding());
		modelMap.addAttribute("feedingTypes", feedingService.getAllFeedingTypes());
		modelMap.addAttribute("pets", petService.getAllPets());
		return view;
	}
	
	@PostMapping(value="/create")
	public String processCreationForm(@Valid Feeding feeding, BindingResult result, ModelMap modelMap ) {
		String view = "welcome";
		if(result.hasErrors()) {
			modelMap.addAttribute("feeding", feeding);
			modelMap.addAttribute("feedingTypes", feedingService.getAllFeedingTypes());
			modelMap.addAttribute("pets", petService.getAllPets());
			return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
		}
		else {
			try {
				feedingService.save(feeding);
			} catch (UnfeasibleFeedingException e) {
				// TODO Auto-generated catch block
				result.rejectValue("pet", "not match", "La mascota seleccionada no se le puede asignar el plan de alimentación especificado.");
                return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
			}
			modelMap.addAttribute("message", "Feeding successfully saves!");
		}
		return view;
	}
	

//	@PostMapping(value = "/create")
//	public String processCreationForm(@Valid Product product, BindingResult result, ModelMap modelMap ) {
//		String view = "welcome";
//		if(result.hasErrors()) {
//			modelMap.addAttribute("product", product);
//			modelMap.addAttribute("productType", productService.findAllProductTypes());
//			return VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM;
//		}
//		else {
//			productService.save(product);
//			modelMap.addAttribute("message", "Product saved succesfully!");
//		}
//		return view;
//	}
	
   
}
