package com.matolaypal.controller;

import com.matolaypal.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 */
@Controller
@Scope("session")
public class MainController {

    @Autowired
    RssService rssService;

    @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView postIndex(@RequestParam("rssfeed")String link) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        try {
            List items = rssService.read(link);
            System.out.println(items);
            modelAndView.addObject("items", items);
            System.out.println(rssService.getChannelTitle());
            modelAndView.addObject("title", rssService.getChannelTitle());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

}
