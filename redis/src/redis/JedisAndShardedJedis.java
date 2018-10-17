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
	//redis-server --port 6380  启动redis服务器，并指定redis的端口号
	//redis-server  启动redis服务器，使用默认端口号
	//redis-cli -h 服务器地址 -p 端口号 连接redis服务器
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
	//redis的事务是符合 acid四个事务原则，先布置十万个命令，后再统一执行
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
	/**redis管道
	 * Redis是一个响应式的服务，当客户端发送一个请求后，就处于阻塞状态等待Redis返回结果。
	 * 这样一次命令消耗的时间就包括三个部分：
	 * 		请求从客户端到服务器的时间、结果从服务器到客户端的时间和命令真正执行时间，前两个部分消耗的时间总和称为RTT（Round Trip Time），
	 * 		当客户端与服务器存在网络延时时，RTT就可能会很大，这样就会导致性能问题。
	 * 管道（Pipeline）就是为了改善这个情况的，利用管道，客户端可以一次性发送多个请求而不用等待服务器的响应，
	 *    待所有命令都发送完后再一次性读取服务的响应，这样可以极大的降低RTT时间从而提升性能。
	 *    需要注意到是用pipeline方式打包命令发送，redis必须在处理完所有命令前先缓存起所有命令的处理结果。
	 *    打包的命令越多，缓存消耗内存也越多。所以并不是打包的命令越多越好。
	 * pipeline和“事务”是两个完全不同的概念，pipeline只是表达“交互”中操作的传递的方向性，pipeline也可以在事务中运行，也可以不在。
	 * 无论如何，pipeline中发送的每个command都会被server立即执行，如果执行失败，将会在此后的相应中得到信息；
	 * 也就是pipeline并不是表达“所有command都一起成功”的语义；但是如果pipeline的操作被封装在事务中，那么将有事务来确保操作的成功与失败。
	 */
	public static void Pipelined() {
	    Jedis jedis = new Jedis("localhost");
	    //这边是先在客户端与服务端用一个管道连接，然后将命令通过管道传输到服务端，
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
	    pipeline.multi();//管道使用事务
	    for (int i = 0; i < 100000; i++) {
	        pipeline.set("" + i, "" + i);
	    }
	    pipeline.exec();//执行事务，效率会比较慢
	    List<Object> results = pipeline.syncAndReturnAll();
	    long end = System.currentTimeMillis();
	    System.out.println("Pipelined transaction: " + ((end - start)/1000.0) + " seconds");
	    jedis.disconnect();
	}
	
	public static void shardNormal() {
		//分布式直连同步调用（多个redis服务器，客户端与服务器直接连接，同步执行）
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo("localhost",6379),
	            new JedisShardInfo("localhost",6380));
	 
	    ShardedJedis sharding = new ShardedJedis(shards);
	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        String result = sharding.set("sn" + i, "n" + i);
	    }//会将并命令传输到各个redis服务器上，并且是同步的
	    long end = System.currentTimeMillis();
	    System.out.println("Simple@Sharing SET: " + ((end - start)/1000.0) + " seconds");
	 
	    sharding.disconnect();
	}
	
	public static void shardpipelined() {
		//分布式直连管道异步执行
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
	
	//使用连接池
	public static void shardSimplePool() {
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo("localhost",6379),
	            new JedisShardInfo("localhost",6380));
	 
	    ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
	 
	    ShardedJedis one = pool.getResource();
	  //只会对其中一个redis服务器发起命令
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
	//只会对其中一个redis服务器发起命令
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
