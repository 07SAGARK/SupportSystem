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
    public ResponseEntity<?> getEmail(@RequestParam("email") String email){
        return service.getEmail(email);
    }
    @PostMapping("/send")
    public ResponseEntity<?> sendOtp(@RequestParam("email") String email, HttpSession session){
       String otp= resetService.sendOTP(email);
       session.setAttribute("OTP",otp);
       session.setAttribute("OTP_Email", email);
       session.setAttribute("OTP_Time", System.currentTimeMillis());

       return ResponseEntity.ok().body("OTP Sent Successful");
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequestBody request, HttpSession session){
        String email=(String)session.getAttribute("OTP_Email");
        String otp= (String) session.getAttribute("OTP");
        Long time=(Long) session.getAttribute("OTP_Time");
        long currentTime=System.currentTimeMillis();
        if (currentTime-time>5*60*1000){
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
        if (!verified){
            return ResponseEntity.badRequest().body("OTP Not Verified");
        }
        if (userEmail!=request.getUsername() || userEmail==null){
            return ResponseEntity.badRequest().body("Invalid Email");
        }
        service.updatePassword(userEmail,request.getPassword());
        session.removeAttribute("OTP");
        session.removeAttribute("OTP_Email");
        session.removeAttribute("OTP_Time");
        session.removeAttribute("OTP_Verified");
        return ResponseEntity.ok().body("Password Updated Successfully");
    }




}
