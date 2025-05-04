// import java.util.*;

 class TokenRing1 extends Thread{

    public static volatile int tokenHolder;
    public static int NUM_PROCESSES=5;
    public static volatile boolean[] wantsToEnter = new boolean[NUM_PROCESSES];

    public int idx;
    TokenRing1(int idx){
        this.idx=idx;
    }

    private void consumeResource(int idx) throws InterruptedException{
        this.tokenHolder=0;
        System.out.printf("Process %d is using the resource \n",idx);
        Thread.sleep(1000);
        System.out.printf("Process %d finished using the resource \n",idx);
    }

    private void passToken(){
        tokenHolder=(tokenHolder+1)%NUM_PROCESSES;
    }

    @Override
    public void run(){
        while(true){

            // System.out.println("getName()");
            if(idx==tokenHolder){
                if(wantsToEnter[idx]==true){
                    try{
                        this.consumeResource(idx);
                        wantsToEnter[idx]=false;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                // System.out.println("We are passing the token");
                passToken();
                try{
                    Thread.sleep(2);
                }catch(InterruptedException e){
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
    }

}

public class TokenRing{

    public static void main(String[] args) {
        TokenRing1 tokenRing1[]=new TokenRing1[5];
        for(int i=0;i<5;i++){
            tokenRing1[i]=new TokenRing1(i);
            tokenRing1[i].start();
        }
        new Thread(()-> {
            while (true) {
                try{
                    int requester=(int) (Math.random() * 5);
                    System.out.print(requester);
                    TokenRing1.wantsToEnter[requester]=true;
                    Thread.sleep(3000);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }
};