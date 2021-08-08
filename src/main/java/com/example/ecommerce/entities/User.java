package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Pattern(regexp = "^[A-Z]+(.)*")
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank
    @Email(message = "Campo inválido")
    @Column(length = 50, nullable = false)
    private String email;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String phone;

    @NotBlank
    @Length(min = 5)
    @Column(length = 20, nullable = false)
    private String password;

    //mappedBy: nome do atributo na outra ponta do relacionamento, ou seja na classe Client
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
