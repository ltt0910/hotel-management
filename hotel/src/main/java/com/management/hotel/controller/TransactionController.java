package com.management.hotel.controller;

import com.management.hotel.service.RoomService;
import com.management.hotel.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/thanhtoan/{code}")
    public ModelAndView create(@PathVariable Long code) throws Exception {
        ModelAndView mav = new ModelAndView("thanh-toan.html");
        mav.addObject("transaction", transactionService.getByCode(code).getData());
        mav.addObject("ma_phong", code);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        mav.addObject("current_time", LocalDateTime.now().format(formatter));
        mav.addObject("price_per_day", roomService.getRoomByCode(code).getData());

        return mav;
    }
}
