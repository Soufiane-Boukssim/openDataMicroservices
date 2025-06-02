package com.soufiane.authservice.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity @Getter @Setter @NoArgsConstructor @SuperBuilder
public class Provider extends User{
    private String providerAttribut1;
    private String providerAttribut2;
}
