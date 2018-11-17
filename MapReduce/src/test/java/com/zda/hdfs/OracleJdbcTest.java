package com.zda.hdfs;

import org.junit.Test;

import java.sql.*;

public class OracleJdbcTest {

public final String dbUrl="jdbc:oracle:thin:@//192.168.209.1:1521/mr";
public final String driver="oracle.jdbc.driver.OracleDriver";
public final String name="system";
public final String password="root";
    /**
     * 语句对象，不能防止sql注入，性能较低。
     */
    @Test
    public void  insert() throws Exception {
        long start=System.currentTimeMillis();
        //设置驱动
        Class.forName(driver);
        //创建链接
        Connection conn= DriverManager.getConnection(dbUrl,name,password);
        //关闭自动提交
        conn.setAutoCommit(false);

        Statement statement= conn.createStatement();
        for(int i=0;i<100;i++){
            String  sql="insert into words(name,text) values('tomas"+i+"','"+i+"tomas')";
            statement.execute(sql);
        }
        conn.commit();
        statement.close();
        conn.close();
        System.out.println(System.currentTimeMillis()-start);
        //640
    }
    @Test
    public void preparedStatement() throws  Exception{
        long start=System.currentTimeMillis();
        //设置驱动
        Class.forName(driver);
        //创建链接
        Connection conn= DriverManager.getConnection(dbUrl,name,password);
        //关闭自动提交
        conn.setAutoCommit(false);
        String sql="insert into  words(name,text) values(?,?)";
        PreparedStatement ppst=conn.prepareStatement(sql);
        for (int i=0;i<1000000;i++){
            ppst.setString(1,"tom" + i);
            ppst.setInt(2,i % 100);
            //将sql保存到批次中
            ppst.addBatch();
            if(i % 2000 == 0){
                //统一执行批次(批量提交)
                ppst.executeBatch();
            }
        }
        ppst.executeBatch();
        conn.commit();
        ppst.close();
        conn.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void callProcedure()throws Exception {
        long start=System.currentTimeMillis();
        //设置驱动
        Class.forName(driver);
        //创建链接
        Connection conn= DriverManager.getConnection(dbUrl,name,password);
        //关闭自动提交
        conn.setAutoCommit(false);
        CallableStatement cst=conn.prepareCall("{call P_USETABLE(?,?,?)}");
        cst.setString(1,"李四");
        cst.setInt(2,13);
        //注册输出参数
        cst.registerOutParameter(3, Types.VARCHAR);
        cst.execute();
        System.out.println(cst.getString(3));
        conn.commit();
        conn.close();
        System.out.println(System.currentTimeMillis()-start);
    }

    @Test
    public void callFunction()throws Exception {
        long start=System.currentTimeMillis();
        //设置驱动
        Class.forName(driver);
        //创建链接
        Connection conn= DriverManager.getConnection(dbUrl,name,password);
        //关闭自动提交
        conn.setAutoCommit(false);
        //如果方法有多个参数，就用逗号隔开，一个问号表示一个参数。最前面的参数是返回值
        CallableStatement cst=conn.prepareCall("{? = call F_getName(?)}");
        cst.setInt(2,9);
       //cst.setString(3,"李四");
        //注册输出参数
        cst.registerOutParameter(1, Types.VARCHAR);
        cst.execute();
        System.out.println(cst.getString(1));
        conn.commit();
        conn.close();
        System.out.println(System.currentTimeMillis()-start);
    }
}
