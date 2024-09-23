import java.util.ArrayList;
import java.util.List;

public class MultiThreadingCalculatingIntegral {
    private final Object object = new Object();
    private List<Integer> list = new ArrayList<>();
    public double function(double x){
        return x*x*x*x;
    }
    public double integralCalculating(double a, int startIteration, int lastIteration, double h){
        double sum = 0.0;
        for(int i = startIteration; i < lastIteration; i++){
            double x = a + h * i;
            sum += function(x);
        }
        return sum;
    }
    public void addListTest(){

        synchronized (object){
            for (int i = 0; i < 1000000; i ++){
                list.add(i);
            }
            System.out.println(list.size());
        }
    }
    public void addToListThreads(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                addListTest();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                addListTest();
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.size());

    }
    public void startThreads(int numOfThreads, int n, double a, double h){
        int numOfIterations = n/numOfThreads;
        Thread[] threads = new Thread[numOfThreads];
        double result = 0.0;
        List<Double> list = new ArrayList<>();
        for(int i = 0; i < numOfThreads; i++){
            int startIteration = (i)* numOfIterations;
            int lastIteration = (i + 1) * numOfIterations;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(integralCalculating(a, startIteration, lastIteration, h));
                }
            });
            threads[i].start();
            System.out.println("Поток номер " + i + " запущен" );
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Double aDouble : list) {
            result += aDouble;
        }
        System.out.println("Решенный интеграл равен :" + result * h);
    }
}
