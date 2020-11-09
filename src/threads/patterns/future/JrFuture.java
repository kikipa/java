package threads.patterns.future;


public interface JrFuture<V> {

    public V get();

    public boolean isDone();

}
