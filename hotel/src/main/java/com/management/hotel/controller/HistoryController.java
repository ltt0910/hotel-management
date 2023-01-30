package com.management.hotel.controller;

import com.management.hotel.model.request.PaymentForm;
import com.management.hotel.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("")
    public String findAll(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page, HttpServletRequest http) throws Exception {

        int currentPage = page;
        Page<PaymentForm> paymentForms;
        paymentForms = transactionService.findAll(PageRequest.of(currentPage - 1, 5));
        model.addAttribute("notifyPage", paymentForms);
        model.addAttribute("currentPage", page);
        model.addAttribute("historys", paymentForms.getContent());
        model.addAttribute("currentURL", http.getRequestURI());
        model.addAttribute("totalPrice", transactionService.getTotal());
        List<PaymentForm> paymentForm = (List<PaymentForm>) transactionService.findAll().getData();
        model.addAttribute("totalUser", paymentForm.size());
        return "history";
    }


}
