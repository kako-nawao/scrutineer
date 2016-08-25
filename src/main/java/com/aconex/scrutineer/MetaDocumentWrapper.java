package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

public class MetaDocumentWrapper extends AbstractDocumentWrapper {

    public static final DocumentWrapperFactory FACTORY = MetaDocumentWrapperFactory.INSTANCE;

    public MetaDocumentWrapper(SearchHit hit, String versionField) {
        super(hit, versionField);
    }

    @Override
    public long getVersion() {
        return hit.getVersion();
    }
}
