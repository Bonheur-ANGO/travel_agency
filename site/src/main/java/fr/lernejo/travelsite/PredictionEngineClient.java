package fr.lernejo.travelsite;

import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface PredictionEngineClient {
    @GET("/api/temperature")
    Call<String> getTemperature(@Query("country") String country);
}
