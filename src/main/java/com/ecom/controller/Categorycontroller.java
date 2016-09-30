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

import com.ecom.dao.CategoryDAO;
import com.ecom.model.Category;

@Controller
public class Categorycontroller {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String landPage(@ModelAttribute("category")Category category,BindingResult result,Model model)
	{
		model.addAttribute("categoryList",categoryDAO.list());
		return "Category";
		
	}
	 
	@RequestMapping(value="/Category")
	public String getCategoryDetails(@ModelAttribute("category")Category category,BindingResult result,Model model)
	{
	
		model.addAttribute("categoryList",categoryDAO.list());
		return "Category";
	}
	
	@RequestMapping(value="/Category",method = RequestMethod.POST)
public String addCategoryDetails(@ModelAttribute("category") Category c){
		
		this.categoryDAO.saveOrUpdate(c);
		return "redirect:/Category";
		
	}
	@RequestMapping(value="/ItemById/{id}",method = RequestMethod.GET)
	public String editSupplierDetails(@PathVariable("id") int id,RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("category", categoryDAO.get(id));
		return "redirect:/Category";
		
}
	@RequestMapping(value="/deleteById/{id}",method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") int id,Category category)
	{
		categoryDAO.delete(category);
		return "redirect:/Category";
		
}
}