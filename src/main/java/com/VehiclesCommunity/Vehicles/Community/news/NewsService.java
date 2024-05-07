package com.VehiclesCommunity.Vehicles.Community.news;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getNews() {
        return new ArrayList<>(newsRepository.findAll());

    }
    public Optional<News> getNews(Integer id) {
        return newsRepository.findById(id);

    }

    public void addNewNews(News news) {
        newsRepository.save(news);
    }

    public boolean deleteNews(Integer id) {
        Optional<News> news = newsRepository.findById(id);
        if(news.isEmpty()) {
            return false;
        }
        newsRepository.deleteById(id);
        return true;
    }
}
