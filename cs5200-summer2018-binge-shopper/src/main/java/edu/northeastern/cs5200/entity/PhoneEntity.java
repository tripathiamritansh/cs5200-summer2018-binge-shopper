package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "primary_phone")
    private boolean primary;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_phone_association"))
    private UserEntity user;

    @Override
    public String toString() {
        return "PhoneEntity{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", primary=" + primary +
                ", user=" + user +
                '}';
    }

    public PhoneEntity() {
    }

    public PhoneEntity(String phone, boolean primary, UserEntity user) {
        this.phone = phone;
        this.primary = primary;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
