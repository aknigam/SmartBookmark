package com.smartbookmark.service;

import com.smartbookmark.entity.Bookmark;
import com.smartbookmark.entity.User;

import java.util.List;


/**
 * Created by a.nigam on 29/12/16.
 */
public interface BookmarkService {

    void addBookmark(Bookmark bookmark);

    void updateBookmark(Bookmark bookmark);

    void deleteBookmark(Bookmark bookmark);

    List<Bookmark> searchInUserBookmarks(String searchQuery, User user);

}
