package edu.library.libraryservice.controller;

import edu.library.libraryservice.dto.LibraryResponse;
import edu.library.libraryservice.model.Notification;
import edu.library.libraryservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Operation(summary = "Notifications", description = "Get the notifications for user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Notifications retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/getAllNotification/{userId}")
    public ResponseEntity<LibraryResponse<List<Notification>>> getUnreadNotifications(@PathVariable String userId) {
        List<Notification> notifications = notificationService.checkDueDatesAndNotifyUsers(userId);
        return ResponseEntity.ok(new LibraryResponse<>(HttpStatus.OK.name(), "Notifications Retrieved Successfully", notifications));
    }

    @PostMapping("/read/{userId}")
    public ResponseEntity<LibraryResponse<String>> markNotificationAsRead(@PathVariable String userId) {
        try {
            notificationService.markAllAsReadByUserId(userId);
            return ResponseEntity.ok(new LibraryResponse<>(HttpStatus.OK.name(), "Notification marked as read", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LibraryResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Failed to update"));
        }
    }


}

