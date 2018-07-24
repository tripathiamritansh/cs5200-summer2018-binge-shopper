package edu.northeastern.cs5200.entity;

import javax.persistence.*;

public class productEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private int price;
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "qty")
    private int qty;

    @Override
    public String toString() {
        return "productEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", image_url='" + image_url + '\'' +
                ", qty=" + qty +
                '}';
    }

    public productEntity(String name, String category, int price, String image_url, int qty) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.image_url = image_url;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
