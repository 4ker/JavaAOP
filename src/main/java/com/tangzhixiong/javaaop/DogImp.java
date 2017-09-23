package com.tangzhixiong.javaaop;

import com.tangzhixiong.javaaop.annon.Seven;
import com.tangzhixiong.javaaop.imp.AnimalInterface;

public class DogImp implements AnimalInterface {

    @Seven(value = "Lumia")
    private String name;

    private String Property;

    public DogImp() {
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void say() {
        System.out.println("Ð¡¹·:ÍôÍôÍôÍô.....");
    }

    @Override
    @Seven(Property = "Ë®Â½Á½ÆÜÕ½Ê¿")
    public void setProperty(String Property) {
        this.Property = Property;
    }

    @Override
    public void getProperty() {
        System.out.println(this.name + this.Property);
    }
}
