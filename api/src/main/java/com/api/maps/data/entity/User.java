package com.api.maps.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Size(min = 3, max = 120)
    @Column(nullable = false)
    private String username;

    @Size(min = 3, max = 120)
    @Column(nullable = false)
    private String password;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Column(columnDefinition = "boolean default true")
    private boolean active;
}
