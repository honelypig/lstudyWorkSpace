package redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestRedis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new RedisClient().show();
		redisString();
	}
	public static void  redisString() {
		  //���ӱ��ص� Redis ����
        Jedis jedis = new Jedis("localhost");
        System.out.println("���ӳɹ�");
        //���� redis �ַ�������
        jedis.set("runoobkey", "www.baidu.com");
        jedis.sadd("set", "setValue1", "setValue2", "setValue3", "setValue4");
        jedis.lpush("list", "listValue1", "listValue2", "listValue3", "listValue4");
        // ��ȡ�洢�����ݲ����
        System.out.println("redis �洢���ַ���Ϊ: "+ jedis.get("runoobkey"));
	}
	public static void  redisList() {
		 //���ӱ��ص� Redis ����
        Jedis jedis = new Jedis("localhost");
        System.out.println("���ӳɹ�");
        //�洢���ݵ��б���
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // ��ȡ�洢�����ݲ����
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("�б���Ϊ: "+list.get(i));
        }
	}
	public static void  redisKey() {
		 //���ӱ��ص� Redis ����
        Jedis jedis = new Jedis("localhost");
        System.out.println("���ӳɹ�");
 
        // ��ȡ���ݲ����
        Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);   
        }
	}

}
