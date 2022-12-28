package com.example.foodrecipe;

import android.content.Context;

import com.example.foodrecipe.Listeners.IstructionListener;
import com.example.foodrecipe.Listeners.RandomRecipeResponseListener;
import com.example.foodrecipe.Listeners.RecipeDetailsListener;
import com.example.foodrecipe.Listeners.SimilarRecipeListener;
import com.example.foodrecipe.Models.InstructionResponse;
import com.example.foodrecipe.Models.RandomRecipeApiResponse;
import com.example.foodrecipe.Models.RecipeDetailsResponse;
import com.example.foodrecipe.Models.SimilarRecipeRespose;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

//    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "10", tags);
//        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "10", tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipe(SimilarRecipeListener listener, int id){
        CallSimilarrecipes callSimilarrecipes = retrofit.create(CallSimilarrecipes.class);
        Call<List<SimilarRecipeRespose>> call = callSimilarrecipes.callSimilarRecipe(id, "4", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeRespose>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeRespose>> call, Response<List<SimilarRecipeRespose>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeRespose>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getInstruction(IstructionListener listner, int id){
        CallInstructor callInstructor = retrofit.create(CallInstructor.class);
        Call<List<InstructionResponse>> call = callInstructor.calliInstruction(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionResponse>> call, Response<List<InstructionResponse>> response) {
                if(!response.isSuccessful()){
                    listner.didError(response.message());
                    return;
                }
                listner.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionResponse>> call, Throwable t) {
                listner.didError(t.getMessage());
            }
        });

    }



    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(

                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")List<String> tags

//                @Query("tags") List<String> tags
                );
    }

    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallSimilarrecipes{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeRespose>> callSimilarRecipe(
          @Path("id") int id,
          @Query("number") String number,
          @Query("apiKey") String apiKey
        );
    }

    private interface CallInstructor{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionResponse>> calliInstruction(
          @Path("id") int id,
          @Query("apiKey") String apiKey
        );
    }


}
