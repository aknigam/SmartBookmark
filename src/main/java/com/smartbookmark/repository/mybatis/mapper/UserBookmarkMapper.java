package com.smartbookmark.repository.mybatis.mapper;

import com.smartbookmark.entity.UserBookmark;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Created by a.nigam on 28/01/17.
 */
public interface UserBookmarkMapper {


    @Insert("INSERT INTO UserBookmark(UserBookmarkId,Url,FolderId)VALUES(    <{UserBookmarkId: }>,<{Url: }>,<{FolderId: }>);")
    void insertUserBookmark(UserBookmark bookmark);

}
