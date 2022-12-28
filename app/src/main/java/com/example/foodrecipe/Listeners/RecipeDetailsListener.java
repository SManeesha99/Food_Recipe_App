package com.example.foodrecipe.Listeners;

import com.example.foodrecipe.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
