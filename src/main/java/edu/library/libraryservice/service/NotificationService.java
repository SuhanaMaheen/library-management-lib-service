package edu.library.libraryservice.service;

import edu.library.libraryservice.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> checkDueDatesAndNotifyUsers(String userId);

    void markAllAsReadByUserId(String userId);

}
