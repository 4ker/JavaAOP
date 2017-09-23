package com.tangzhixiong.javaaop.annon;

import com.tangzhixiong.javaaop.DogImp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnoInjection {

    public static Object getBean(Object obj) {
        try {

            System.err.println("== 给 '" + obj + "' 注入属性 ==");
            // 获得类属性
            Field declaredFields[] = obj.getClass().getDeclaredFields(); // DogImp.class.getDeclaredFields();
            // 遍历属性
            for (Field field : declaredFields) {
                // 获得属性上的注解
                Seven annotation = field.getAnnotation(Seven.class);
                if (annotation == null) {
                    System.err.println("属性无注入：'" + field.getName() + "'");
                } else {
                    System.err.println("属性注入：'" + field.getName() + "' = '" + annotation.value() + "' (通过 setter）");
                    // ????? 反射调用 public set 方法, 如果为访问级别 private, 那么可以直接使用属性的 set(obj,value);
                    String fieldSetter = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    obj.getClass()
                            .getMethod(fieldSetter, new Class<?>[]{String.class}) // 函数名和参数类型 <=> 函数签名
                            .invoke(obj, annotation.value());                     // 我们知道这个 setter 怎么调用
                }
            }

            System.err.println("== 给 '" + obj + "' 注入方法 ==");
            // 获得所有方法
            /*
                    * Returns an array containing {@code Method} objects reflecting all the
                    * declared methods of the class or interface represented by this {@code
                    * Class} object, including public, protected, default (package)
                    * access, and private methods, but excluding inherited methods.
             */
            Method declaredMethods[] = obj.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                // 获得方法注解
                Seven annotation = method.getAnnotation(Seven.class);
                if (annotation == null) {
                    System.err.println("方法无注入：'" + method.getName() + "'");
                } else {
                    System.err.println("方法注入：'" + method.getName() + "' = '" + annotation.Property() + "'");
                    method.invoke(obj, annotation.Property());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
