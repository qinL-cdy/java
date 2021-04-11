public class RunnableTest implements Runnable {

    public int a;

    public int result;

    @Override
    public void run() {
        result = fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
