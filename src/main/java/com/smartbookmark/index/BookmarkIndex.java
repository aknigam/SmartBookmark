package com.smartbookmark.index;

import com.smartbookmark.entity.Bookmark;
import com.smartbookmark.exception.BookmarkSearchException;
import com.smartbookmark.service.impl.SearchCriteria;

import java.util.List;

/**
 * This class use solr index
 */
public interface BookmarkIndex {

    List<Bookmark> searchContent(SearchCriteria searchQuery) throws BookmarkSearchException;

    void index(String url);

    void remove(String url);
}
