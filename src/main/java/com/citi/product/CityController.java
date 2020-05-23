package com.citi.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

	@Autowired
	private ICityService cityService;

	@GetMapping("/showCities")
	public List<City> findCities(Model model) {

		List<City> cities = (List<City>) cityService.findAll();
		System.out.println(cities);
		return cities;
	}
}
