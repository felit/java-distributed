package com.livedrof.distributed.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

import java.util.Map;

public class CommandHelloWorld extends HystrixCommand<String> {
    private final String name;

    public CommandHelloWorld(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloWorldPool")));

        this.name = name;
    }

    @Override
    protected String run() {
//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        int a = 0;
//        double b = 1 / a;
        for (Map.Entry<Thread, StackTraceElement[]> t : Thread.getAllStackTraces().entrySet()) {
            System.out.println(t.getKey().getId() + ":" + t.getKey().getName());
        }
        return "Hello " + name + "! thread:" + Thread.currentThread().getName();
    }
}
