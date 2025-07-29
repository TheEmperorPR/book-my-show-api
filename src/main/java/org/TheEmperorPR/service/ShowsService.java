package org.TheEmperorPR.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.TheEmperorPR.dto.ShowsSearchResponse;
import java.io.IOException;
import java.util.List;
import org.TheEmperorPR.dto.ShowsSearchResponse.Hit;

@ApplicationScoped
public class ShowsService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public ShowsSearchResponse fetchShows(
            String city, String showName, String category
    , String latitude, String longitude) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("in.bookmyshow.com")
                .addPathSegment("quickbook-search.bms")
                .addQueryParameter("r", city)
                .addQueryParameter("lt",latitude)
                .addQueryParameter("lg",longitude)
                .addQueryParameter("q",showName)
                .addQueryParameter("cat",category)
                .addQueryParameter("f", "json")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new RuntimeException("Unexpected response: " + response);

            String json = response.body().string();
            JsonNode root = mapper.readTree(json);
            JsonNode hitsNode = root.get("hits");

            List<Hit> hits = mapper.convertValue(
                    hitsNode,
                    new TypeReference<List<Hit>>() {}
            );

            return new ShowsSearchResponse(hits); // Kotlin constructor!
        }
    }


    public String getShowDetails() throws IOException {
        Request request = new Request.Builder()
                .url("\n" +
                        "https://in.bookmyshow.com/api/movies/v1/synopsis/init/static?eventcode=ET00403839&channel=web&isRnROnly=false&isdesktop=true&lat=26.4634&lon=80.3229")
//                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36")
                .addHeader("X-App-Code", "WEB")
                .addHeader("X-Mobile", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36")
                .addHeader("X-Region-Code", "KANP") // imp
                .addHeader("X-Region-Slug", "kanpur")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected response: " + response);

            return response.body().string();
        }
    }
}
