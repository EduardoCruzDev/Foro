package com.eduardocruzdev.foro.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,unique = true)
    private String email;

    @Column(unique = true)
    private String secondaryEmail;

    @Column(unique = true)
    private String emailToken;

    @Column(nullable = false, unique = true ,length = 30)
    private String username;

    @Column(length =60)
    private String password;

    private boolean enabled;

    private boolean removed;

    private LocalDateTime activated;

    private LocalDateTime lastLogin;


    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private UserProfile profile;


}
