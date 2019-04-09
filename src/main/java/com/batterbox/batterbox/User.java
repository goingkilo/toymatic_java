package com.batterbox.batterbox;

public class User {

    private String name,address,cellNumber;

    public User(String name, String cellNumber, String address) {
        this.name = name;
        this.cellNumber = cellNumber;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String getName() {
        return name;
    }
}
