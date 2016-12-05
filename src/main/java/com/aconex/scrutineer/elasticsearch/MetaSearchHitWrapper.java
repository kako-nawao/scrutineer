package com.aconex.scrutineer.elasticsearch;

import org.elasticsearch.search.SearchHit;

public class MetaSearchHitWrapper extends AbstractSearchHitWrapper {

    public static final SearchHitWrapperFactory FACTORY = MetaSearchHitWrapperFactory.INSTANCE;

    public MetaSearchHitWrapper(SearchHit hit) {
        super(hit);
    }

    @Override
    public long getVersion() {
        return getHit().getVersion();
    }
}
