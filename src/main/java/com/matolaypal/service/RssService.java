package com.matolaypal.service;

import com.matolaypal.model.RssFeed;
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

    /**
     * @return items with item(title, link)
     * @throws IllegalAccessException
     */
    public RssFeed read(String feedUrl) throws IllegalAccessException {
        RssFeed rssFeed = new RssFeed();
        List<List<String>> items = new ArrayList<>();
        try {
            URL url = new URL(feedUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));
            rssFeed.setTitle(feed.getTitle());
            List<SyndEntry> entries = feed.getEntries();
            if (entries.size() < 1) {
                return null;
            }
            for (SyndEntry entry : entries) {
                List<String> item = new ArrayList<>();
                item.add(entry.getTitle());
                item.add(entry.getLink());
                items.add(item);
            }
            rssFeed.setItems(items);
        }
        catch (Exception e) {
           e.printStackTrace();
        }
        return rssFeed;
    }
}
