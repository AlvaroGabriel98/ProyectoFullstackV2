package com.example.ms_resenas.repository;

import com.example.ms_resenas.model.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {
    private List<Review> listaReviews = new ArrayList<>();

    public ReviewRepository() {
        listaReviews.add(new Review(1,"Titulo","Comentario",5));
    }

    public List<Review> obtenerReviews() {
        return listaReviews;
    }

    public Review buscarPorId(Integer id){
        for (Review review : listaReviews) {
            if (review.getId().equals(id)) {
                return review;
            }
        }
        return null;
    }

    public Review guardar(Review review) {
        if (buscarPorId(review.getId()) == null) {
            listaReviews.add(review);
            return review;
        }
        return null;
    }

    public void eliminar(Integer id) {
        listaReviews.remove(buscarPorId(id));
    }

    public Integer totalConsolas() {
        return listaReviews.size();
    }


}
