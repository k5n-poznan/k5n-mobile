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
public class MockRemoteNotificationHandler implements NotificationsService.RemoteNotificationHandler {

    public final static MockRemoteNotificationHandler INSTANCE = new MockRemoteNotificationHandler();

    private MockRemoteNotificationHandler() {
    }

    @Override
    public void receivedRemoteNotification(String s) {
    }

    @Override
    public void failToRegisterForRemoteNotifications(String s) {
    }

    @Override
    public void didRegisterForRemoteNotifications(String s) {
    }
}
