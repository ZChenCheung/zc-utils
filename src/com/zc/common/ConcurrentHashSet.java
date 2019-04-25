package com.zc.common;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashSet<E> extends AbstractSet<E> implements Set<E>, Serializable {
    
	private static final long serialVersionUID = -3765190241619829426L;

    private final ConcurrentHashMap<E, Object> map;

    private static final Object INNER_VALUE = new Object();
    
    public ConcurrentHashSet() {
        this.map = new ConcurrentHashMap<>();
    }

    public ConcurrentHashSet(int initCapacity) {
        this.map = new ConcurrentHashMap<>(initCapacity);
    }

    @Override
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.map.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        return this.map.put(e, INNER_VALUE) == null;
    }

    @Override
    public boolean remove(Object o) {
        return this.map.remove(o) == INNER_VALUE;
    }

    @Override
    public void clear() {
        this.map.clear();
    }

}