package pattern.state.demo;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 10:37
 */
public class Runnable extends ThreadState{
    public Runnable(){
        System.out.println("current state: RUNNABLE");
    }
    public void getCPU(ThreadContext tc){
        System.out.println("invoke getCPU: RUNNABLE -> RUNNING");
        tc.setState(new Running());
    }
}
