package com.microservice.alumnos.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderService {

    @Value("${account.sid}")
    private String account_id;

    @Value("${auth.token}")
    private String auth_token;

    @PostConstruct
    private void setup(){
        Twilio.init(account_id, auth_token);
    }

    public String sendMessage(String smsnumber, String smsmessage){
        Message message = Message.creator(
                new PhoneNumber(smsnumber),
                new PhoneNumber("+18508100749"), smsmessage).create();
        return message.getStatus().toString();
    }

}
