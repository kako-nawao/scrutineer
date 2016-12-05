package com.aconex.scrutineer.elasticsearch;

import org.elasticsearch.search.SearchHit;

public enum SourceSearchHitWrapperFactory implements SearchHitWrapperFactory {
    INSTANCE;

    @Override
    public SourceSearchHitWrapper create(SearchHit hit, String versionField) {
        return new SourceSearchHitWrapper(hit, versionField);
    }
}
