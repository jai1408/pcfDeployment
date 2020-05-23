package com.citi.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Jai
 *
 */
@RestController
public class ProductServiceController {
   private static Map<String, Product> productMap = new HashMap<>();
   static {
      Product honey = new Product();
      honey.setId("1");
      honey.setName("Honey");
      productMap.put(honey.getId(), honey);
      
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Almond");
      productMap.put(almond.getId(), almond);
   }
   
   @RequestMapping("/")
	public String healthCheck() {
		return "OK";
	}
   
	/*
	 * @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	 * public ResponseEntity<Object> delete(@PathVariable("id") String id) {
	 * productMap.remove(id); return new
	 * ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK); }
	 */
   
   @RequestMapping(value = "/products/delete", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@RequestParam(name="id", required=false, defaultValue="1") String id) { 
      productMap.remove(id);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
    
// @RequestParam(name="name", required=false, defaultValue="Unknown") String name
   
   @RequestMapping(value = "/products", method = RequestMethod.GET)
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productMap.values(), HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      productMap.remove(id);
      product.setId(id);
      productMap.put(id, product);
      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
      productMap.put(product.getId(), product);
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
}
