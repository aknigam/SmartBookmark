package com.smartbookmark.service.impl;

import com.smartbookmark.entity.UserBookmark;
import com.smartbookmark.entity.User;
import com.smartbookmark.exception.BookmarkSearchException;
import com.smartbookmark.index.BookmarkIndex;
import com.smartbookmark.repository.BookmarkRepository;
import com.smartbookmark.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by a.nigam on 29/12/16.
 */
@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkIndex bookmarkIndex;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    /**
     * Steps:
     * 1. Persist in DB
     * 2. Queue for indexing
     *
     * @param bookmark
     */
    @Override
    public void addBookmark(UserBookmark bookmark) {
        int id = bookmarkRepository.save(bookmark);

        // below should happen asynchronously

        indexIfRequired(bookmark);
    }

    private void indexIfRequired(UserBookmark bookmark) {
        if(notAlreadyIndexed(bookmark.getUrl())) {
            bookmarkIndex.index(bookmark.getUrl());
        }
    }

    private boolean notAlreadyIndexed(String url) {
        return true;
    }

    @Override
    public void updateBookmark(UserBookmark bookmark) {
        bookmarkRepository.update(bookmark);
        indexIfRequired(bookmark);
    }

    @Override
    public void deleteBookmark(UserBookmark bookmark) {
        bookmarkRepository.delete(bookmark);
        /**
         * Index should be removed only if it not being used in any other user's bookmark
         */
        removeIndexIfRequired(bookmark);

    }

    private void removeIndexIfRequired(UserBookmark bookmark) {
        // TODO: remove index only if this url is not bookmarked by anyone else
        bookmarkIndex.remove(bookmark.getUrl());
    }

    @Override
    public List<UserBookmark> searchInUserBookmarks(SearchCriteria searchCriteria) throws BookmarkSearchException {
        return bookmarkIndex.searchContent(searchCriteria);
    }

    private List<UserBookmark> getUserBookMarks(User user) {
        return Collections.emptyList();
    }
}
