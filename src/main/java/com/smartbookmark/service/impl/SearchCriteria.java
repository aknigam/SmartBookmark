package com.smartbookmark.service.impl;

import com.smartbookmark.entity.Bookmark;

import java.util.List;

/**
 * Created by a.nigam on 28/01/17.
 */
public class SearchCriteria {
    private String searchQuery;
    private List<Bookmark> bookmarks;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
