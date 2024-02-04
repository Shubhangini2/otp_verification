package com.otpAuthentication.Controller;

import com.otpAuthentication.Model.User;
import com.otpAuthentication.Payload_DTO.UserRequestDto;
import com.otpAuthentication.Payload_DTO.UserResponseDto;
import com.otpAuthentication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){

        UserResponseDto responseDto= userService.addUser(userRequestDto);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }


    @PostMapping("/verification")
    public ResponseEntity verifyUser(@RequestParam String email, @RequestParam String otp){
        String response = userService.verifyUser(email,otp);
        return new ResponseEntity(response,HttpStatus.OK);
    }


}
