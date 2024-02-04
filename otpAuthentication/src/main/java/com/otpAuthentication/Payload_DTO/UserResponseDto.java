package com.otpAuthentication.Payload_DTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    int user_Id;
    String username;
    String email;
    boolean verified;
    String message;
}
