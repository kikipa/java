package pattern.state.demo;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 10:43
 */
public class Running extends ThreadState{
    public Running(){
        System.out.println("current state: RUNNING");
    }
    public void suspend(ThreadContext tc){
        System.out.println("invoke suspend: RUNNING -> BLOCKED");
        tc.setState(new Blocked());
    }
    public void stop(ThreadContext tc){
        System.out.println("invoke dead: RUNNING -> DEAD");
        tc.setState(new Dead());
    }
}
