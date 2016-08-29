package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

/**
 * Created by claudio.melendrez on 25/08/16.
 */
public enum MetaDocumentWrapperFactory implements DocumentWrapperFactory {
    INSTANCE;

    @Override
    public MetaDocumentWrapper create(SearchHit hit, String versionField) {
        return new MetaDocumentWrapper(hit, versionField);
    }
}
