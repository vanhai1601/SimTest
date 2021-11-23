package com.hitex.yousim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Base64;

@Entity
@Data
@Table(name = "payment_method")
public class PaymentMethod implements Serializable {
    @Id
    @Column(name = "method_code")
    private String methodCode;

    @Column(name = "method_name")
    private String methodName;

    private String description;

    private int status;

    @Column(name = "checkout_url_live")
    private String checkoutUrlLive;

    @Column(name = "check_order_url_live")
    private String checkOrderUrlLive;

    @Column(name = "checkout_url_sandbox")
    private String checkoutUrlSandbox;

    @Column(name = "check_order_url_sandbox")
    private String checkOrderUrlSandbox;

    @Column(name = "return_url_live")
    private String returnUrlLive;

    @Column(name = "cancel_url_live")
    private String cancelUrlLive;

    @Column(name = "return_url_test")
    private String returnUrlTest;

    @Column(name = "cancel_url_test")
    private String cancelUrlTest;

    private String username;

    @Column(name = "username_test")
    private String usernameTest;

    private String password;

    @Column(name = "password_test")
    private String passwordTest;

    @Column(name = "receiver_email")
    private String receiverEmail;

    private String version;

    @JsonIgnore
    public String getRawPassword() {
        try {
            return new String(Base64.getDecoder().decode(this.password));
        } catch (Exception e) {
            return "";
        }
    }

    @JsonIgnore
    public String getRawPasswordTest() {
        try {
            return new String(Base64.getDecoder().decode(this.passwordTest));
        } catch (Exception e) {
            return "";
        }
    }
}
