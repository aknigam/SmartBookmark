package com.smartbookmark.reposotory;

import com.smartbookmark.entity.Bookmark;

/**
 * Created by a.nigam on 29/12/16.
 */
public interface BookmarkRepository {
    int save(Bookmark bookmark);

    void update(Bookmark bookmark);

    void delete(Bookmark bookmark);
}
