package mozziyulmu.meeple.service;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.UserRepository;
import mozziyulmu.meeple.dto.UserSignUpDto;
import mozziyulmu.meeple.dto.UserUpdateDto;
import mozziyulmu.meeple.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public Boolean checkDupEMail(String email){
        return !StringUtils.hasText(email) || userRepository.checkDupEMail(email) == 0;
    }

    public Boolean checkDupNickName(String nickname){
        return !StringUtils.hasText(nickname) ||userRepository.checkDupNickName(nickname) == 0;
    }

    public Boolean signUp(UserSignUpDto userSignUpDto){
        if (!checkDupEMail(userSignUpDto.getEmail())
                || !checkDupNickName(userSignUpDto.getNickname()))
            return false;

        userRepository.save(
                new User(
                    userSignUpDto.getEmail(),
                    userSignUpDto.getPassword(),
                    userSignUpDto.getNickname())
        );
        return true;
    }

    public Boolean update(UserUpdateDto userUpdateDto) {
        Optional<User> findUser = userRepository.findByEmailAndPassword(userUpdateDto.getEmail(), userUpdateDto.getPassword());
        if (!findUser.isPresent())
            return false;

        User user = findUser.get();

        // 닉네임 변경
        if (StringUtils.hasText(userUpdateDto.getNickname())){
            String newNickName = userUpdateDto.getNickname();
            if(!checkDupNickName(newNickName)){
                return false;
            }

            user.updateNickName(newNickName);
        }

        // 프로필 이미지 변경
        if (userUpdateDto.getProfileImage() != null){
            boolean haveAlreadyImage = (user.getUserProfileImage() != null) ? true : false;
            String oldImagePath = haveAlreadyImage
                                ? user.getUserProfileImage().getPath()
                                : "";

            // 신규 파일 처리
            String newFileName = "D:\\UserImage\\"
                    + user.getNickname() + "_"
                    + userUpdateDto.getProfileImage().getOriginalFilename();
            File newImage = new File(newFileName);
            try {
                userUpdateDto.getProfileImage().transferTo(newImage);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            if(haveAlreadyImage){
                user.getUserProfileImage().updateImage(newFileName);

                // 기존 파일 삭제
                File oldImage = new File(oldImagePath);
                oldImage.delete();
            }
            else{
                user.setProfileImage(newFileName);
            }
        }

        return true;
    }
}
