package threads.practice;

import java.util.concurrent.Semaphore;

/**
 *
 * 三个售票窗口同时出售20张票
 *
 *
 *
 * @description:
 * @author: za-hejin
 * @time: 2020/4/13 18:15
 */
public class SellTicket {

    private volatile int count = 20;

    private Semaphore semaphore = new Semaphore(3);

    public SellTicket(){
        System.out.println("init count is 20");
    }

    public boolean sell() throws InterruptedException {
        if(count<=0){
            throw new InterruptedException("tickets are sold out!");
        }

        try{
            semaphore.acquire();
        }catch (InterruptedException e){
            System.out.println("An error occurs or ticket sold out");
            return false;
        }

        int current = --count;
        System.out.println("sold one ticket, current count is " + current);

        semaphore.release();

        return true;
    }

    public static void main(String[] args) {
        SellTicket st = new SellTicket();

        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(;;){
                        try {
                            st.sell();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


//    class Sync{
//        private volatile int state;
//
//        Sync(int state){
//            this.state = state;
//        }
//
//        int getState(){
//            return state;
//        }
//
//        boolean tryReduceNonfairly(){
//
//            int current = getState();
//            int next = current - 1;
//            if(next==0){
//                //notify all waiting threads
//
//            }
//
//
//
//            return false;
//        }
//    }
//
//    private int ticketCount = 20;
//
//    protected int getTicketCount(){
//        return ticketCount;
//    }
//
//    private boolean trySellTicket(){
//
//        int currentTicketCount = getTicketCount();
//        int nextCount = currentTicketCount - 1;
//        if(nextCount == 0){
//            //notify all waiting thread
//        }
//
//        return false;
//    }
//
//    public void sellTicket(){
//
//
//
//    }


}
