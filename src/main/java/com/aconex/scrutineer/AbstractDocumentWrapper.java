package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

public abstract class AbstractDocumentWrapper implements DocumentWrapper {

    protected final SearchHit hit;

    protected AbstractDocumentWrapper(SearchHit hit, String versionField) {
        this.hit = hit;
    }

    @Override
    public String getId() {
        return hit.getId();
    }

    public abstract long getVersion();
}
