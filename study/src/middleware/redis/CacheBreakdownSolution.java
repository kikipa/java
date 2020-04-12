package middleware.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: 缓存雪崩情况解决方案：用信号量对数据库访问限流
 */
public class CacheBreakdownSolution {

    RedisTemplate redisTemplate;

    Mybatis mybatis;

    //用信号量对数据库连接限流
    Semaphore semaphore = new Semaphore(10);

    /**
     * @description: 一般从缓存获取数据的逻辑（没有考虑高并发缓存雪崩问题）
     * @param key
     * @return: value值
     */
    public String getData(String key){
        String value = null;

        //1.查缓存
        value = redisTemplate.get(key);
        if(value!=null){
            System.out.println("从缓存查询到数据: key="+key);
            return value;
        }

        //2.缓存未命中则查数据库(高并发情况下，数据库会抗不住访问压力)
        value = mybatis.select(key);
        System.out.println("从数据库查询到数据: key="+key);

        //3.更新缓存(可以异步更新,此处未实现)
        redisTemplate.setNX(key,value);

        return value;
    }

    /**
     * @description: 高并发下从缓存获取数据（缓存雪崩解决方案:Semaphore）
     * @param key
     * @return: value值
     */
    public String getDataHighConcurrent_Semaphore(String key){
        String value = null;

        //1.查缓存
        value = redisTemplate.get(key);
        if(value!=null){
            System.out.println("从缓存查询到数据: key="+key);
            return value;
        }

        //信号量解决缓存雪崩导致数据库访问压力（限流）
        try {

            /**
             * 这里不用semaphore.acquire();
             * 它会使没有获取到信号量的线程长时间等待
             * */
            //查数据库前要获取信号量
            if(semaphore.tryAcquire(3, TimeUnit.SECONDS)){
                //尝试获取信号量3秒后,获取到了信号量

                //二次检查缓存(如果前面的请求已经更新了缓存,则直接查缓存)
                value = redisTemplate.get(key);
                if(value!=null){
                    System.out.println("再次从缓存查询到数据: key="+key);
                    return value;
                }

                //2.缓存未命中则查数据库
                value = mybatis.select(key);
                System.out.println("从数据库查询到数据: key="+key);

                //3.更新缓存(可以异步更新,此处未实现)
                redisTemplate.setNX(key,value);
            }else{
                //尝试获取信号量3秒后,没有获取到了信号量

                //TODO 异常提示
                //TODO 排队,从缓存再次查询
                value = redisTemplate.get(key);
                if(value!=null){
                    System.out.println("再次从缓存查询到数据: key="+key);
                    return value;
                }
                //TODO 从备份缓存查询
                //TODO 降低客户心理预期,节省服务资源：XXX太火了,没货了,推荐您购买YYY
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放信号量
            semaphore.release();
        }

        return value;
    }

    /**
     * 模拟缓存访问
     * */
    class RedisTemplate{
        Redis redis;
        public RedisTemplate(){
            redis = new Redis();
        }
        void setNX(String key, String value){
            if(!redis.keyValue.containsKey(key)){
                redis.keyValue.put(key,value);
            }
        }
        String get(String key){
            return redis.keyValue.get(key);
        }
    }

    /**
     * 模拟数据库访问
     * */
    class Mybatis{
        String select(String key){
            return "stored-value";
        }
    }

    /**
     * 模拟缓存
     * */
    class Redis{
        Map<String,String> keyValue;
        public Redis(){
            keyValue = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        //TODO Test
    }
}
