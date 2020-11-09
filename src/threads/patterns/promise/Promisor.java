package threads.patterns.promise;

public class Promisor {

    private Promise promise;

    private TaskExecutor executor;

    public Promise compute(){

         executor.run();

         return promise;
    }

}
