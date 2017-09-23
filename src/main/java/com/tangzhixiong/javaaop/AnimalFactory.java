package com.tangzhixiong.javaaop;

import com.tangzhixiong.javaaop.annon.AnnoInjection;
import com.tangzhixiong.javaaop.imp.AOPMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnimalFactory {

    private static Object getAnimalBase(Object obj, AOPMethod method) {
        /*
                public static Object newProxyInstance(ClassLoader loader,
                                                      Class<?>[] interfaces,
                                                      InvocationHandler h)
         */
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new AOPHandle(AnnoInjection.getBean(obj), method));
//                (proxy, method1, args) -> method1.invoke(AnnoInjection.getBean(proxy),args));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAnimal(Object obj, AOPMethod aopMethod) {
        // 调用方法：AnimalInterface dog = AnimalFactory.getAnimal(new DogImp(), new AOPMethod() {...});
        return (T) getAnimalBase(obj, aopMethod);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAnimal(String className, AOPMethod method) {
        // 调用方法：AnimalInterface dog = AnimalFactory.getAnimal("com.tangzhixiong.javaaop.DogImp", new AOPMethod() {...}
        Object obj = null;
        try {
            obj = getAnimal(Class.forName(className).newInstance(), method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAnimal(Class<? extends T> clz, AOPMethod method) {
        // 调用方法： AnimalInterface dog = AnimalFactory.getAnimal(DogImp.class, new AOPMethod() {...});
        Object obj = null;
        try {
            obj = getAnimalBase(clz.newInstance(), method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) obj;
    }
}
