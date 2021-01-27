package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.Order;
import com.omerkorkmaz.moviboostore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrderController {
    private static final String viewPrefix = "orders/";

    @Autowired protected OrderService orderService;

    @RequestMapping(value="/orders", method= RequestMethod.GET)
    public String listOrders(Model model) {
        List<Order> list = orderService.getAllOrders();
        model.addAttribute("orders",list);
        return viewPrefix+"orders";
    }

    @RequestMapping(value="/orders/{orderNumber}", method=RequestMethod.GET)
    public String editOrderForm(@PathVariable String orderNumber, Model model) {
        Order order = orderService.getOrder(orderNumber);
        model.addAttribute("order",order);
        return viewPrefix+"edit_order";
    }

    @RequestMapping(value="/orders/{orderNumber}", method=RequestMethod.POST)
    public String updateOrder(@ModelAttribute("order") Order order, BindingResult result,
                              Model model, RedirectAttributes redirectAttributes) {
        Order persistedOrder = orderService.updateOrder(order);
        redirectAttributes.addFlashAttribute("info", "Order updated successfully");
        return "redirect:/orders";
    }
}
