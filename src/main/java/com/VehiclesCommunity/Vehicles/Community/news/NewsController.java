package com.VehiclesCommunity.Vehicles.Community.news;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/all")
    public ResponseEntity <Map<String, List<News>>> getNews(){
        return ResponseEntity.ok(Map.of("data", newsService.getNews()));
    }

    @GetMapping("/{id}")
    public ResponseEntity <Map<String, Optional<News>>> getNews(@PathVariable Integer id){
        return ResponseEntity.ok(Map.of("data", newsService.getNews(id)));
    }

    @PostMapping("/add")
    public ResponseEntity <Map<String,String>> addNewNews(@RequestBody News news){
        newsService.addNewNews(news);
        return ResponseEntity.ok(Map.of("message", "News added successfully"));
    }

    @DeleteMapping("/deleteNews/{id}")
    public ResponseEntity<Map<String, String>> deleteNews(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        boolean exists = newsService.deleteNews(id);
        if (!exists) {
            response.put("message", "News not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("message","Event is deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
