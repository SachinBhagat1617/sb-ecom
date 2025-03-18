package com.ecomerce.sb_ecom.models;

public class Category {
    private long CategoryId;
    private String name;
/*In your Category model, the constructor is never explicitly called in your code, yet the CategoryId and name values are still being set.
This happens  due to Spring Boot's Jackson-based deserialization mechanism when handling JSON requests in a REST API.*/
    public Category(long CategoryId, String name) {
        this.CategoryId = CategoryId;
        this.name = name;
    }

    public long getId() {
        return CategoryId;
    }

    public void setId(long CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
