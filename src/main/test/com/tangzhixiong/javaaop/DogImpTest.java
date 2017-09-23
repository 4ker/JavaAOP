package com.tangzhixiong.javaaop;

import com.tangzhixiong.javaaop.imp.AnimalInterface;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class DogImpTest {
    @Test
    public void should_fail_to_inject() throws Exception {
        // 直接拿，注解肯定没有生效，必须要经过 proxy 这一层转化
        // Butler Lampson：
        //      All problems in computer science can be solved by another level of indirection
        AnimalInterface animal = new DogImp();
        assertNull(animal.getName());
        assertNull(animal.getProperty());
    }
}