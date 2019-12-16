package pattern.state.demo;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 10:52
 */
public class ThreadContextTest {
    public static void main(String[] args) {
        ThreadContext tc = new ThreadContext();
        tc.start();
        tc.getCPU();
        tc.suspend();
        tc.resume();
        tc.getCPU();
        tc.stop();
    }
}
