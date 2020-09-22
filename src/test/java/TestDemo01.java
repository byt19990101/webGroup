import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class TestDemo01 {
    @Test
    public void test01(){
        Jedis jedis = new Jedis("192.168.56.101",6379);
        jedis.set("username","zhangsan");
        jedis.zadd("sortedset",90,"ted");
        String name = jedis.get("username");
        System.out.println(name);

        Set<String> sortedset = jedis.zrange("sortedset", 0, -1);
        sortedset.stream().forEach(System.out::println);

        jedis.close();
    }

    @Test
    public void test02(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //1.最大连接数
        poolConfig.setMaxTotal(30);
        //2.最大空闲连接数
        poolConfig.setMaxIdle(10);

        JedisPool pool = new JedisPool(poolConfig,"192.168.56.101",6379);

        Jedis jedis = pool.getResource();

        jedis.set("name","black");
        String name = jedis.get("name");

        System.out.println(name);

        jedis.close();
        pool.close();
    }
}
