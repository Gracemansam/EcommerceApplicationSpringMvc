package com.example.main_week7.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Component
public class UserRegistrationDao {
    @NotBlank(message = "Name is mandatory")
    private String firstName;

    @NotBlank(message = "Name is mandatory")
    private String lastName;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}
