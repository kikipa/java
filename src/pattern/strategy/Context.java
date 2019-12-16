package pattern.strategy;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/4 9:47
 */
public class Context {
    private Strategy strategy;
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
    public Strategy getStrategy(){
        return strategy;
    }
    public void strategyMethod(){
        strategy.strategyMethod();
    }
}
