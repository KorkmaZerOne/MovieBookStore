package com.omerkorkmaz.moviboostore.validators;

import com.omerkorkmaz.moviboostore.model.Product;
import com.omerkorkmaz.moviboostore.model.ProductForm;
import com.omerkorkmaz.moviboostore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductFormValidator implements Validator {

    @Autowired
    protected MessageSource messageSource;
    @Autowired protected ProductService productService;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        ProductForm product = (ProductForm) target;
        String sku = product.getSku();
        Product p = productService.getProductBySku(sku);
        if(p != null){
            errors.rejectValue("sku", "error.exists", new Object[]{sku}, "Product SKU "+sku+" already exists");
        }
    }

}
