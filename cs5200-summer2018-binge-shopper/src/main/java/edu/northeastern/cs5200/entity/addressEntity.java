package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

public class addressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "street1")
    private String street1;
    @Column(name = "street2")
    private String street2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip")
    private int zip;
    @Column(name = "primary")
    private Boolean primary;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_address_association"))
    private int userId;

    public addressEntity(String street1, String street2, String city, String state, int zip, Boolean primary, int userId) {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.primary = primary;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "addressEntity{" +
                "id=" + id +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", primary=" + primary +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
