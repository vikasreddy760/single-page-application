 package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.dao.ProductDAO;
import com.ecom.model.Product;



@Controller
public class Productcontroller {
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String landPage(@ModelAttribute("product")Product product,BindingResult result,Model model)
	{
		
		model.addAttribute("productList",productDAO.list());
		return "Product";
		
	}
	 
	@RequestMapping(value="/Product")
	public String getProductDetails(@ModelAttribute("product")Product product,BindingResult result,Model model)
	{
	
		model.addAttribute("productList",productDAO.list());
		return "Product";
	}
	
	@ RequestMapping(value="/addItem",method = RequestMethod.POST)
public String addProductDetail(@ModelAttribute("product") Product p){
		
		this.productDAO.saveOrUpdate(p);
		return "redirect:/Product";
		
	}
	
	@RequestMapping(value="/getItemById/{id}",method = RequestMethod.GET)
	public String editProductDetail(@PathVariable("id") int id,RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("product", productDAO.get(id));
		return "redirect:/Product";
		
}
	@RequestMapping(value="/deleteProductById/{id}",method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") int id,Product product)
	{
		productDAO.delete(product);
		return "redirect:/Product";
		
}
}