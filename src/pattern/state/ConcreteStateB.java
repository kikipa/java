package pattern.state;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 9:56
 */
public class ConcreteStateB extends State{
    @Override
    public void handle(Context context){
        System.out.println("ConcreteStateB handle");
        context.setState(new ConcreteStateA());
    }
}
