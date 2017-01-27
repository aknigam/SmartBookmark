package com.smartbookmark;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.ContentStreamBase;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.handler.extraction.ExtractingParams;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

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
        System.out.println("No of docs after 30\t"+docs.size());

        sco.indexFromUrl();

    }

    private void indexFromUrl() throws IOException, SolrServerException {

        ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/extract");
        up.addContentStream(new ContentStreamBase.URLStream(new URL("http://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english/9513423#9513423")));
        up.setParam("literal.id", "stackoverflow3");
        up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
//        NamedList<Object> result = solr.request(up);

        solr.deleteById("stackoverflow1");
        solr.deleteById("stackoverflow2");
        solr.deleteById("stackoverflow3");

        solr.commit();

        QueryResponse rsp = solr.query(new SolrQuery("content:*Basic principle*"));
        System.out.println(" No. of docs\t "+ rsp.getResults().getNumFound() );

        for (int i = 0; i < rsp.getResults().getNumFound(); i++) {
            SolrDocument result = rsp.getResults().get(i);
            System.out.println(result.getFieldValue("id"));
        }

    }



}
