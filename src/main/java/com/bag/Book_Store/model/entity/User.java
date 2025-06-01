package com.bag.Book_Store.model.entity;

import com.bag.Book_Store.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Enumerated(EnumType.STRING)
    private Role role;

}
