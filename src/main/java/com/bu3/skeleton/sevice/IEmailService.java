package com.bu3.skeleton.sevice;

public interface IEmailService {

    void send(String to, String email, String subject);

    String buildEmailWelcome(String email);
}
