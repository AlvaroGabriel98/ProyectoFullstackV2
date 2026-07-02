package com.example.ms_resenas.controller;

import com.example.ms_resenas.model.Review;
import com.example.ms_resenas.repository.ReviewRepository;
import com.example.ms_resenas.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> listarReviews(){
        return reviewService.getReviews();
    }

    @PostMapping
    public Review agregarrReview(@RequestBody Review review){
        return reviewService.saveReview(review);
    }

    @GetMapping("{id}")
    public Review buscarReview(@PathVariable Integer id){
        return reviewService.getReviewId(id);
    }

    @DeleteMapping("{id}")
    public String eliminarReview(@PathVariable Integer id){
       return reviewService.deleteReview(id);
    }

    @GetMapping("/total")
    public Integer totalReviews(){
        return reviewService.totalReviews();
    }
}
