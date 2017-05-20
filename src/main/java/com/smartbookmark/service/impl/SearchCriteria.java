package com.smartbookmark.service.impl;

import com.smartbookmark.entity.UserBookmark;

import java.util.List;

/**
 * Created by a.nigam on 28/01/17.
 */
public class SearchCriteria {
    private String searchQuery;
    private List<UserBookmark> bookmarks;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void setBookmarks(List<UserBookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
