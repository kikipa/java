package pattern.state.demo;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 10:45
 */
public class Blocked extends ThreadState {
    public Blocked(){
        System.out.println("current state: BLOCKED");
    }
    public void resume(ThreadContext tc){
        System.out.println("invoke resume: BLOCKED -> RUNNABLE");
        tc.setState(new Runnable());
    }
}
