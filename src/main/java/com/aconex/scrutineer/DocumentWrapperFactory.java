package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

/**
 * Created by claudio.melendrez on 25/08/16.
 */
public interface DocumentWrapperFactory {
    DocumentWrapper create(SearchHit hit, String versionField);
}