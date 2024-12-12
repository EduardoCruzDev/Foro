package com.eduardocruzdev.foro.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
public class UserProfile {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(length = 18)
    private String phoneNumber;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String lastName;


    private Date birthday;

    @Column(length = 100)
    private String city;

    @Column(length = 300)
    private String aboutMe;

    @Column(length = 100)
    private String footer;



}
