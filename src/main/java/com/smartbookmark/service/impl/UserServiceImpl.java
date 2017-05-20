package com.smartbookmark.service.impl;

import com.smartbookmark.entity.User;
import com.smartbookmark.entity.UserBookmark;
import com.smartbookmark.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by a.nigam on 19/05/17.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserBookmark> getUserBookMarks(User user) {
        return null;
    }
}
