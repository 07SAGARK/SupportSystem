package com.example.crudOprt.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordResetService {

    @Autowired
    private EmailService service;
    public String generateOtp(){
        String otp = String.format("%06d", new Random().nextInt(1000000));
        return otp;
    }

    public String sendOTP(String email){

        String otp=generateOtp();
        service.sendEmail(email,"OTP for Password Reset","Here is your OTP for changing the Password! Please do not share it with anyone\n"+otp);
        return otp;
    }


}
