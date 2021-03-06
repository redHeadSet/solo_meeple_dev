package mozziyulmu.meeple.service;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.NewsRepository;
import mozziyulmu.meeple.dto.NewsListDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsService {
    private final NewsRepository newsRepository;

    public List<NewsListDto> getNewsList() {
        return newsRepository.findAllByOrderByWriteTimeDesc()
                .stream().map(
                        news -> new NewsListDto(news)
                ).collect(Collectors.toList());
    }
}
