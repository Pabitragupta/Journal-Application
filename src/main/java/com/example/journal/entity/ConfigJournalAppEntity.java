package com.example.journal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "config_journal_app")
public class ConfigJournalAppEntity {

    @Id
    private String key;

    private String value;
}
