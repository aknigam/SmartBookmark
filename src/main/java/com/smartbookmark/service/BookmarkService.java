package com.smartbookmark.service;

import com.smartbookmark.entity.UserBookmark;
import com.smartbookmark.exception.BookmarkSearchException;
import com.smartbookmark.service.impl.SearchCriteria;

import java.util.List;


/**
 * Created by a.nigam on 29/12/16.
 */
public interface BookmarkService {

    void addBookmark(UserBookmark bookmark);

    void updateBookmark(UserBookmark bookmark);

    void deleteBookmark(UserBookmark bookmark);

    List<UserBookmark> searchInUserBookmarks(SearchCriteria searchQuery) throws BookmarkSearchException ;

}
