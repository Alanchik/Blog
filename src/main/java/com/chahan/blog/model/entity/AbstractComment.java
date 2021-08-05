package com.chahan.blog.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorOptions(force = true)
public abstract class AbstractComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Blogger author;

    @Column(name = "text")
    private String text;

    @Column(name = "published")
    private LocalDateTime published;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "comment_likes"
            , joinColumns = @JoinColumn(name = "comment_id")
            , inverseJoinColumns = @JoinColumn(name = "blogger_id")
    )
    private List<Blogger> bloggerLikes;
}
