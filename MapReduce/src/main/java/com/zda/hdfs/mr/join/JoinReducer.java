package com.zda.hdfs.mr.join;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class JoinReducer extends Reducer<ComboKey2, NullWritable, Text,NullWritable> {
    @Override
    protected void reduce(ComboKey2 key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<NullWritable> it = values.iterator();
        //it.next();
        int type = key.getType();
        int cid = key.getCid() ;
        String cinfo = key.getCustomerInfo() ;
        while(it.hasNext()){
            it.next();
            String oinfo = key.getOrderInfo();
            context.write(new Text(cinfo + "," + oinfo),NullWritable.get());
        }
    }
}
