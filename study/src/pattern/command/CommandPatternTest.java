package pattern.command;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/9 14:16
 */
public class CommandPatternTest {
    public static void main(String[] args) {
        Command command = new ConcreteCommandA();
        Invoker invoker = new Invoker(command);
        invoker.cell();

        command = new ConcreteCommandB();
        invoker.setCommand(command);
        invoker.cell();
    }
}
