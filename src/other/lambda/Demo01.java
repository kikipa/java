package other.lambda;

public class Demo01 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("多线程执行任务");
            }
        }).start();

        new Thread(()->{
           System.out.println("多线程执行任务2");
        }).start();

//        invokeCook(()->{
//            System.out.println("吃饭了");
//        });

        invokeCook(()->System.out.println("吃饭了"));
    }

    static void invokeCook(Cooker cooker){
        cooker.makeFood();
    }
}

interface Cooker{
    void makeFood();
}
