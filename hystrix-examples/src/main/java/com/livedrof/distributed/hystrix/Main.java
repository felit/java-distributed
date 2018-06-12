package com.livedrof.distributed.hystrix;

import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCommand;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 如果某程序或class要使用Hystrix，只需简单继承HystrixCommand/HystrixObservableCommand并重写run()/construct()，然后调用程序实例化此class并执行execute()/queue()/observe()/toObservable()。
 * <p>
 * 作者：star24
 * 链接：https://www.jianshu.com/p/b9af028efebb
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Main {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        HystrixCommand<String> command = new CommandHelloWorld("Bob");

        //请求
        String s = command.execute();

        System.out.println(s);


        Observable<String> observe = new CommandHelloWorld("Synchronous-hystrix").observe();

        //block
        String single = observe.toBlocking().single();
        System.out.println("result=" + single);




//        Future<String> s2 = new CommandHelloWorld("Bob").queue();
//        System.out.println(s2.get());
//        Observable<String> s3 = new CommandHelloWorld("Bob").observe();
//        System.out.println(s3);
    }
}
