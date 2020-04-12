package pattern.command.macro_command;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/10 10:03
 */
public class MacroCommandPatternTest {
    public static void main(String[] args) {
        CompositeInvoker invoker = new CompositeInvoker();
        invoker.add(new ConcreteCommandA());
        invoker.add(new ConcreteCommandB());
        invoker.execute();
    }
}
