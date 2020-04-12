package pattern.command;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/9 14:08
 */
public class ConcreteCommandB implements Command{
    private Receiver receiver;
    public ConcreteCommandB(){
        receiver = new ReceiverB();
    }
    @Override
    public void execute() {
        receiver.action();
    }
}
