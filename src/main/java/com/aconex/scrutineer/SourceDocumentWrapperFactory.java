package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

/**
 * Created by claudio.melendrez on 25/08/16.
 */
public enum SourceDocumentWrapperFactory implements DocumentWrapperFactory {
    INSTANCE;

    @Override
    public SourceDocumentWrapper create(SearchHit hit, String versionField) {
        return new SourceDocumentWrapper(hit, versionField);
    }
}
