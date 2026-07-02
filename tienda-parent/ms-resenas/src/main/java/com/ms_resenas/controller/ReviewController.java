package com.ms_resenas.controller;

import com.ms_resenas.model.Review;
import com.ms_resenas.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/resenas", "/api/v1/review"})
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> listarReviews() {
        return ResponseEntity.ok(reviewService.getReviews());
    }

    @PostMapping
    public ResponseEntity<Review> agregarReview(@Valid @RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.saveReview(review));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> buscarReview(@PathVariable Integer id) {
        return ResponseEntity.ok(reviewService.getReviewId(id));
    }

    @GetMapping("/puntuacion/{puntuacion}")
    public ResponseEntity<List<Review>> buscarPorPuntuacion(@PathVariable Integer puntuacion) {
        return ResponseEntity.ok(reviewService.getReviewsPorPuntuacion(puntuacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> actualizarReview(@PathVariable Integer id, @Valid @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.updateReview(id, review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> totalReviews() {
        return ResponseEntity.ok(Map.of("total", reviewService.totalReviews()));
    }
}
