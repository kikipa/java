package pattern.state;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 9:55
 */
public class ConcreteStateA extends State{
    @Override
    public void handle(Context context){
        System.out.println("ConcreteStateA handle");
        context.setState(new ConcreteStateB());
    }
}
