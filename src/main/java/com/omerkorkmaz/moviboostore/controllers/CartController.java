package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.Cart;
import com.omerkorkmaz.moviboostore.model.CartItem;
import com.omerkorkmaz.moviboostore.model.Product;
import com.omerkorkmaz.moviboostore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value="/cart", method= RequestMethod.GET)
    public String showCart(HttpServletRequest request, Model model)
    {
        Cart cart = getOrCreateCart(request);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping(value="/cart/items/count", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCartItemCount(HttpServletRequest request, Model model)
    {
        Cart cart = getOrCreateCart(request);
        int itemCount = cart.getItemCount();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", itemCount);
        return map;
    }

    @RequestMapping(value="/cart/items", method=RequestMethod.POST)
    @ResponseBody
    public void addToCart(@RequestBody Product product, HttpServletRequest request)
    {
        Cart cart = getOrCreateCart(request);
        Product p = productService.getProductBySku(product.getSku());
        cart.addItem(p);
    }

    @RequestMapping(value="/cart/items", method=RequestMethod.PUT)
    @ResponseBody
    public void updateCartItem(@RequestBody CartItem item, HttpServletRequest request, HttpServletResponse response)
    {
        Cart cart = getOrCreateCart(request);
        if(item.getQuantity() <= 0){
            String sku = item.getProduct().getSku();
            cart.removeItem(sku);
        } else {
            cart.updateItemQuantity(item.getProduct(), item.getQuantity());
        }
    }

    @RequestMapping(value="/cart/items/{sku}", method=RequestMethod.DELETE)
    @ResponseBody
    public void removeCartItem(@PathVariable("sku") String sku, HttpServletRequest request)
    {
        Cart cart = getOrCreateCart(request);
        cart.removeItem(sku);
    }

    @RequestMapping(value="/cart", method=RequestMethod.DELETE)
    @ResponseBody
    public void clearCart(HttpServletRequest request)
    {
        Cart cart = getOrCreateCart(request);
        cart.setItems(new ArrayList<CartItem>());
    }

    protected Cart getOrCreateCart(HttpServletRequest request)
    {
        Cart cart = null;
        cart = (Cart) request.getSession().getAttribute("CART_KEY");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("CART_KEY", cart);
        }
        return cart;
    }
}
