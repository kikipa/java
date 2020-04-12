package pattern.command.macro_command;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/10 9:59
 */
public class ConcreteCommandA implements Command{
    private CompositeReceiver receiver;
    public ConcreteCommandA(){
        receiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        receiver.action1();
    }
}
