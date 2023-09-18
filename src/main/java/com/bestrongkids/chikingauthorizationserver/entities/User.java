package com.bestrongkids.chikingauthorizationserver.entities;

import com.bestrongkids.chikingauthorizationserver.entities.enums.EncryptionAlgorithm;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String name;

    private Email email;

    private String password;

    private int loginCount;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    private LocalDateTime lastLoginAt;

    private LocalDateTime createAt;


}
