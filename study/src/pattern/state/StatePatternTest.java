package pattern.state;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 10:10
 */
public class StatePatternTest {
    public static void main(String[] args) {
        Context context = new Context();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
    }
}
