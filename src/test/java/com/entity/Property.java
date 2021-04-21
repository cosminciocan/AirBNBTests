package com.entity;

public class Property {


    private String Name;
    private String pricePerNight;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(String pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Property{" +
                "Name='" + Name + '\'' +
                ", pricePerNight='" + pricePerNight + '\'' +
                '}';
    }
}
