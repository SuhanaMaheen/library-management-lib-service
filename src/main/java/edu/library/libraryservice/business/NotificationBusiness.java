package edu.library.libraryservice.business;

import edu.library.libraryservice.model.LibraryDetails;
import edu.library.libraryservice.model.Notification;

import java.util.List;

public interface NotificationBusiness {
    List<LibraryDetails> getUnreturnedBooksByUserId(String userId);

    List<Notification> findByUserIdAndIsReadFalse(String userId);

    Notification saveNotification(Notification notification);

    void markAllAsReadByUserId(String userId);
}
