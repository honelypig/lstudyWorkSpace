package com.zda.hdfs.mr.join;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class ComboKey2Comparator extends WritableComparator {
    public ComboKey2Comparator() {
        super(ComboKey2.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        ComboKey2 k1 = (ComboKey2) a;
        ComboKey2 k2 = (ComboKey2) b;
        return k1.compareTo(k2);
    }
}
