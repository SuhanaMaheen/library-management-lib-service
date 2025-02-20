package edu.library.libraryservice.business;

import edu.library.libraryservice.model.LibraryDetails;
import edu.library.libraryservice.model.Notification;
import edu.library.libraryservice.repository.LibraryDetailsRepository;
import edu.library.libraryservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationBusinessImpl implements NotificationBusiness {
    @Autowired
    private LibraryDetailsRepository libraryDetailsRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<LibraryDetails> getUnreturnedBooksByUserId(String userId) {
        return libraryDetailsRepository.findUnreturnedBooksByUserId(userId);
    }

    @Override
    public List<Notification> findByUserIdAndIsReadFalse(String userId) {
        return notificationRepository.findByUserIdAndIsReadFalse(Long.valueOf(userId));
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void markAllAsReadByUserId(String userId) {
        notificationRepository.markAllAsReadByUserId(userId);
    }
}
