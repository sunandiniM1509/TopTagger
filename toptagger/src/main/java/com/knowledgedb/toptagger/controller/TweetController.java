package com.knowledgedb.toptagger.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.knowledgedb.toptagger.entity.Tweet;
import com.knowledgedb.toptagger.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/tweets")
public class TweetController {

    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/list")
    public String listTweets(Model theModel) {

        List<Tweet> theTweets = tweetService.findAll();

        theModel.addAttribute("tweets",theTweets);

        return "tweet-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Tweet theTweet= new Tweet();

        theModel.addAttribute("tweet",theTweet);

        return "tweet-form";
    }

    @GetMapping("/topHashtags")
    public String topHashtags(Model theModel) {

        ArrayList<String> hashtags = tweetService.findTopHashTags();

        theModel.addAttribute("tweet",hashtags);
        return "tophashtags";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

    @PostMapping("/save")
    public String saveTweet(@Valid @ModelAttribute("tweet") Tweet theTweet, BindingResult bindingResult, HttpServletRequest request) {
        if(bindingResult.hasErrors())
            return "tweet-form";
            String name = currentUserNameSimple(request);
             theTweet.setName(name);
        tweetService.save(theTweet); 

        return "redirect:/tweets/list";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("tweetId") int theId) {

        tweetService.deleteById(theId);

        return "redirect:/tweets/list";

    }


}
