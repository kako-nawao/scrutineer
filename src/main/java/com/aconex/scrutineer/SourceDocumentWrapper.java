package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

import java.util.Map;

public class SourceDocumentWrapper extends AbstractDocumentWrapper {

    public static final DocumentWrapperFactory FACTORY = SourceDocumentWrapperFactory.INSTANCE;

    private Map<String, Object> source;
    private String versionField;

    public SourceDocumentWrapper(SearchHit hit, String versionField) {
        super(hit);
        this.versionField = versionField;
        this.source = hit.getSource();
    }

    @Override
    public long getVersion() {
        return (long) Integer.parseInt(source.get(versionField).toString());
    }
}
