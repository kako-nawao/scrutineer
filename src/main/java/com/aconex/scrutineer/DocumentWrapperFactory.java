package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

public interface DocumentWrapperFactory {
    DocumentWrapper create(SearchHit hit, String versionField);
}
