package TEXT;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class zhhixing {


    public static void main(String[] args) {
        lll lll = new lll(1, 3);
        new Thread(()->{
            lll.print("a",1,2);
        },"t1").start();
        new Thread(()->{
            lll.print("b",2,3);
        },"t2").start();
        new Thread(()->{
            lll.print("c",3,1);
        },"t3").start();
    }

}
class lll{
    private int fal;
    private int count;
    lll(int fal,int count){
        this.fal=fal;
        this.count=count;
    }
    public void print(String pr,int fal,int next){
        for (int i = 0; i < count; i++) {
        synchronized (this){
                while (fal!=this.fal){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(pr);
                this.fal=next;
                this.notifyAll();
            }
        }
    }
}