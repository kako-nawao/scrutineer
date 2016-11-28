package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;


public class SourceDocumentWrapper extends AbstractDocumentWrapper {

    public static final DocumentWrapperFactory FACTORY = SourceDocumentWrapperFactory.INSTANCE;

    private String versionField;

    public SourceDocumentWrapper(SearchHit hit, String versionField) {
        super(hit);
        this.versionField = versionField;
    }

    @Override
    public long getVersion() {
        return (long) Integer.parseInt(getHit().getFields().get(versionField).getValue().toString());
    }
}
