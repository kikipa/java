package pattern.strategy;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/4 9:49
 */
public class StrategyTest {
    public static void main(String[] args) {
//        Context c = new Context();
//        Strategy s = new ConcreteStrategyA();
//        c.setStrategy(s);
//        c.strategyMethod();
//        s = new ConcreteStrategyB();
//        c.setStrategy(s);
//        c.strategyMethod();

        //StrategyFacotry test
        StrategyFactory f = new StrategyFactory();
        Strategy sa = new ConcreteStrategyA();
        Strategy sb = new ConcreteStrategyB();
        f.put("sa",sa);
        f.put("sb",sb);
        f.strategyMethod("sa");
        f.strategyMethod("sb");
    }
}
