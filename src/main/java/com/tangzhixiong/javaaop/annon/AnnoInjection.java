package com.tangzhixiong.javaaop.annon;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnoInjection {

    public static Object getBean(Object obj) {
        try {
            
            // 获得类属性
            Field declaredFields[] = obj.getClass().getDeclaredFields();
            // 遍历属性
            for (Field field : declaredFields) {
                // 获得属性上的注解
                Seven s = field.getAnnotation(Seven.class);
                if (s == null) { continue; }
                System.err.println(" 注入 " + field.getName() + " 属性 " + "\t\t" + s.value());
                // 反射调用 public set 方法, 如果为访问级别 private, 那么可以直接使用属性的 set(obj,value);
                String fieldSetter = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                System.out.println(" 属性 setter 名称为：" + fieldSetter);
                obj.getClass()
                        .getMethod(fieldSetter, new Class<?>[]{String.class})
                        .invoke(obj, s.value());
            }
            
            // 获得所有方法
            Method declaredMethods[] = obj.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                // 获得方法注解
                Seven s = method.getAnnotation(Seven.class);
                if (s != null) {
                    System.err.println(" 注入 " + method.getName() + " 方法 " + "\t" + s.Property());
                    method.invoke(obj, s.Property());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
