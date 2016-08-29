package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

public enum SourceDocumentWrapperFactory implements DocumentWrapperFactory {
    INSTANCE;

    @Override
    public SourceDocumentWrapper create(SearchHit hit, String versionField) {
        return new SourceDocumentWrapper(hit, versionField);
    }
}
