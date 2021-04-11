import java.util.concurrent.Callable;

public class CallableTest implements Callable<Integer> {

    public int result;
    @Override
    public Integer call() throws Exception {
        result = fibo(36);
        return result;
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
