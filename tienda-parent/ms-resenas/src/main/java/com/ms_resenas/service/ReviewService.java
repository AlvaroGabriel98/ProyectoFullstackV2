package com.ms_resenas.service;

import com.ms_resenas.exception.ResourceNotFoundException;
import com.ms_resenas.model.Review;
import com.ms_resenas.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public Review saveReview(Review review) {
        review.setId(null);
        return reviewRepository.save(review);
    }

    public Review getReviewId(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la resena con ID: " + id));
    }

    public List<Review> getReviewsPorPuntuacion(Integer puntuacion) {
        return reviewRepository.findByPuntuacion(puntuacion);
    }

    public Review updateReview(Integer id, Review review) {
        Review existente = getReviewId(id);
        existente.setTitulo(review.getTitulo());
        existente.setComentario(review.getComentario());
        existente.setPuntuacion(review.getPuntuacion());
        return reviewRepository.save(existente);
    }

    public void deleteReview(Integer id) {
        Review existente = getReviewId(id);
        reviewRepository.delete(existente);
    }

    public long totalReviews() {
        return reviewRepository.count();
    }
}
