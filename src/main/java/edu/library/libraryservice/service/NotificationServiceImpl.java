package edu.library.libraryservice.service;

import edu.library.libraryservice.business.NotificationBusiness;
import edu.library.libraryservice.model.LibraryDetails;
import edu.library.libraryservice.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationBusiness notificationBusiness;// Redis cache

    public List<Notification> checkDueDatesAndNotifyUsers(String userId) {
        List<Notification> notifications = new ArrayList<>();

        LocalDateTime reminderDate = LocalDateTime.now().plusDays(3); // Get the date 3 days ahead
        List<LibraryDetails> details = notificationBusiness.getUnreturnedBooksByUserId(userId); // Fetch unreturned books by the user

        // Filter records that have due date exactly 3 days from now
        List<LibraryDetails> dueInThreeDays = details.stream()
                .filter(record -> record.getDueDate().toLocalDate().isEqual(reminderDate.toLocalDate()))
                .toList();

        // Now from list of books due in 3 days, can create notifications
        for (LibraryDetails record : dueInThreeDays) {
            // Create and save notification
            Notification notification = new Notification();
            notification.setUserId(Long.valueOf(record.getUserId()));
            notification.setMessage("Reminder: Your borrowed book '" + record.getBook().getTitle() +
                    "' is due on " + record.getDueDate());
            notification.setCreatedAt(LocalDateTime.now());
            notification.setRead(false);
            notificationBusiness.saveNotification(notification);
        }
        // Fetch notifications from DB after creating new ones
        notifications = notificationBusiness.findByUserIdAndIsReadFalse(userId);
        return notifications;
    }

    @Override
    public void markAllAsReadByUserId(String userId) {
         notificationBusiness.markAllAsReadByUserId(userId);
    }
}

