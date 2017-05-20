package com.smartbookmark.resource;

import com.smartbookmark.entity.UserBookmark;
import com.smartbookmark.entity.User;
import com.smartbookmark.exception.BookmarkSearchException;
import com.smartbookmark.service.BookmarkService;
import com.smartbookmark.service.UserService;
import com.smartbookmark.service.impl.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by a.nigam on 27/01/17.
 */
@RestController
@RequestMapping("/api/bookmark")
public class BookmarkResource {

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private UserService userService;

    @PostMapping
    public void addBookmark(UserBookmark bookmark){
        bookmarkService.addBookmark(bookmark);
    }

    @PutMapping
    void updateBookmark(UserBookmark bookmark){
        bookmarkService.updateBookmark(bookmark);
    }

    @DeleteMapping
    void deleteBookmark(UserBookmark bookmark){
        bookmarkService.deleteBookmark(bookmark);
    }

    @GetMapping(value = "/search")
    List<UserBookmark> searchInUserBookmarks(@RequestParam String searchQuery, User user) throws BookmarkSearchException {

        SearchCriteria criteria = new SearchCriteria();
        criteria.setSearchQuery(searchQuery);

        List<UserBookmark> bookmarks = userService.getUserBookMarks(user);

        criteria.setBookmarks(bookmarks);

        bookmarks = bookmarkService.searchInUserBookmarks(criteria);

        return bookmarks;
    }
}
