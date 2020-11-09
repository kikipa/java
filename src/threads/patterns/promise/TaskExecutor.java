package threads.patterns.promise;

public class TaskExecutor implements Runnable{

    private Promise promise;

    @Override
    public void run() {

        Result result = new Result();

        promise.setResult(result);

    }
}
