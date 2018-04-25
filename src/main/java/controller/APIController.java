package controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.API;
import model.Vehicle;

/**
 * 
 * @author Jeff Lee
 * Router for the API
 *
 */

@RestController
@RequestMapping("/vehicles")
public class APIController {

	@Autowired
	API api;
	
	@RequestMapping("/all")
	public Vehicle[] getAllByPrice() {
		return api.vehiclesByPrice();
	}
	
	@RequestMapping("/type")
	public ArrayList<String> getByType() {
		return api.getCostByType();
	}
	
	@RequestMapping("/brand")
	public ArrayList<String> getByBrand() {
		return api.getCostByBrand();
	}
	
	@RequestMapping("/engine")
	public ArrayList<String> getByEngine() {
		return api.getCostByEngine();
	}
	
	@RequestMapping("/color")
	public ArrayList<String> getByColor() {
		return api.getCostByColor();
	}
}
