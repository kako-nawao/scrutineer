package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

public enum MetaDocumentWrapperFactory implements DocumentWrapperFactory {
    INSTANCE;

    @Override
    public MetaDocumentWrapper create(SearchHit hit, String versionField) {
        return new MetaDocumentWrapper(hit);
    }
}
