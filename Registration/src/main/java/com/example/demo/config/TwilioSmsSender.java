package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service("twilio")
public class TwilioSmsSender {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }
    
    @Autowired
    private UserRepository userRepository;

    public void sendSms(User user) {
    	userRepository.save(user);
        if (isPhoneNumberValid(user.getMobnumber())) {
            PhoneNumber to = new PhoneNumber(user.getMobnumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = "User Registration Successful"+" "+user.getUserName();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", user);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + user.getMobnumber() + "] is not a valid number"
            );
        }

    }

    private boolean isPhoneNumberValid(String mobnumber) {
        // TODO: Implement phone number validator
        return true;
    }

	
}
