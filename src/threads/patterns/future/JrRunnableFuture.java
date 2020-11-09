package threads.patterns.future;

public interface JrRunnableFuture<V> extends Runnable, JrFuture<V>{

    void run();

}
