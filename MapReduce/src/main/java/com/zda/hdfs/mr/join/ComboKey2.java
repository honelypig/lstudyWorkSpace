package com.zda.hdfs.mr.join;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 这个key是通过mapper构建的
 */
public class ComboKey2 implements WritableComparable<ComboKey2> {
    //0-customer 1-order
    private int type;
    private int cid;
    private int oid;
    private String orderInfo="";
    private String customerInfo="";

    //这种key有两种可能，0是客户（此时oid，与orderinfo为空），1是订单（此时cid与customerInfo为空），但是int类型初始值为0；
    public int compareTo(ComboKey2 o) {//对比，表示这个类含有排序的功能
        int cid0=o.getCid();
        int oid0=o.getOid();
        int type0=o.getType();
        //如果是同一个客户
        if(cid0==cid){
            if(type0==type){//同一个客户的两个订单
                return oid-oid0;
            }else{//一个customer和一个order
                //那就让customer排在前面
                if(type==0){
                    return -1;
                }else return 1;
            }
            //不同客户
        }else {
            return cid-cid0;
        }
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(type);
        out.writeInt(cid);
        out.writeInt(oid);
        out.writeUTF(customerInfo);
        out.writeUTF(orderInfo);
    }

    //反序列化，从字节流中读出各个数据字段 读出的顺序应该跟序列化时写入的顺序保持一致
    public void readFields(DataInput in) throws IOException {
        type = in.readInt();
        cid = in.readInt();
        oid = in.readInt();
        customerInfo = in.readUTF();
        orderInfo = in.readUTF();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }
    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }
}
