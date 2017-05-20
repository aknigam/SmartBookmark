package com.smartbookmark.index;

import com.smartbookmark.entity.UserBookmark;
import com.smartbookmark.exception.BookmarkSearchException;
import com.smartbookmark.service.impl.SearchCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class use solr index
 */

public interface BookmarkIndex {

    List<UserBookmark> searchContent(SearchCriteria searchQuery) throws BookmarkSearchException;

    void index(String url);

    void remove(String url);
}
