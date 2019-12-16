package pattern.command.macro_command;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/10 10:01
 */
public class ConcreteCommandB implements Command {
    private CompositeReceiver receiver;
    public ConcreteCommandB(){
        receiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        receiver.action2();
    }
}
