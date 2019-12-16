package pattern.state.demo;

import pattern.state.State;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 10:27
 */
public class ThreadContext {
    private ThreadState state;
    public ThreadContext(){
        //init state
        state = new New();
    }
    public void setState(ThreadState state){
        this.state = state;
    }
    public ThreadState getState(){
        return state;
    }
    public void start(){
        ((New)state).start(this);
    }
    public void getCPU(){
        ((Runnable)state).getCPU(this);
    }
    public void suspend(){
        ((Running)state).suspend(this);
    }
    public void resume(){
        ((Blocked)state).resume(this);
    }
    public void stop(){
        ((Running)state).stop(this);
    }
}
