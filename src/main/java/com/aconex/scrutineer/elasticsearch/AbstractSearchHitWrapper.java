package com.aconex.scrutineer.elasticsearch;

import com.aconex.scrutineer.SearchHitWrapper;

import org.elasticsearch.search.SearchHit;

public abstract class AbstractSearchHitWrapper implements SearchHitWrapper {

    private final SearchHit hit;

    protected AbstractSearchHitWrapper(SearchHit hit) {
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
