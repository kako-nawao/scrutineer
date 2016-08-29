package com.aconex.scrutineer;

import org.elasticsearch.search.SearchHit;

public interface DocumentWrapper {

    SearchHit getHit();

    String getId();

    long getVersion();

}
