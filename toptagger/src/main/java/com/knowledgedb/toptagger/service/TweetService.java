package com.knowledgedb.toptagger.service;

import com.knowledgedb.toptagger.entity.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TweetService {


    public List<Tweet> findAll();

    public ArrayList<String> findTopHashTags();

    public Tweet findById(int id);

    public void updateTweet(Tweet tweet);

    public void addTweet( String description);

    public void deleteById(int id); 

    public void save(Tweet tweet);

}
