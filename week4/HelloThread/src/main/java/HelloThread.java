import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class HelloThread {

    public static void main(String[] args) {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        int result = 0; //这是得到的返回值
        try {
            result = sum1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        start=System.currentTimeMillis();

        result = 0; //这是得到的返回值
        try {
            result = sum2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程

        start=System.currentTimeMillis();

        result = 0; //这是得到的返回值
        try {
            result = sum5();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程

        start=System.currentTimeMillis();

        result = 0; //这是得到的返回值
        try {
            result = sum6();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }

    // 实现runnable接口轮询等待线程执行完
    private static int sum1() throws InterruptedException {
        RunnableTest runnableTest = new RunnableTest();
        runnableTest.a = 36;
        new Thread(runnableTest).start();
        while(runnableTest.result == 0) {
            Thread.sleep(10);
        }
        return runnableTest.result;
    }

    // join暂停主线程
    private static int sum2() throws InterruptedException {
        RunnableTest runnableTest = new RunnableTest();
        runnableTest.a = 36;
        Thread t = new Thread(runnableTest);
        t.start();
        t.join();
        return runnableTest.result;
    }

    // 继承thread类实现，与前两种类似
    private static void sum3() {}
    private static void sum4() {}

    // FutureTask + Callable获取返回值
    private static int sum5() throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new CallableTest());
        Thread thread = new Thread(futureTask);
        thread.start();
        return (int) futureTask.get();
    }

    // 线程池 + Callable
    private static int sum6() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new CallableTest());
        return future.get();
    }

    // CountDownLatch
    private static void sum7() {}

    // 在新线程加lock，主线程等锁
    private static void sum8() {}
}
