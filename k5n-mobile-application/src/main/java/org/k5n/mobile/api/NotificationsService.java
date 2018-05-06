/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api;

import java.util.Arrays;
import java.util.EnumSet;
import org.k5n.mobile.api.notifications.MockRemoteNotificationHandler;

/**
 *
 * @author Waldemar Kłaczyński
 */
public abstract class NotificationsService {

    public enum NotificationType {
        None,
        Alert,
        Sound,
        Badge
    }

    public interface RemoteNotificationHandler {

        void receivedRemoteNotification(String notification);

        void failToRegisterForRemoteNotifications(String message);

        void didRegisterForRemoteNotifications(String deviceToken);
    }

    private EnumSet<NotificationType> registeredNotificationTypes;

    protected EnumSet<NotificationType> reportedNotificationTypes;

    private RemoteNotificationHandler remoteNotificationHandler = MockRemoteNotificationHandler.INSTANCE;

    protected NotificationsService() {
    }

    public RemoteNotificationHandler getRemoteNotificationHandler() {
        return remoteNotificationHandler;
    }

    public void setRemoteNotificationHandler(RemoteNotificationHandler remoteNotificationHandler) {
        this.remoteNotificationHandler = remoteNotificationHandler == null ? MockRemoteNotificationHandler.INSTANCE : remoteNotificationHandler;
    }

    public final void registerForNotificationTypes(NotificationType... notificationTypes) {
        registeredNotificationTypes = EnumSet.copyOf(Arrays.asList(notificationTypes));
        doRegisterForNotificationTypes(notificationTypes);
    }

    public EnumSet<NotificationType> getRegisteredNotificationTypes() {
        return registeredNotificationTypes;
    }

    public EnumSet<NotificationType> getReportedNotificationTypes() {
        return reportedNotificationTypes;
    }

    public abstract boolean isNotificationTypeSupported(NotificationType notificationTypes);

    protected abstract void doRegisterForNotificationTypes(NotificationType... notificationTypes);

    public abstract EnumSet<NotificationType> getCurrentNotificationTypes();
}
