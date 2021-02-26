package com.knowledgedb.toptagger.service;

import com.knowledgedb.toptagger.dao.TweetRepository;
import com.knowledgedb.toptagger.entity.Tweet;
import com.knowledgedb.toptagger.security.CustomAuthenticationSuccessHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {

  private TweetRepository tweetRepository;

  @Autowired
  private UserService userService;

  @Autowired
  public TweetServiceImpl(TweetRepository theTweetRepository) {
    this.tweetRepository = theTweetRepository;
  }

  @Override
  public List<Tweet> findAll() {
    return tweetRepository.findAll();
  }

  @Override
  public Tweet findById(int id) {
    Optional<Tweet> result = tweetRepository.findById(id);

    Tweet theTweet = null;
    if (result.isPresent()) theTweet =
      result.get(); else throw new RuntimeException(
      "Tweet's with id: " + id + " does not exists."
    );
    return theTweet;
  }

  @Override
  public void updateTweet(Tweet tweet) {
    tweetRepository.save(tweet);
  }

  @Override
  public ArrayList<String> findTopHashTags() {
    List<Tweet> data = tweetRepository.findAll();

    Tweet[] tweets = data.toArray(new Tweet[0]);

    ArrayList<String> tweetList = new ArrayList<String>();
    String fieldValue = null;
    StringBuilder sb = new StringBuilder();
    for (Tweet i : tweets) {
      String val = String.valueOf(i);
      fieldValue = val.replaceFirst(".*'(.*?)'.*", "$1");
      tweetList.add(fieldValue);
      sb.append(fieldValue);
      sb.append(" ");
    }
    String tweetresult = String.valueOf(sb);
    ArrayList<String> top_hashtags = new ArrayList<String>();
    Pattern pattern = Pattern.compile("(#\\w+)");
    Matcher matcher;

    LinkedHashMap<String, Integer> tagMap = new LinkedHashMap<String, Integer>();

    String total_tweets = tweetresult;
    matcher = pattern.matcher(total_tweets);
    while (matcher.find()) {
      if (null != tagMap.get(matcher.group())) {
        tagMap.put(matcher.group(), (tagMap.get(matcher.group()) + 1));
      } else {
        tagMap.put(matcher.group(), new Integer(1));
      }
    }

    List<Map.Entry<String, Integer>> tag_entries = new ArrayList<Map.Entry<String, Integer>>(
      tagMap.entrySet()
    );
    Collections.sort(tag_entries, new Comparator<Map.Entry<String, Integer>>() {
        public int compare(
          Map.Entry<String, Integer> val1,
          Map.Entry<String, Integer> val2
        ) {
          if (val2.getValue().equals(val1.getValue())) return val1
            .getKey()
            .compareTo(val2.getKey()); else return val2
            .getValue()
            .compareTo(val1.getValue());
        }
      }
    );

    Map<String, Integer> tags_sorted = new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> entry : tag_entries) {
      tags_sorted.put(entry.getKey(), entry.getValue());
    }
    Iterator it = tags_sorted.entrySet().iterator();
    int top = 1;
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      top_hashtags.add(
        top +
        ". " +
        (String) pair.getKey() +
        " " +
        "-" +
        " " +
        pair.getValue()+" "+"times"
      );
      it.remove();
      if (top == 10) break;
      top++;
    }
    return top_hashtags;
  }

  @Override
  public void addTweet(String description) {
    String userName = null;

    Authentication authentication = SecurityContextHolder
      .getContext()
      .getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      userName = authentication.getName();
    }
    tweetRepository.save(new Tweet(userName, description));
  }

  @Override
  public void deleteById(int id) {
    tweetRepository.deleteById(id);
  }

  @Override
  public void save(Tweet tweet) {
    tweetRepository.save(tweet);
  }
}
