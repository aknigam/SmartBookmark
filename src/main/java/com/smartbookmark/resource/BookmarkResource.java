package com.smartbookmark.resource;

import com.smartbookmark.entity.Bookmark;
import com.smartbookmark.entity.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by a.nigam on 27/01/17.
 */
@RestController
public class BookmarkResource {

    @PostMapping
    void addBookmark(Bookmark bookmark){}

    @PostMapping
    void updateBookmark(Bookmark bookmark){}

    @DeleteMapping
    void deleteBookmark(Bookmark bookmark){}

    @GetMapping
    List<Bookmark> searchInUserBookmarks(String searchQuery, User user){
        return null;
    }
}
