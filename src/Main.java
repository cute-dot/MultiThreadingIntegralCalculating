import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        int n = 10000;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество потоков : ");
        int num = scanner.nextInt();
        System.out.println("Введите нижнюю границу  : ");
        double a = scanner.nextDouble();
        System.out.println("Введите верхнюю границу : ");
        double b = scanner.nextDouble();
        double startTime = System.currentTimeMillis();
        double h = (b-a)/n;
        MultiThreadingCalculatingIntegral startCalculating = new MultiThreadingCalculatingIntegral();
        startCalculating.startThreads(num, n , a , h);
        double endTime = System.currentTimeMillis();
        System.out.println("Затраченное время на расчет :" + (endTime-startTime));

//        double startTime = System.currentTimeMillis();
//        MultiThreadingCalculatingIntegral test = new MultiThreadingCalculatingIntegral();
//        test.addToListThreads();
//        double endTime = System.currentTimeMillis();
//        System.out.println("Затраченное время на расчет :" + (endTime-startTime));
    }

}
