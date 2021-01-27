package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.Category;
import com.omerkorkmaz.moviboostore.model.Product;
import com.omerkorkmaz.moviboostore.model.ProductForm;
import com.omerkorkmaz.moviboostore.services.ProductService;
import com.omerkorkmaz.moviboostore.validator.ProductFormValidator;
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
public class AdminProductController {

    private static final String viewPrefix = "products/";

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFormValidator productFormValidator;


    @ModelAttribute("categoriesList")
    public List<Category> categoriesList()
    {
        return productService.getAllCategories();
    }

    @RequestMapping(value="/products", method= RequestMethod.GET)
    public String listProducts(Model model) {
        model.addAttribute("products",productService.getAllProducts());
        return viewPrefix+"products";
    }

    @RequestMapping(value="/products/new", method=RequestMethod.GET)
    public String createProductForm(Model model) {
        ProductForm product = new ProductForm();
        model.addAttribute("product",product);
        model.addAttribute("categoriesList",productService.getAllCategories());
        return viewPrefix+"create_product";
    }

    @RequestMapping(value="/products", method=RequestMethod.POST)
    public String createProduct(@Valid @ModelAttribute("product") ProductForm productForm, BindingResult result,
                                Model model, RedirectAttributes redirectAttributes) {
        productFormValidator.validate(productForm, result);
        if(result.hasErrors()){
            return viewPrefix+"create_product";
        }
        Product product = productForm.toProduct();
        Product persistedProduct = productService.createProduct(product);
        productForm.setId(product.getProductId());
        redirectAttributes.addFlashAttribute("info", "Product created successfully");
        return "redirect:/products";
    }

    @RequestMapping(value="/products/{id}", method=RequestMethod.GET)
    public String editProductForm(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product",ProductForm.fromProduct(product));
        model.addAttribute("categoriesList",productService.getAllCategories());
        return viewPrefix+"edit_product";
    }
}
