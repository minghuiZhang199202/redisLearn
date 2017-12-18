package com.redis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>@author minghui_zhang </p>
 * <p>description   </p>
 * <p>date  created in  22:56 / 2017/12/18</p>
 * <p>modified by   </p>
 */
public class JedisTest {

    @Test
    public void test(){
        //设置ip地址
        Jedis jedis = new Jedis("192.168.0.104",6379);
        //保存数据
        jedis.set("name","jack");
        //获取数据
        String name = jedis.get("name");
        System.out.println(name);
    }

    /**
     * 连接池测试
     */
    @Test
    public void testPool(){
        //获得连接池对象
        JedisPoolConfig jpc = new JedisPoolConfig();
        //设置最大连接池
        jpc.setMaxTotal(30);
        //设置最大空闲连接数
        jpc.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(jpc,"192.168.0.104",6379);
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set("age","12");
            System.out.println(jedis.get("age"));
        }catch (Exception e){
          e.printStackTrace();
        } finally {
            if (jedis != null){
                jedis.close();
            }
            if (jedisPool != null){
                jedisPool.close();;
            }
        }
    }
}
