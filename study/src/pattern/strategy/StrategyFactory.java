package pattern.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/4 10:03
 */
public class StrategyFactory {
    private Map<String, Strategy> strategies = new HashMap<String, Strategy>();
    public void put(String key, Strategy strategy){
        strategies.put(key,strategy);
    }
    public Strategy get(String key){
        return strategies.get(key);
    }
    public void strategyMethod(String key){
        strategies.get(key).strategyMethod();
    }
}
