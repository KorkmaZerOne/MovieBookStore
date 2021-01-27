package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.Cart;
import com.omerkorkmaz.moviboostore.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CheckoutController {

    @RequestMapping(value="/checkout", method= RequestMethod.GET)
    public String checkout(HttpServletRequest request, Model model)
    {
        Order order = new Order();
        model.addAttribute("order", order);
        Cart cart = getOrCreateCart(request);
        model.addAttribute("cart", cart);
        return "checkout";
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
