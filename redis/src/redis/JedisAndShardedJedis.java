package redis;

import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

public class JedisAndShardedJedis {
	//redis-server --port 6380  ����redis����������ָ��redis�Ķ˿ں�
	//redis-server  ����redis��������ʹ��Ĭ�϶˿ں�
	//redis-cli -h ��������ַ -p �˿ں� ����redis������
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		shardPipelinedPool();
	}
	public static void baseUseJedis(){
		Jedis jedis=new Jedis("localhost");
		long start=System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			String result=jedis.set("A"+i, "V"+i);
		}
		long end=System.currentTimeMillis();
		System.out.println("simple time"+(end-start)/1000.0+"second");
		jedis.disconnect();
	}
	//redis�������Ƿ��� acid�ĸ�����ԭ���Ȳ���ʮ����������ͳһִ��
	public static void transactions(){
		 Jedis jedis = new Jedis("localhost");
		    long start = System.currentTimeMillis();
		    Transaction tx = jedis.multi();
		    for (int i = 0; i < 100000; i++) {
		        tx.set("t" + i, "t" + i);
		    }
		    //
		    List<Object> results = tx.exec();
		    long end = System.currentTimeMillis();
		    System.out.println("Transaction SET: " + ((end - start)/1000.0) + " seconds");
		    jedis.disconnect();
	}
	/**redis�ܵ�
	 * Redis��һ����Ӧʽ�ķ��񣬵��ͻ��˷���һ������󣬾ʹ�������״̬�ȴ�Redis���ؽ����
	 * ����һ���������ĵ�ʱ��Ͱ����������֣�
	 * 		����ӿͻ��˵���������ʱ�䡢����ӷ��������ͻ��˵�ʱ�����������ִ��ʱ�䣬ǰ�����������ĵ�ʱ���ܺͳ�ΪRTT��Round Trip Time����
	 * 		���ͻ��������������������ʱʱ��RTT�Ϳ��ܻ�ܴ������ͻᵼ���������⡣
	 * �ܵ���Pipeline������Ϊ�˸����������ģ����ùܵ����ͻ��˿���һ���Է��Ͷ����������õȴ�����������Ӧ��
	 *    ������������������һ���Զ�ȡ�������Ӧ���������Լ���Ľ���RTTʱ��Ӷ��������ܡ�
	 *    ��Ҫע�⵽����pipeline��ʽ�������ͣ�redis�����ڴ�������������ǰ�Ȼ�������������Ĵ�������
	 *    ���������Խ�࣬���������ڴ�ҲԽ�ࡣ���Բ����Ǵ��������Խ��Խ�á�
	 * pipeline�͡�������������ȫ��ͬ�ĸ��pipelineֻ�Ǳ��������в����Ĵ��ݵķ����ԣ�pipelineҲ���������������У�Ҳ���Բ��ڡ�
	 * ������Σ�pipeline�з��͵�ÿ��command���ᱻserver����ִ�У����ִ��ʧ�ܣ������ڴ˺����Ӧ�еõ���Ϣ��
	 * Ҳ����pipeline�����Ǳ�����command��һ��ɹ��������壻�������pipeline�Ĳ�������װ�������У���ô����������ȷ�������ĳɹ���ʧ�ܡ�
	 */
	public static void Pipelined() {
	    Jedis jedis = new Jedis("localhost");
	    //��������ڿͻ�����������һ���ܵ����ӣ�Ȼ������ͨ���ܵ����䵽����ˣ�
	    Pipeline pipeline = jedis.pipelined();
	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        pipeline.set("p" + i, "p" + i);
	    }
	    List<Object> results = pipeline.syncAndReturnAll();
	    long end = System.currentTimeMillis();
	    System.out.println("Pipelined SET: " + ((end - start)/1000.0) + " seconds");
	    jedis.disconnect();
	}

	public static void combPipelineTrans() {
		Jedis  jedis = new Jedis("localhost"); 
		 Pipeline pipeline = jedis.pipelined();
	    long start = System.currentTimeMillis();
	    pipeline.multi();//�ܵ�ʹ������
	    for (int i = 0; i < 100000; i++) {
	        pipeline.set("" + i, "" + i);
	    }
	    pipeline.exec();//ִ������Ч�ʻ�Ƚ���
	    List<Object> results = pipeline.syncAndReturnAll();
	    long end = System.currentTimeMillis();
	    System.out.println("Pipelined transaction: " + ((end - start)/1000.0) + " seconds");
	    jedis.disconnect();
	}
	
	public static void shardNormal() {
		//�ֲ�ʽֱ��ͬ�����ã����redis���������ͻ����������ֱ�����ӣ�ͬ��ִ�У�
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo("localhost",6379),
	            new JedisShardInfo("localhost",6380));
	 
	    ShardedJedis sharding = new ShardedJedis(shards);
	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        String result = sharding.set("sn" + i, "n" + i);
	    }//�Ὣ������䵽����redis�������ϣ�������ͬ����
	    long end = System.currentTimeMillis();
	    System.out.println("Simple@Sharing SET: " + ((end - start)/1000.0) + " seconds");
	 
	    sharding.disconnect();
	}
	
	public static void shardpipelined() {
		//�ֲ�ʽֱ���ܵ��첽ִ��
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo("localhost",6379),
	            new JedisShardInfo("localhost",6380));
	 
	    ShardedJedis sharding = new ShardedJedis(shards);
	    ShardedJedisPipeline pipeline = sharding.pipelined();
	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        pipeline.set("sp" + i, "p" + i);
	    }
	    List<Object> results = pipeline.syncAndReturnAll();
	    long end = System.currentTimeMillis();
	    System.out.println("Pipelined@Sharing SET: " + ((end - start)/1000.0) + " seconds");
	    sharding.disconnect();
	}
	
	//ʹ�����ӳ�
	public static void shardSimplePool() {
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo("localhost",6379),
	            new JedisShardInfo("localhost",6380));
	 
	    ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
	 
	    ShardedJedis one = pool.getResource();
	  //ֻ�������һ��redis��������������
	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        String result = one.set("spn" + i, "n" + i);
	    }
	    long end = System.currentTimeMillis();
	    pool.returnResource(one);
	    System.out.println("Simple@Pool SET: " + ((end - start)/1000.0) + " seconds");
	 
	    pool.destroy();
	}
	
	
	public static void shardPipelinedPool() {
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo("localhost",6379),
	            new JedisShardInfo("localhost",6380));
	    ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
	
	    ShardedJedis one = pool.getResource();
	//ֻ�������һ��redis��������������
	    ShardedJedisPipeline pipeline = one.pipelined();
	 
	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        pipeline.set("sppn" + i, "n" + i);
	    }
	    List<Object> results = pipeline.syncAndReturnAll();
	    long end = System.currentTimeMillis();
	   // pool.returnResource(one); 
	    System.out.println("Pipelined@Pool SET: " + ((end - start)/1000.0) + " seconds");
	    pool.destroy();
	}
}
