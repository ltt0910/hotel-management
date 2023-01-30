package com.management.hotel.controller;

import com.management.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/lists")
    public ModelAndView findAll(
            @RequestParam(name = "keyword", required = false) String keyword, HttpServletRequest httpRequest) throws Exception {
        ModelAndView mav = new ModelAndView("index.html");
        if (keyword == null) {
            mav.addObject("listOneRoom", roomService.findAll(1, keyword).getData());
            mav.addObject("listTwoRoom", roomService.findAll(2, keyword).getData());
        } else {
            mav.addObject("listsSearch", roomService.findAll(0, keyword).getData());
            mav.addObject("keySearch", keyword);
        }
        mav.addObject("url", httpRequest.getRequestURI());
        return mav;
    }

    @GetMapping("/list_empty")
    public ModelAndView findRoomEmpty(
            @RequestParam(name = "keyword", required = false) String keyword, HttpServletRequest httpRequest) throws Exception {
        ModelAndView mav = new ModelAndView("index.html");
        if (keyword == null) {
            mav.addObject("listOneRoom", roomService.getEmptyRoom(1, keyword).getData());
            mav.addObject("listTwoRoom", roomService.getEmptyRoom(2, keyword).getData());
        } else {
            mav.addObject("listsSearch", roomService.getEmptyRoom(0, keyword).getData());
            mav.addObject("keySearch", keyword);
        }
        mav.addObject("url", httpRequest.getRequestURI());
        return mav;
    }

    @GetMapping("/list_rented")
    public ModelAndView findRoomRented(
            @RequestParam(name = "keyword", required = false) String keyword, HttpServletRequest httpServletRequest) throws Exception {
        ModelAndView mav = new ModelAndView("index.html");
        if (keyword == null) {
            mav.addObject("listOneRoom", roomService.getRentedRoom(1, keyword).getData());
            mav.addObject("listTwoRoom", roomService.getRentedRoom(2, keyword).getData());
        } else {
            mav.addObject("listsSearch", roomService.getRentedRoom(0, keyword).getData());
            mav.addObject("keySearch", keyword);
        }
        mav.addObject("url", httpServletRequest.getRequestURI());
        return mav;
    }


}
