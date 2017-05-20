package com.smartbookmark.repository;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * Created by a.nigam on 28/01/17.
 */
public class SolrRepository {

    private String urlString = "http://localhost:8983/solr/smartbookmark";
    protected SolrClient solr;

    public SolrRepository(){
        solr  = new HttpSolrClient(urlString);
    }

}
