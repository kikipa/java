package pattern.command;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/9 14:07
 */
public class ConcreteCommandA implements Command{
    private Receiver receiver;
    public ConcreteCommandA(){
        receiver = new ReceiverA();
    }
    @Override
    public void execute() {
        receiver.action();
    }
}
