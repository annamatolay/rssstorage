package com.matolaypal.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class RssService {

    private String feedUrl;
    private String channelTitle;

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    /**
     * @return items with item(title, link)
     * @throws IllegalAccessException
     */
    public List<List<String>> read() throws IllegalAccessException {
        if (feedUrl==null)
            throw new IllegalAccessException("Feed url is null!");
        List<List<String>> items = new ArrayList<>();
        try {
            URL url = new URL(feedUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));
//            System.out.println(feed.getTitle());
            this.channelTitle = feed.getTitle();
            List<SyndEntry> entries = feed.getEntries();
            if (entries.size() > 0) {
                for (SyndEntry entry : entries) {
                    List<String> item = new ArrayList<>();
                    item.add(entry.getTitle());
                    item.add(entry.getLink());
//                    System.out.println(item);
                    items.add(item);
                }
            }
        }
        catch (Exception e) {
           e.printStackTrace();
        }
//        System.out.println("****\n"+items);
        return items;
    }
}
