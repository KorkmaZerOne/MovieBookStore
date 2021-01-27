package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.Category;
import com.omerkorkmaz.moviboostore.services.ProductService;
import com.omerkorkmaz.moviboostore.validators.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class AdminCategoryController
{
	private static final String viewPrefix = "categories/";
	
	@Autowired
	private ProductService productService;
	
	private CategoryValidator categoryValidator;

	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public String listCategories(Model model) {
		List<Category> list = productService.getAllCategories();
		model.addAttribute("categories",list);
		return viewPrefix+"categories";
	}
	
	@RequestMapping(value="/categories/new", method=RequestMethod.GET)
	public String createCategoryForm(Model model) {
		Category category = new Category();
		model.addAttribute("category",category);
		
		return viewPrefix+"create_category";
	}

	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		//categoryValidator.validate(category, result);
		if(result.hasErrors()){
			return viewPrefix+"create_category";
		}
		Category persistedCategory = productService.createCategory(category);
		redirectAttributes.addFlashAttribute("info", "Category created successfully");
		return "redirect:/categories";
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.GET)
	public String editCategoryForm(@PathVariable Integer id, Model model) {
		Category category = productService.getCategoryById(id);
		model.addAttribute("category",category);
		return viewPrefix+"edit_category";
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.POST)
	public String updateCategory(Category category, Model model, RedirectAttributes redirectAttributes) {
		Category persistedCategory = productService.updateCategory(category);
		redirectAttributes.addFlashAttribute("info", "Category updated successfully");
		return "redirect:/categories";
	}

}
