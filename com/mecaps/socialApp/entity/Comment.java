package com.mecaps.socialApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentString;

    @ManyToOne
    private Post postId;

    @ManyToOne
    private User author;

    @DateTimeFormat
    @CreationTimestamp
    private String commentedAt;
}
