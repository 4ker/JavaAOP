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
            Field declaredFields[] = obj.getClass().getDeclaredFields();
            // 遍历属性
            for (Field field : declaredFields) {
                // 获得属性上的注解
                Seven annotation = field.getAnnotation(Seven.class);
                if (annotation == null) {
                    System.err.println("属性无注入：'" + field.getName() + "'");
                } else {
                    System.err.println("属性注入：'" + field.getName() + "' = '" + annotation.value() + "'");
                    // ????? 反射调用 public set 方法, 如果为访问级别 private, 那么可以直接使用属性的 set(obj,value);
                    String fieldSetter = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    obj.getClass()
                            .getMethod(fieldSetter, new Class<?>[]{String.class})
                            .invoke(obj, annotation.value());
                }
            }

            System.err.println("== 给 '" + obj + "' 注入方法 ==");
            // 获得类属性
            // 获得所有方法
            Method declaredMethods[] = obj.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                // 获得方法注解
                Seven annotation = method.getAnnotation(Seven.class);
                if (annotation == null) {
                    System.err.println("方法无注入：'" + method.getName() + "'");
                } else {
                    System.err.println("方法注入：'" + method.getName() + "' = '" + annotation.value() + "'");
                    method.invoke(obj, annotation.Property());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
