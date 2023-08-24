package br.com.melo.api.controller;


import br.com.melo.api.model.Review;
import br.com.melo.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/")
    public void adicionarReview(@RequestBody Review review) {
        reviewService.salvarReview(review);
    }

    @DeleteMapping("/{id}")
    public void removerProduct(@PathVariable Long id) {
        reviewService.removeReview(id);
    }

}
