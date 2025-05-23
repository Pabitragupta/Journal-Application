package com.example.journal.entity;

import com.example.journal.enums.Sentiment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "journal_entity")
public class JournalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String title;

    private String content;

    private Sentiment sentiment;

    //    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    @JsonBackReference
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

}