package com.edu;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by iivaniv on 07.09.2016.
 */
public class HashMapImpl {

    private static final Logger log = LoggerFactory.getLogger(HashMapImpl.class);

    private final static int INITIAL_SIZE = 100;
    private int size = 0;
    private double loadingFactor = 0.75;


    private Entry entries[];

    public HashMapImpl() {
        entries = new Entry[INITIAL_SIZE];
    }

    public HashMapImpl(int size) {
        this.entries = new Entry[size];
    }

    public HashMapImpl(int size, double loadingFactor) {
        this.entries = new Entry[size];
        this.loadingFactor = loadingFactor;
    }


    public boolean put(int key, long value) {

        if ((double)(this.size + 1) / entries.length > loadingFactor) {
            rebuildArray();
        }
        return push(key, value);
    }

    private boolean push(int key, long value) {


        int index = generateIndex(key);
        if (isNull(entries[index])) {
            addToArray(index, key, value);
            return true;
        }

        int i = index;
        do {
            if (isNull(entries[i]) || entries[i].getKey() == key) {
                addToArray(i, key, value);
                return true;
            }

            i = (i + 1) % entries.length;

        } while (i != index);

        return false;
    }


    public Entry get(int key) {

        int index = generateIndex(key);

        if (isNull(entries[index])) {
            return null;
        } else if (entries[index].key == key) {
            return entries[index];
        }

        int i = index + 1;

        do {

            if (isNull(entries[i]) || entries[i].getKey() == key)
                return entries[i];

            i = (i + 1) % entries.length;

        } while (i != index);

        return null;
    }

    private void rebuildArray() {
        Entry[] origin = Arrays.copyOf(this.entries, entries.length);
        this.entries = new Entry[(int) (this.entries.length * 1.5) + 1];
        this.size = 0;

        for ( Entry entry : origin ){
            if ( isNull(entry) )
                continue;
            push(entry.key, entry.value);
        }

    }

    private void addToArray(int index, int key, long value) {
        if (isNull(entries[index])) {
            this.size++;
        }
        entries[index] = new Entry(key, value);
    }

    public int size() {
        return this.size;
    }

    private int index(int hash) {
        return hash & (this.entries.length - 1);
    }

    private int getHash(int key) {
        key ^= (key >>> 20) ^ (key >>> 12);
        return key ^ (key >>> 7) ^ (key >>> 4);
    }

    private int generateIndex(int key) {
        int hash = getHash(key);
        return index(hash);
    }

    private boolean isNull(Entry entry) {
        return entry == null;
    }


    public class Entry {

        private int key;
        private long value;

        Entry(int key, long value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }


        public boolean equalKeys(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry entry = (Entry) o;

            return key == entry.key;

        }


    }
}
