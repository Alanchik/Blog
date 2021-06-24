package com.chahan.blog.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "bloggers")
@NoArgsConstructor
@AllArgsConstructor
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column (name = "password")
    private String password;
}
