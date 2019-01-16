package com.example.dell.Store;

public class Product {
    private String name, id;
    private String price, quentity;

    public Product(String name, String price, String quentity) {
        this.name = name;
        this.price = price;
        this.quentity = quentity;
    }
    public Product() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuentity() {
        return quentity;
    }

    public void setQuentity(String quentity) {
        this.quentity = quentity;
    }


}
