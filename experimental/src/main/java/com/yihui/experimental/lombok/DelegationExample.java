package com.yihui.experimental.lombok;

import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.Collection;

public class DelegationExample {
    private interface SimpleCollection {
        boolean add(String item);
        boolean remove(Object item);
    }

    @Delegate(types=SimpleCollection.class)
    private final Collection<String> collection = new ArrayList<String>();

    public void main() {
        collection.add("abc");
    }
}

