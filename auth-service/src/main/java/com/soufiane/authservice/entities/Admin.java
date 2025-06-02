package com.soufiane.authservice.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity @Getter @Setter @NoArgsConstructor @SuperBuilder
public class Admin extends User{
    private String adminAttribut;
}
