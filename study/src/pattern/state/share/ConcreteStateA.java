package pattern.state.share;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/13 9:48
 */
public class ConcreteStateA extends ShareState{
    @Override
    public void handle(ShareContext context) {
        System.out.println("ConcreteStateA handle");
        context.setState(context.getState("B"));
    }
}
