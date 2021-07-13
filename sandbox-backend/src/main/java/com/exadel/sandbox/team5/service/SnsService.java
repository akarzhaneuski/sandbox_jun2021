package com.exadel.sandbox.team5.service;

import com.amazonaws.services.sns.model.PublishResult;
import com.exadel.sandbox.team5.util.Notification;

public interface SnsService {

    PublishResult sendToAllUsers(Notification notification);
}
