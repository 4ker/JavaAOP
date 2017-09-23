package com.tangzhixiong.javaaop;

import com.tangzhixiong.javaaop.imp.AOPMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AOPHandle implements InvocationHandler {
    private Object object;
    private AOPMethod method;

    public AOPHandle(Object object, AOPMethod method) {
        this.object = object;
        this.method = method;
    }

    /**
     * 这个方法会自动调用, Java 动态代理机制
     * 会传入下面是个参数
     *
     * @param Object   proxy	代理对象的接口, 不同于对象
     * @param Method   method	被调用方法
     * @param Object[] args	方法参数
     *                 不能使用 invoke 时使用 proxy 作为反射参数时, 因为代理对象的接口, 不同于对象
     *                 这种代理机制是面向接口，而不是面向类的
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.method.before(proxy, method, args);
        Object ret = method.invoke(object, args);
        this.method.after(proxy, method, args);
        return ret;
    }
}
