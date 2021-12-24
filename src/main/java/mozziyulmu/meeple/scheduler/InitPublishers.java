package mozziyulmu.meeple.scheduler;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.PublisherRepository;
import mozziyulmu.meeple.entity.Publisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitPublishers {
    private final PublisherRepository publisherRepository;

    private void savePublisher(String korName, String engName){
        publisherRepository.save(new Publisher(korName, engName));
    }

    // 관리해야 하는 퍼블리셔 등록
    public void setPublishers(){
        savePublisher("코리아 보드게임즈", "Korea Boardgames Co., Ltd.");
    }
}
