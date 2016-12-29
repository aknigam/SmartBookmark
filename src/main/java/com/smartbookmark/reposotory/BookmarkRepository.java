package com.smartbookmark.reposotory;

import com.smartbookmark.entity.Bookmark;

/**
 * Created by a.nigam on 29/12/16.
 */
public interface BookmarkRepository {
    int persist(Bookmark bookmark);
}
