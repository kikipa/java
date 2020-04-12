package threads.juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class YvesCountDownLatch {
    private Sync sync;
    public YvesCountDownLatch(int count){
        this.sync = new Sync(count);
    }

    public void await(){
        sync.tryAcquireShared(1);
    }

    public void countDown(){
        sync.tryReleaseShared(1);
    }

    class Sync extends AbstractQueuedSynchronizer{
        public Sync(int count){
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return getState()==0?1:-1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for(;;){
                int c = getState();
                if(c==0){
                    return false;
                }
                int nectc = c -1;
                if(compareAndSetState(c,nectc)){
                    return nectc==0;
                }
            }
        }
    }

}

