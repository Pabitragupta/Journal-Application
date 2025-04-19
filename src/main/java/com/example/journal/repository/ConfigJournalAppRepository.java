package com.example.journal.repository;


import com.example.journal.entity.ConfigJournalAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfigJournalAppRepository extends JpaRepository<ConfigJournalAppEntity, String> {

}