/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.platform.notifications;

import java.util.EnumSet;
import org.k5n.mobile.api.NotificationsService;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class AndroidNotificationsService extends NotificationsService {

    @Override
    public boolean isNotificationTypeSupported(NotificationType notificationTypes) {
        return false;
    }

    @Override
    protected void doRegisterForNotificationTypes(NotificationType... notificationTypes) {
        getRemoteNotificationHandler().failToRegisterForRemoteNotifications("not yet implemented");
    }

    @Override
    public EnumSet<NotificationType> getCurrentNotificationTypes() {
        return EnumSet.noneOf(NotificationType.class);
    }
}
