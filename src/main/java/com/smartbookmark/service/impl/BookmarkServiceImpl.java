package com.smartbookmark.service.impl;

import com.smartbookmark.entity.Bookmark;
import com.smartbookmark.entity.User;
import com.smartbookmark.index.BookmarkIndex;
import com.smartbookmark.reposotory.BookmarkRepository;
import com.smartbookmark.service.BookmarkService;

import java.util.List;

/**
 * Created by a.nigam on 29/12/16.
 */
public class BookmarkServiceImpl implements BookmarkService {

    private BookmarkIndex bookmarkIndex;

    private BookmarkRepository bookmarkRepository;

    /**
     * Steps:
     * 1. Persist in DB
     * 2. Queue for indexing
     *
     * @param bookmark
     */
    @Override
    public void addBookmark(Bookmark bookmark) {
        int id = bookmarkRepository.persist(bookmark);
    }

    @Override
    public void updateBookmark(Bookmark bookmark) {

    }

    @Override
    public void deleteBookmark(Bookmark bookmark) {

    }

    @Override
    public List<Bookmark> searchInUserBookmarks(String searchQuery, User user) {
        return bookmarkIndex.search(searchQuery);
    }
}
