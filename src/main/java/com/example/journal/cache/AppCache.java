package com.example.journal.cache;

import com.example.journal.entity.ConfigJournalAppEntity;
import com.example.journal.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }
    
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCaches;

    @PostConstruct
    public void init(){
        appCaches = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();

        for(ConfigJournalAppEntity configJournalAppEntity : all){
            appCaches.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
