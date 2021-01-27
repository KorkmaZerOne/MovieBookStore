package com.omerkorkmaz.moviboostore.controllers;


import com.omerkorkmaz.moviboostore.model.Product;
import com.omerkorkmaz.moviboostore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String getProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @RequestMapping("/product/{sku}")
    public String productView(@PathVariable String sku, Model model)
    {
        Product product = productService.getProductBySku(sku);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping("/productsearch")
    public String searchProducts(@RequestParam(name="q", defaultValue="") String query, Model model)
    {
        List<Product> products = productService.searchProducts(query);
        model.addAttribute("products", products);
        return "productsearch";
    }
}
