package com.knowledgedb.toptagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowledgedb.toptagger.entity.Tweet;


public interface TweetRepository extends JpaRepository<Tweet, Integer> {


}