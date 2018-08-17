package edu.northeastern.cs5200.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Lob
    @Column(length = 1000000)
    private String description;
    @Column(name = "image_url")
    private String image_url;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    public ProductEntity() {
    }

    public ProductEntity(int id, String name, String description, String image_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
