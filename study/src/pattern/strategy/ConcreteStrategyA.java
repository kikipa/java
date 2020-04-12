package pattern.strategy;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/4 9:46
 */
public class ConcreteStrategyA implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("ConcreteStrategyA strategyMethod");
    }
}
