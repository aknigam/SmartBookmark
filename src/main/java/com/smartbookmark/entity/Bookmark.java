package com.smartbookmark.entity;

import java.util.List;

/**
 * This class represents a bookmak created by a user.
 */
public class Bookmark {

    private User user;

    private Folder folder;

    private List<Integer> tagIds;

    private String url;

}
