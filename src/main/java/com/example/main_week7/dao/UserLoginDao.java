package com.example.main_week7.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Component
public class UserLoginDao implements Serializable {
    private String email;
    private String password;

}
