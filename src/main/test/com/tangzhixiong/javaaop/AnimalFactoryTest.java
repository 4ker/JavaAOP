package com.tangzhixiong.javaaop;

import com.tangzhixiong.javaaop.imp.AOPMethod;
import com.tangzhixiong.javaaop.imp.AnimalInterface;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnimalFactoryTest {

    @Test
    public void should_inject() throws Exception {
            AnimalInterface dog = AnimalFactory.getAnimal(DogImp.class, new AOPMethod() {
            // 这里写方法执行前的 AOP 切入方法
            public void before(Object proxy, Method method, Object[] args) {
                if (method.getName().equals("getProperty")) {
                    System.err.println("\t\t成功拦截 '" + method.getName() + "' 方法, 启动 ");
                }
            }

            // 这里系方法执行后的 AOP 切入方法
            public void after(Object proxy, Method method, Object[] args) {
                if (method.getName().equals("getProperty"))
                    System.err.println("\t\t成功拦截 '" + method.getName() + "' 方法, 结束 ");

            }
        });
        assertThat(dog.getName(), is("坏🐶"));
        assertThat(dog.setName("好🐶").getName(), is("好🐶"));
        assertThat(dog.getProperty(), is("水陆两栖战士"));
        assertThat(dog.setProperty("不会游泳").getProperty(), is("不会游泳"));
    }
}