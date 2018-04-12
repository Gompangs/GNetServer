package com.gompang.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TypedMap implements Map<String, Object> {
    private Map<String, Object> delegate;

    public TypedMap(Map<String, Object> delegate) {
        this.delegate = delegate;
    }

    public TypedMap() {
        this.delegate = new HashMap<String, Object>();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(TypedMapKey<T> key) {
        return (T) delegate.get(key.name());
    }

    @SuppressWarnings("unchecked")
    public <T> T remove(TypedMapKey<T> key) {
        return (T) delegate.remove(key.name());
    }

    public <T> void put(TypedMapKey<T> key, T value) {
        delegate.put(key.name(), value);
    }

    // --- Only calls to delegates below

    public void clear() {
        delegate.clear();
    }

    public boolean containsKey(Object key) {
        return delegate.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return delegate.containsValue(value);
    }

    public Set<Entry<String, Object>> entrySet() {
        return delegate.entrySet();
    }

    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    public Object get(Object key) {
        return delegate.get(key);
    }

    public int hashCode() {
        return delegate.hashCode();
    }

    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    public Set<String> keySet() {
        return delegate.keySet();
    }

    public Object put(String key, Object value) {
        return delegate.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends Object> m) {
        delegate.putAll(m);
    }

    public Object remove(Object key) {
        return delegate.remove(key);
    }

    public int size() {
        return delegate.size();
    }

    public Collection<Object> values() {
        return delegate.values();
    }
}