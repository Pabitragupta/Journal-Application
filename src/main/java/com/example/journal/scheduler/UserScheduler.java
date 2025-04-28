package com.example.journal.scheduler;


import com.example.journal.cache.AppCache;
import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.enums.Sentiment;
import com.example.journal.repository.UserRepository;
import com.example.journal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppCache appCache;


    //base on schedule give the mail to the user in a specific time and date
    @Scheduled(cron = "0 0 9 * * SUN") // Every Sunday at 9 AM
    public void fetchUsersAndSendSaMail() {

        List<User> users = userRepository.getUserForSA();

        for (User user : users) {
            List<JournalEntity> journalEntries = user.getJournalEntries();


            // ✅ No 7 days filter - take all journal entries
            List<Sentiment> sentiments = journalEntries.stream()
                    .map(JournalEntity::getSentiment)
                    .collect(Collectors.toList());


            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                String subject = "Your Journal Sentiment Analysis";
                String body = "Hi " + user.getUserName() + ",\n\n" +
                        "Here is your overall journal sentiment: " + mostFrequentSentiment + ".\n" +
                        "Keep writing and expressing yourself!\n\n" +
                        "Best Regards,\nJournal App Team";

                emailService.sendEmail(user.getEmail(), subject, body);
            }
        }
    }

    @Scheduled(cron = "0 */10 * * * *") // Every 10 minutes
    public void clearAppCache() {
        appCache.init();
    }
}
