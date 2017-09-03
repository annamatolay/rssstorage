package com.matolaypal.controller;

import com.matolaypal.model.RssFeed;
import com.matolaypal.repository.RssFeedRepository;
import com.matolaypal.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
@Scope("session")
public class MainController {

    @Autowired
    RssService rssService;

    private RssFeedRepository rssFeedRepository = new RssFeedRepository();

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView postIndex(@RequestParam(name = "rssfeed", required = false)String link) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        try {
            System.out.println("ADD: "+rssService.read(link).getItems());
            RssFeed feed = rssService.read(link);
            if(feed.getItems()!=null)
                rssFeedRepository.add(feed);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("GET ALL: "+rssFeedRepository.getAll());
        modelAndView.addObject("feeds", rssFeedRepository.getAll());
        return modelAndView;
    }

}
