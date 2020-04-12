package pattern.flyweight;

import java.util.HashMap;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/13 10:11
 */
public class FlyweightFactory {
    private HashMap<String,Flyweight> flyweights = new HashMap<>();
    public Flyweight getFlyweight(String key){
        if(!flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteFlyweight(key));
        }else {
            System.out.println("具体享元"+key+"已经存在，被成功获取！");
        }
        return flyweights.get(key);
    }
}
