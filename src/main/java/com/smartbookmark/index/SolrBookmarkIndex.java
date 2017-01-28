package com.smartbookmark.index;


import com.smartbookmark.entity.Bookmark;
import com.smartbookmark.exception.BookmarkSearchException;
import com.smartbookmark.service.impl.SearchCriteria;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by a.nigam on 28/01/17.
 */
public class SolrBookmarkIndex implements BookmarkIndex {

    private static Logger LOGGER = LoggerFactory.getLogger(SolrBookmarkIndex.class);

    private String urlString = "http://localhost:8983/solr/smartbookmark";
    private SolrClient solr;

    public SolrBookmarkIndex(){
        solr  = new HttpSolrClient(urlString);
    }

    @Override
    public List<Bookmark> searchContent(SearchCriteria searchCriteria) throws BookmarkSearchException {

        // TODO: need to add urls in the query
        QueryResponse rsp = null;
        try {
            rsp = solr.query(new SolrQuery("content:*"+searchCriteria.getSearchQuery()+"*"));
        } catch (SolrServerException| IOException ioe) {
            LOGGER.error(String.format("Unexpected exception occured while searching for content - %s",searchCriteria), ioe );
            throw new BookmarkSearchException(ioe);
        }
        System.out.println(" No. of docs\t "+ rsp.getResults().getNumFound() );

        for (int i = 0; i < rsp.getResults().getNumFound(); i++) {
            SolrDocument solrDocument = rsp.getResults().get(i);
            System.out.println(solrDocument.getFieldValue("id"));

            solrDocument.getFieldValue("url");
        }
        return null;
    }

    @Override
    public void index(String url) {

    }

    @Override
    public void remove(String url) {

    }
}
