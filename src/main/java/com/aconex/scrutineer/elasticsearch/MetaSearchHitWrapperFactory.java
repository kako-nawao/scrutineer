package com.aconex.scrutineer.elasticsearch;

import org.elasticsearch.search.SearchHit;

public enum MetaSearchHitWrapperFactory implements SearchHitWrapperFactory {
    INSTANCE;

    @Override
    public MetaSearchHitWrapper create(SearchHit hit, String versionField) {
        return new MetaSearchHitWrapper(hit);
    }
}
