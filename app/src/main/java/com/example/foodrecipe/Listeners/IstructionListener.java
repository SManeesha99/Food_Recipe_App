package com.example.foodrecipe.Listeners;

import com.example.foodrecipe.Models.InstructionResponse;

import java.util.List;

public interface IstructionListener {

    void didFetch(List<InstructionResponse> response, String message);
    void didError(String message);

}

