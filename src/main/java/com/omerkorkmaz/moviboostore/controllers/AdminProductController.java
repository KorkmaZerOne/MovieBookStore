package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.Product;
import com.omerkorkmaz.moviboostore.model.ProductForm;
import com.omerkorkmaz.moviboostore.services.CategoryService;
import com.omerkorkmaz.moviboostore.services.ProductService;
import com.omerkorkmaz.moviboostore.validators.ProductFormValidator;
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

@Controller
public class AdminProductController {

    @Autowired
    private ProductService productService;

    private CategoryService categoryService;

   // private ProductFormValidator productFormValidator;


    @RequestMapping(value="/products", method= RequestMethod.GET)
    public String listProducts(Model model) {
        model.addAttribute("products",productService.getAllProducts());
        return "products/products";
    }

    @RequestMapping(value="/products/new", method=RequestMethod.GET)
    public String createProductForm(Model model) {
        ProductForm product = new ProductForm();
        model.addAttribute("product", product);
      //  model.addAttribute("categoriesList",categoryService.getAllCategories());
        return "products/create_product";
    }

    @RequestMapping(value="/products", method=RequestMethod.POST)
    public String createProduct(@Valid @ModelAttribute("product") ProductForm productForm, BindingResult result,
                                Model model, RedirectAttributes redirectAttributes) {
      // productFormValidator.validate(productForm, result);
        if(result.hasErrors()){
            return "products/create_product";
        }
        Product product = productForm.toProduct();
        Product persistedProduct = productService.createProduct(product);
        productForm.setId(product.getProductId());
        redirectAttributes.addFlashAttribute("info", "Product created successfully");
        return "products/products";
    }

    @RequestMapping(value="/products/{id}", method=RequestMethod.GET)
    public String editProductForm(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product",ProductForm.fromProduct(product));
        model.addAttribute("categoriesList",categoryService.getAllCategories());
        return "products/edit_product";
    }
}
