package com.scpc.springsecurityclient.event.listener;

import com.scpc.springsecurityclient.entity.User;
import com.scpc.springsecurityclient.event.RegistrationCompleteEvent;
import com.scpc.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // TODO: Create the Verification Token for User with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);

        // TODO: Send mail to User
        String url =
                event.getApplicationUrl()
                        + "/verifyRegistration?token="
                        + token;

        // TODO: sendVerificationEmail()
        log.info("Click the link to verify your account: {}", url);
    }
}
