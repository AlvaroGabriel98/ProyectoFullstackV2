package com.example.ms_resenas.service;

import com.example.ms_resenas.model.Review;
import com.example.ms_resenas.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviews(){
        return reviewRepository.obtenerReviews();
    }

    public Review saveReview(Review review){
        return reviewRepository.guardar(review);
    }

    public Review getReviewId(Integer id){
        return reviewRepository.buscarPorId(id);
    }

    public String deleteReview(Integer id){
        reviewRepository.eliminar(id);
        return "Review eliminada";
    }

    public Integer totalReviews(){
        return reviewRepository.obtenerReviews().size();
    }
}
