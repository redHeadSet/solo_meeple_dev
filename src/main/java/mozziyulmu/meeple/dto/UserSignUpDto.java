package mozziyulmu.meeple.dto;

import lombok.*;

@Data
public class UserSignUpDto {
    private String email;
    private String password;
    private String nickname;
}
