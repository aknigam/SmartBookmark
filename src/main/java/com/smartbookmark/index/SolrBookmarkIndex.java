package com.smartbookmark.index;


import com.smartbookmark.entity.UserBookmark;
import com.smartbookmark.exception.BookmarkSearchException;
import com.smartbookmark.repository.SolrRepository;
import com.smartbookmark.service.impl.SearchCriteria;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by a.nigam on 28/01/17.
 */
@Repository
public class SolrBookmarkIndex extends SolrRepository implements BookmarkIndex {

    private static Logger LOGGER = LoggerFactory.getLogger(SolrBookmarkIndex.class);

    @Override
    public List<UserBookmark> searchContent(SearchCriteria searchCriteria) throws BookmarkSearchException {

        // TODO: need to add urls in the query
        QueryResponse rsp = null;
        try {
//            rsp = solr.query(new SolrQuery("id:*"+searchCriteria.getSearchQuery()+"*"));
            rsp = solr.query(new SolrQuery(searchCriteria.getSearchQuery()));
        } catch (SolrServerException| IOException ioe) {
            LOGGER.error(String.format("Unexpected exception occured while searching for content - %s",searchCriteria), ioe );
            throw new BookmarkSearchException(ioe);
        }
        System.out.println(" No. of docs\t "+ rsp.getResults().getNumFound() );

        List<UserBookmark> bookmarks =  new ArrayList<>();

        Iterator<SolrDocument> results = rsp.getResults().iterator();
        while (results.hasNext()){

            SolrDocument solrDocument = results.next();
            System.out.println(solrDocument.getFieldValue("id"));

            Object id = solrDocument.getFieldValue("id");
            bookmarks.add(new UserBookmark(id.toString()));
        }
        return bookmarks;
    }

    @Override
    public void index(String url) {

    }

    @Override
    public void remove(String url) {

    }
}
