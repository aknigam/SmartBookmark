package com.smartbookmark.service.impl;

import com.smartbookmark.entity.Bookmark;
import com.smartbookmark.entity.User;
import com.smartbookmark.exception.BookmarkSearchException;
import com.smartbookmark.index.BookmarkIndex;
import com.smartbookmark.reposotory.BookmarkRepository;
import com.smartbookmark.service.BookmarkService;

import java.util.ArrayList;
import java.util.Collections;
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
        int id = bookmarkRepository.save(bookmark);

        // below should happen asynchronously

        indexIfRequired(bookmark);
    }

    private void indexIfRequired(Bookmark bookmark) {
        if(notAlreadyIndexed(bookmark.getUrl())) {
            bookmarkIndex.index(bookmark.getUrl());
        }
    }

    private boolean notAlreadyIndexed(String url) {
        return true;
    }

    @Override
    public void updateBookmark(Bookmark bookmark) {
        bookmarkRepository.update(bookmark);
        indexIfRequired(bookmark);
    }

    @Override
    public void deleteBookmark(Bookmark bookmark) {
        bookmarkRepository.delete(bookmark);
        /**
         * Index should be removed only if it not being used in any other user's bookmark
         */
        removeIndexIfRequired(bookmark);

    }

    private void removeIndexIfRequired(Bookmark bookmark) {
        // remove index only if this url is not bookmarked by anyone else
        bookmarkIndex.remove(bookmark.getUrl());
    }

    @Override
    public List<Bookmark> searchInUserBookmarks(SearchCriteria searchCriteria) throws BookmarkSearchException {

        return bookmarkIndex.searchContent(searchCriteria);
    }

    private List<Bookmark> getUserBookMarks(User user) {
        return Collections.emptyList();
    }
}
