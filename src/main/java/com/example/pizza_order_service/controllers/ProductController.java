package com.example.pizza_order_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.pizza_order_service.model.Product;
import com.example.pizza_order_service.model.User;
import com.example.pizza_order_service.repository.AddonRepository;
import com.example.pizza_order_service.repository.CartRepository;
import com.example.pizza_order_service.repository.Cart_ProductRepository;
import com.example.pizza_order_service.repository.Cart_Product_AddonRepository;
import com.example.pizza_order_service.repository.PaymentInfoRepository;
import com.example.pizza_order_service.repository.ProductRepository;
import com.example.pizza_order_service.repository.TransactionRepository;
import com.example.pizza_order_service.repository.UserRepository;


@RestController
public class ProductController {

//	@Autowired
//    private AddonRepository AddonData;
//	
//	@Autowired
//    private CartRepository CartData;
//	
//	@Autowired
//    private Cart_ProductRepository Cart_ProductData;
//	
//	@Autowired
//    private Cart_Product_AddonRepository Cart_Product_AddonData;
//	
//	@Autowired
//    private PaymentInfoRepository PaymentInfoData;
	
	@Autowired
    private ProductRepository ProductData;
	
//	@Autowired
//    private TransactionRepository TransactionData;
	
	@Autowired
    private UserRepository UserData;
	
	//Create Product
   	@GetMapping("/Product")
	public ModelAndView getPage() {
   		Product prod = new Product();
		return new ModelAndView("createProduct", "fn", prod);       
	}

	@PostMapping("/Product")
	public ModelAndView createProduct(Product prod) {
		Product newProd = ProductData.save(prod);
		
		return new ModelAndView("create", "fn2", newProd);

	}
	
	//Read/Display product
	@GetMapping("/Product")
	public ModelAndView listProduct() {
		List<Product> products = (List<Product>) ProductData.findAll();
		return new ModelAndView("read","product", products);
	}
	
	//Update/Edit product
	@GetMapping("/Product")
	public ModelAndView getupdate() {
		Product prod = new Product();


		return new ModelAndView("UpdateProducts", "fn3" , prod);
	}
	@PostMapping("/Product")
	public ModelAndView updateProd(Product product) {

		ProductData.save(product);
		
		return new ModelAndView("updateProducts2", "fn4", product);
	}
	
	
	//Delete a product by id
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") long theId)  
	{  

		ProductData.deleteById(theId);
		return "Product deleted successfully....(insert hyperlink to desired page)";

	} 
	
	@GetMapping("/login")
	public ModelAndView getPage2() {
		User usr = new User();
		return new ModelAndView("login", "fn5", usr);       
	}

	@PostMapping("/login")
	public String createUser(User u) {
		
		User aUser = UserData.getUserByPassword(u.getPassword());
		aUser = UserData.getUserByName(u.getName());
		
		if(aUser != null) {
			return "Login Successful. (insert hyperlink to desired page)";
		}
		else {
		return "Login Unsuccessful. (insert hyperlink to desired page)";
		} 
	}
}