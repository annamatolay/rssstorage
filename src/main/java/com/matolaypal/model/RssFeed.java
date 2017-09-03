package com.matolaypal.model;

import java.util.List;

/**
 *
 */
public class RssFeed {
    private String title;
    private List<List<String>> items;

    public RssFeed() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<List<String>> getItems() {
        return items;
    }

    public void setItems(List<List<String>> items) {
        this.items = items;
    }
}
