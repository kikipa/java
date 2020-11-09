package threads.patterns.promise;

public class Promise {

    private Result result;

    public Result getResult(){
        return result;
    }

    public void setResult(Result result){
        result = result;
    }

    public boolean isDone(){
        return result==null?false:true;
    }

}
