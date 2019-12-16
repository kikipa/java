package pattern.state.demo;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 10:25
 */
public class New extends ThreadState{
    public New(){
        System.out.println("current state: NEW");
    }
    public void start(ThreadContext tc){
        System.out.println("invoke start: NEW -> RUNNABLE");
        tc.setState(new Runnable());
    }
}
