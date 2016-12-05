package com.aconex.scrutineer;

public interface SearchHitWrapper<T> {

    T getHit();

    String getId();

    long getVersion();

}
