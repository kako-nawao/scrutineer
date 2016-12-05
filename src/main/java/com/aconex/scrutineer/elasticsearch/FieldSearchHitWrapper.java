package com.aconex.scrutineer.elasticsearch;

import org.elasticsearch.search.SearchHit;

public class FieldSearchHitWrapper extends AbstractSearchHitWrapper {

    public static final SearchHitWrapperFactory FACTORY = FieldSearchHitWrapperFactory.INSTANCE;

    private String versionField;

    public FieldSearchHitWrapper(SearchHit hit, String versionField) {
        super(hit);
        this.versionField = versionField;
    }

    @Override
    public long getVersion() {
        return (long) Integer.parseInt(getHit().getFields().get(versionField).getValue().toString());
    }
}
