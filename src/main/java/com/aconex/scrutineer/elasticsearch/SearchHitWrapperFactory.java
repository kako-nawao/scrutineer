package com.aconex.scrutineer.elasticsearch;

import com.aconex.scrutineer.SearchHitWrapper;

import org.elasticsearch.search.SearchHit;

public interface SearchHitWrapperFactory {
    SearchHitWrapper create(SearchHit hit, String versionField);
}
