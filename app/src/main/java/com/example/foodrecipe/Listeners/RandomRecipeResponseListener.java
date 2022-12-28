package com.example.foodrecipe.Listeners;

import com.example.foodrecipe.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);

}
