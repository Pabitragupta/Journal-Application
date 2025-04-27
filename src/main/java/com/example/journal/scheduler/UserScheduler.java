package com.example.journal.scheduler;

import com.example.journal.cache.AppCache;
import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.repository.UserRepository;
import com.example.journal.service.EmailService;
import com.example.journal.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 * * ? * *") // Runs every minute
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepository.getUserForSA();

        users.parallelStream().forEach(user -> { // ✨ parallelStream for faster processing
            String allContent = user.getJournalEntries()
                    .stream()
                    .map(JournalEntity::getContent)
                    .collect(Collectors.joining(" ")); // joins all journal content in one step

            if (!allContent.isBlank()) { // Check to avoid sending empty emails
                String sentiment = sentimentAnalysisService.getSentiment(allContent);

                emailService.sendEmail(
                        "pabitragupta947@gmail.com", // send to user's real email
                        "Your Journal Sentiment Analysis",
                        "Hi " + user.getUserName() + ",\n\n" + "Here is the sentiment analysis of your journals: " + sentiment
                );
            }
        });
    }


    @Scheduled(cron = "0 */10 * * * *")
    public void clearAppCache(){
        appCache.init();
    }
}
