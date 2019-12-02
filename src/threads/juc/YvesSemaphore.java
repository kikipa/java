package threads.juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class YvesSemaphore {
    private Sync sync;
    public YvesSemaphore(int permits){
        this.sync = new Sync(permits);
    }

    public void acquire(){
        sync.tryAcquireShared(1);
    }

    public void release(){
        sync.tryReleaseShared(1);
    }

    class Sync extends AbstractQueuedSynchronizer{
        private int permits;
        public Sync(int permits){
            this.permits = permits;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            int state = getState();
            int nextState = state + arg;
            //这里不用自旋自旋逻辑在acquireShared方法中有
            if(nextState<=permits){
                if(compareAndSetState(state, nextState)){
                    return 1;
                }
            }
            return -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            int state = getState();
            if(compareAndSetState(state, state-arg)){
                return true;
            }else {
                return false;
            }
        }
    }
}
