package com.smartbookmark.entity;

import java.util.List;

/**
 * This class represents a bookmak created by a user.
 */
public class UserBookmark {

    private User user;

    private Folder folder;

    private List<Integer> tagIds;

    private String url;

    public UserBookmark(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public Folder getFolder() {
        return folder;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public String getUrl() {
        return url;
    }
}
