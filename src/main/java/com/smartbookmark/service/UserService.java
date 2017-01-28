package com.smartbookmark.service;

import com.smartbookmark.entity.Bookmark;
import com.smartbookmark.entity.User;

import java.util.List;

/**
 * Created by a.nigam on 28/01/17.
 */
public interface UserService {
    List<Bookmark> getUserBookMarks(User user) ;
}
