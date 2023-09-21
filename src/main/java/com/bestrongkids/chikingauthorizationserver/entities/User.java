package com.bestrongkids.chikingauthorizationserver.entities;

import com.bestrongkids.chikingauthorizationserver.entities.enums.EncryptionAlgorithm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private int loginCount;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities;

    private LocalDateTime lastLoginAt;

    private LocalDateTime createAt;

    private boolean nonExpired;

    private boolean nonBlocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    public User() {

    }
}
