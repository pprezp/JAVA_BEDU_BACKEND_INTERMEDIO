package org.bedu.module3Project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class UserModel {
    @GeneratedValue
    @Id
    private Long id;

    @NotEmpty( message = "El campo name no puede estar vacío" )
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @NotEmpty( message = "El campo lastname no puede estar vacío" )
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @NotEmpty( message = "El campo username no puede estar vacío" )
    @Size(min = 8, max = 30, message = "El nombre de usuario debe tener un minimo de 8 caracteres y un máximo de 30")

    @Column(name = "username", nullable = false, length = 30, unique = true)
    private String username;

    @NotEmpty( message = "El campo email no puede estar vacío" )
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;


    @NotEmpty( message = "El campo password no puede estar vacío" )
    @Size(min = 8, max = 30, message = "La contrasena debe tener un minimo de 8 caracteres y un máximo de 30")
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
