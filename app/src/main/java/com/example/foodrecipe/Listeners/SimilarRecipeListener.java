package com.example.foodrecipe.Listeners;

import com.example.foodrecipe.Models.SimilarRecipeRespose;

import java.util.List;

public interface SimilarRecipeListener {
    void didFetch(List<SimilarRecipeRespose> resposes, String message);
    void didError(String message);
}
