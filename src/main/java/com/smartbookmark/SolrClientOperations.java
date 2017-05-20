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
import org.apache.solr.common.util.SimpleOrderedMap;


import java.io.IOException;
import java.net.URL;

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

        /**
         *
         * Look at the config of ExtractionHandler (/update/extract). The content field is mapped to _text_.
         *
         * So if someone wants to search the content of an indexed page then the query should look like _text_:*query*
         *  <requestHandler name="/update/extract"
             startup="lazy"
             class="solr.extraction.ExtractingRequestHandler" >
             <lst name="defaults">
             <str name="lowernames">true</str>
             <str name="fmap.meta">ignored_</str>
             <str name="fmap.content">_text_</str>
             </lst>
         </requestHandler>
         *
         *
         */
        ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/extract");
        String url = "https://www.toptal.com/java/the-trie-a-neglected-data-structure";
        up.addContentStream(new ContentStreamBase.URLStream(new URL(url)));
        up.setParam("literal.id", "toptal");
        up.setParam("literal.url", url);


        up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
        NamedList<Object> result = solr.request(up);
        Integer status = (Integer) ((SimpleOrderedMap) result.get("responseHeader")).get("status");
//        solr.deleteById("stackoverflow1");
//        solr.deleteById("stackoverflow2");
//        solr.deleteById("stackoverflow3");

        solr.commit();
        SolrQuery sorlQuery = new SolrQuery("twitter_title:*solr* AND (id:stackoverflow7 OR id:stackoverflow6)");

        QueryResponse rsp = solr.query(sorlQuery);
        int noOfResults = rsp.getResults().size();
        Object urlField = rsp.getResults().get(0).getFieldValue("url");
        System.out.println(urlField);

        rsp = solr.query(new SolrQuery("content:*Basic principle*"));
        System.out.println(" No. of docs\t "+ rsp.getResults().getNumFound() );

        for (int i = 0; i < rsp.getResults().getNumFound(); i++) {
            SolrDocument solrDocument = rsp.getResults().get(i);
            System.out.println(solrDocument.getFieldValue("id"));
        }

    }



}
