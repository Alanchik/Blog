package com.chahan.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;


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
            //с помощью какого столбца таблица subscribers связыввается с ПК bloggers т.е. с айди
            joinColumns = @JoinColumn(name = "subscriber_id"),
            //а здесь другой столбец с помощью которого будет свзязываться subscribers с какой-то другой таблицы
            inverseJoinColumns = @JoinColumn(name = "blogger_id")
    )
    private List<Blogger> subscriptions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscribers",
            joinColumns = @JoinColumn(name = "blogger_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    private List<Blogger> subscribers;
}
