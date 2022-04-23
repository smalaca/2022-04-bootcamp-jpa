package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@ToString
@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactDetails {
    @Column(nullable = false)
    private String login;
    @Column(length = 20)
    private String phone;
    @Column(length = 150)
    private String mail;

    public ContactDetails(String login, String phone, String mail) {
        this.login = login;
        this.phone = phone;
        this.mail = mail;
    }
}
