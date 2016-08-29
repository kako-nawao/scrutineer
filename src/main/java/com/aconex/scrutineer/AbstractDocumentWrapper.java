package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

public abstract class AbstractDocumentWrapper implements DocumentWrapper {

    private final SearchHit hit;

    protected AbstractDocumentWrapper(SearchHit hit) {
        this.hit = hit;
    }

    @Override
    public SearchHit getHit() {
        return hit;
    }

    @Override
    public String getId() {
        return getHit().getId();
    }

    public abstract long getVersion();
}
