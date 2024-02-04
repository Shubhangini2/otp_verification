package com.otpAuthentication.Service;

import com.otpAuthentication.Model.User;
import com.otpAuthentication.Payload_DTO.UserRequestDto;
import com.otpAuthentication.Payload_DTO.UserResponseDto;
import com.otpAuthentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    public UserResponseDto addUser(UserRequestDto userRequestDto){

        UserResponseDto response= new UserResponseDto();

        Optional<User> optionalUser = userRepository.findByEmail(userRequestDto.getEmail());
        if(optionalUser.isPresent()){
            response.setMessage("User Already Registered!!");
        }
        else{
            //System generated OTP
            Random r = new Random();
            String generatedOtp = String.format("%06d", r.nextInt(100000));
            User newUser= new User();
            newUser.setUsername(userRequestDto.getUsername());
            newUser.setEmail(userRequestDto.getEmail());
            newUser.setOtp(generatedOtp); //OTP OF 6  DIGITS
            newUser.setVerified(false);
           User savedUser =  userRepository.save(newUser);
           String subject = "Email Verification";
           String text= "Your verification OTP is"+ savedUser.getOtp();

           //Send Email
            this.emailService.sendEmail(savedUser.getEmail(), subject,text);

            //Response
            response.setUser_Id(savedUser.getId());
            response.setUsername(savedUser.getUsername());
            response.setEmail(savedUser.getEmail());
            response.setMessage("OTP sent successfully");
        }
        return response;
    }


    //FOR VERIFICATION
    public String verifyUser(String email, String otp){
        String response="";
       Optional<User> optionalUser = userRepository.findByEmail(email);
       if(optionalUser.isPresent() && optionalUser.get().isVerified()){
              response = "User is already verified!!";
       }
       else if(otp.equals(optionalUser.get().getOtp())) {
           optionalUser.get().setVerified(true);
           userRepository.save(optionalUser.get());
           response = "User verified successfully!!";
       }

       else{
           response= "User not verified!!";
       }
       return response;
    }
}
