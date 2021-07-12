package com.chahan.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "bloggers")
@NoArgsConstructor
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscribers",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "blogger_id")
    )
    private Set<Blogger> subscriptions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscribers",
            joinColumns = @JoinColumn(name = "blogger_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    private Set<Blogger> subscribers;
}
