package com.matolaypal.repository;

import com.matolaypal.model.RssFeed;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RssFeedRepository {
    private List<RssFeed> userFeeds = new ArrayList<>();

    public void add(RssFeed feed){
        this.userFeeds.add(feed);
    }

    public List<RssFeed> getAll() {
        return userFeeds;
    }
}
