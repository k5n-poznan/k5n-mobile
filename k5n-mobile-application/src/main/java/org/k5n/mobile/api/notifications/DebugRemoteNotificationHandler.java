/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.notifications;

import org.k5n.mobile.api.NotificationsService;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class DebugRemoteNotificationHandler implements NotificationsService.RemoteNotificationHandler {

    @Override
    public void receivedRemoteNotification(String s) {
        System.err.println("receivedRemoteNotification: " + s);
    }

    @Override
    public void failToRegisterForRemoteNotifications(String s) {
        System.err.println("failToRegisterForRemoteNotifications: " + s);
    }

    @Override
    public void didRegisterForRemoteNotifications(String s) {
        System.err.println("didRegisterForRemoteNotifications: " + s);
    }
}
