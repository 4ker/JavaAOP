package com.tangzhixiong.javaaop;

import com.tangzhixiong.javaaop.annon.Seven;
import com.tangzhixiong.javaaop.imp.AnimalInterface;

public class DogImp implements AnimalInterface {

//  @Seven，这个注解会注入默认的值，也就是"🐶"
//  @Seven("坏🐶")，这个也等价，如果注解里面的 KEY=VALUE 没有 KEY，默认就是 value=VALUE。
    @Seven(value = "坏🐶")
    private String name;

    // 无注入
    private String property;

    @Override
    public DogImp setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    @Seven(Property = "水陆两栖战士")
    public DogImp setProperty(Object Property) {
        this.property = (String)Property;
        return this;
    }

    @Override
    public String getProperty() {
        return this.property;
    }
}
