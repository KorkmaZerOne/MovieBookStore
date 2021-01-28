package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.Category;
import com.omerkorkmaz.moviboostore.services.CategoryService;
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
	private CategoryService categoryService;
	
	//private CategoryValidator categoryValidator;


	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public String listCategories(Model model) {
		List<Category> list = categoryService.getAllCategories();
		model.addAttribute("categories", list);
		return "categories/categories";
	}

	@RequestMapping(value="/categories/new", method=RequestMethod.GET)
	public String createCategoryForm(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);

		return "categories/create_category";
	}

	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		//categoryValidator.validate(category, result);
		if(result.hasErrors()){
			return "categories/create_category";
		}
		Category persistedCategory = categoryService.createCategory(category);
		redirectAttributes.addFlashAttribute("info", "Category created successfully");
		return "redirect: /categories";
	}

	@RequestMapping(value="/categories/{id}", method=RequestMethod.GET)
	public String editCategoryForm(@PathVariable Integer id, Model model) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("category",category);
		return "categories/edit_category";
	}

	@RequestMapping(value="/categories/{id}", method=RequestMethod.POST)
	public String updateCategory(Category category, Model model, RedirectAttributes redirectAttributes) {
		Category persistedCategory = categoryService.updateCategory(category);
		redirectAttributes.addFlashAttribute("info", "Category updated successfully");
		return "redirect: /categories";
	}

}
