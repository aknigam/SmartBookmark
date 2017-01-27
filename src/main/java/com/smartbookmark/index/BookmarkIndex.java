package com.smartbookmark.index;

import com.smartbookmark.entity.Bookmark;

import java.util.List;

/**
 * This class use solr index
 */
public interface BookmarkIndex {

    List<Bookmark> search(String searchQuery);

    void index(String url);

    void remove(String url);
}
