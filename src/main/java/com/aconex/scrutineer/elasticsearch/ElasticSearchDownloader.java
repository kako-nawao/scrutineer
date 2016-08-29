package com.aconex.scrutineer.elasticsearch;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.aconex.scrutineer.*;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;

public class ElasticSearchDownloader {

    private static final Logger LOG = LogUtils.loggerForThisClass();

    static final int BATCH_SIZE = 100000;
    static final int SCROLL_TIME_IN_MINUTES = 10;
    private long numItems = 0;

    private final Client client;
    private final String indexName;
    private final String query;
    private final String versionField;
    private final boolean skipFields;
    private final IdAndVersionFactory idAndVersionFactory;
    private final DocumentWrapperFactory documentWrapperFactory;


    public ElasticSearchDownloader(Client client, String indexName, String query, String versionField, IdAndVersionFactory idAndVersionFactory, DocumentWrapperFactory documentWrapperFactory) {
        this.client = client;
        this.indexName = indexName;
        this.query = query;
        this.versionField = versionField;
        this.skipFields = versionField.isEmpty();
        this.idAndVersionFactory = idAndVersionFactory;
        this.documentWrapperFactory = documentWrapperFactory;
    }

    public void downloadTo(OutputStream outputStream) {
        long begin = System.currentTimeMillis();
        doDownloadTo(outputStream);
        LogUtils.infoTimeTaken(LOG, begin, numItems, "Scan & Download completed");
    }

    private void doDownloadTo(OutputStream outputStream) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            consumeBatches(objectOutputStream, startScroll().getScrollId());
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void consumeBatches(ObjectOutputStream objectOutputStream, String initialScrollId) throws IOException {

        String scrollId = initialScrollId;
        SearchResponse batchSearchResponse;
        do {
            batchSearchResponse = client.prepareSearchScroll(scrollId).setScroll(TimeValue.timeValueMinutes(SCROLL_TIME_IN_MINUTES)).execute().actionGet();
            scrollId = batchSearchResponse.getScrollId();
        } while (writeSearchResponseToOutputStream(objectOutputStream, batchSearchResponse));
    }

    boolean writeSearchResponseToOutputStream(ObjectOutputStream objectOutputStream, SearchResponse searchResponse) throws IOException {
        SearchHit[] hits = searchResponse.getHits().hits();
        for (SearchHit hit : hits) {
            DocumentWrapper docWrapper = documentWrapperFactory.create(hit, versionField);
        	idAndVersionFactory.create(docWrapper.getId(), docWrapper.getVersion()).writeToStream(objectOutputStream);
            numItems++;
        }
        return hits.length > 0;
    }

    QueryStringQueryBuilder createQuery() {
        return QueryBuilders.queryString(query).defaultOperator(QueryStringQueryBuilder.Operator.AND).defaultField("_all");
    }

    @SuppressWarnings("PMD.NcssMethodCount")
    SearchResponse startScroll() {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexName);
        searchRequestBuilder.setSearchType(SearchType.SCAN);
        searchRequestBuilder.setQuery(createQuery());
        searchRequestBuilder.setSize(BATCH_SIZE);
        if (skipFields) {
            searchRequestBuilder.setNoFields();
        }
        searchRequestBuilder.setExplain(false);
        searchRequestBuilder.setVersion(true);
        searchRequestBuilder.setScroll(TimeValue.timeValueMinutes(SCROLL_TIME_IN_MINUTES));

        return searchRequestBuilder.execute().actionGet();
    }

}
