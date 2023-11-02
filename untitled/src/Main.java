import java.util.Timer;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main
{

    public static AtomicInteger total = new AtomicInteger(0);
    public static AtomicBoolean finished = new AtomicBoolean(false);
    static long startTime;
    public static void main(String[] args)
    {
        startTime = System.currentTimeMillis();
        for(int i = 0; i < 100; i++)
        {
            finished.getAndSet(i==99);
            new Thread(() -> {
                AtomicInteger localSum = new AtomicInteger();
                for(int j = 0; j < 1000000; j++)
                {
                    localSum.incrementAndGet();
                }
                total.getAndAdd(localSum.get());
                System.out.println(total);
                if(finished.get())
                {
                    System.out.println(System.currentTimeMillis() - startTime);
                }
            }).start();
        }

    }
}
