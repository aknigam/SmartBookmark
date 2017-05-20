package com.smartbookmark.repository;

import com.smartbookmark.entity.UserBookmark;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Created by a.nigam on 28/01/17.
 */
@Repository
public class SolrUserBookmarkRepository extends SolrRepository implements BookmarkRepository {
    @Override
    public int save(UserBookmark bookmark) {


        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "552199");
        document.addField("username", bookmark.getUser().getUsername());
        document.addField("folder", "49.99");
        try {
            UpdateResponse response = solr.add(document);
            solr.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return -1;

    }

    @Override
    public void update(UserBookmark bookmark) {

    }

    @Override
    public void delete(UserBookmark bookmark) {

    }
}
