package com.tangzhixiong.javaaop.imp;

public interface AnimalInterface {

    <T extends AnimalInterface> T setName(String name);
    String getName();

    Object getProperty();
    <T extends AnimalInterface> T setProperty(Object Property);
}
