package com.example.crudOprt.Controller;

import com.example.crudOprt.Service.EmailService;
import com.example.crudOprt.Service.PasswordResetService;
import com.example.crudOprt.Service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/forgot")
public class forgotPasswordController {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private PasswordResetService resetService;

    @PostMapping("/email")
    public ResponseEntity<?> getEmail(@RequestBody EmailRequest request){
        return service.getEmail(request.getEmail());
    }
    @PostMapping("/send")
    public ResponseEntity<?> sendOtp(@RequestBody EmailRequest request, HttpSession session){
       String otp= resetService.sendOTP(request.getEmail());

       session.setAttribute("OTP",otp);
       session.setAttribute("OTP_Email",request.getEmail());
       session.setAttribute("OTP_Time", System.currentTimeMillis());

       return ResponseEntity.ok().body("OTP Sent Successful");
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequestBody request, HttpSession session){
        String email=(String)session.getAttribute("OTP_Email");
        String otp= (String) session.getAttribute("OTP");
        Long time=(Long) session.getAttribute("OTP_Time");
        long currentTime=System.currentTimeMillis();
        if (time==null || currentTime-time>5*60*1000){
            session.removeAttribute("OTP");
            return ResponseEntity.badRequest().body("OTP Expired!!");
        }
        if (email==null || otp==null){
            return ResponseEntity.badRequest().body("OTP Expired!!");
        }
        if (!email.equals(request.getEmail())){
            return ResponseEntity.badRequest().body("Invalid Email");
        }
        if (!otp.equals(request.getOtp())){
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
        session.setAttribute("OTP_Verified", true);
        return ResponseEntity.ok().body("OTP Verified");

    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody LoginRequest request, HttpSession session){
        Boolean verified=(Boolean) session.getAttribute("OTP_Verified");
        String userEmail=(String) session.getAttribute("OTP_Email");

        if (verified==null || !verified){

            return ResponseEntity.badRequest().body("OTP Not Verified");
        }
        if ( userEmail==null || !userEmail.equals(request.getUsername())){

            return ResponseEntity.badRequest().body("Invalid Email");
        }

        session.removeAttribute("OTP");
        session.removeAttribute("OTP_Email");
        session.removeAttribute("OTP_Time");
        session.removeAttribute("OTP_Verified");
        return service.updatePassword(userEmail,request.getPassword());
    }




}
