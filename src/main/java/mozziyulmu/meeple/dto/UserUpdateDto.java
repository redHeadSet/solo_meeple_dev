package mozziyulmu.meeple.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserUpdateDto {
    private String email;
    private String password;

    // updatable
    private String nickname;
    MultipartFile profileImage;
}
