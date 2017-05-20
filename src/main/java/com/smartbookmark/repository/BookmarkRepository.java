package com.smartbookmark.repository;

import com.smartbookmark.entity.UserBookmark;
import org.springframework.stereotype.Repository;

/**
 * Created by a.nigam on 29/12/16.
 */
@Repository
public interface BookmarkRepository {
    int save(UserBookmark bookmark);

    void update(UserBookmark bookmark);

    void delete(UserBookmark bookmark);
}
