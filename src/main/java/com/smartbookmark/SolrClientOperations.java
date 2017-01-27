package com.smartbookmark;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

/**
 * Created by a.nigam on 27/01/17.
 */
public class SolrClientOperations {

    private String urlString = "http://localhost:8983/solr/smartbookmark";
    private SolrClient solr;

    public SolrClientOperations(){
        solr  = new HttpSolrClient(urlString);
    }

    private SolrDocumentList query(String queryString) throws IOException, SolrServerException {

        SolrQuery query = new SolrQuery();
        query.setQuery(queryString);
        System.out.println(query.getRequestHandler());
        query.setStart(30);
        return solr.query(query).getResults();
    }


    public static void main(String[] args) throws IOException, SolrServerException {
        SolrClientOperations sco =  new SolrClientOperations();
        String queryString =  "*:*";

        SolrDocumentList docs =  sco.query(queryString);
        System.out.println(docs.size());
    }



}
