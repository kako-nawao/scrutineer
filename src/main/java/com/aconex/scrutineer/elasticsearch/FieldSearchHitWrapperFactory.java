package com.aconex.scrutineer.elasticsearch;

import org.elasticsearch.search.SearchHit;

public enum FieldSearchHitWrapperFactory implements SearchHitWrapperFactory {
    INSTANCE;

    @Override
    public FieldSearchHitWrapper create(SearchHit hit, String versionField) {
        return new FieldSearchHitWrapper(hit, versionField);
    }
}
