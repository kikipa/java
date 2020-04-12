package pattern.state.share;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/13 9:50
 */
public class ConcreteStateB extends ShareState{
    @Override
    public void handle(ShareContext context) {
        System.out.println("ConcreteStateB handle");
        context.setState(context.getState("A"));
    }
}
