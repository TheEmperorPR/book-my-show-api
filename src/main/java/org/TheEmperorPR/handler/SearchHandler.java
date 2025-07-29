package org.TheEmperorPR.handler;

import jakarta.json.Json;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.TheEmperorPR.dto.ShowsSearchResponse;
import org.TheEmperorPR.service.ShowsService;

import java.io.IOException;

@Path("/")
public class SearchHandler {

    private final ShowsService showsService = new ShowsService();

    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public ShowsSearchResponse searchShows(
            @QueryParam("city") String city,
            @QueryParam("lt") String latitude,
            @QueryParam("lg") String longitude,
            @QueryParam("showName") String showName,
            @QueryParam("category") String category
    ) throws IOException {

        return showsService.fetchShows(city,showName,category,latitude,longitude);

    }


    @GET
    @Path("/getShowDetails")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getShowDetails() throws IOException {
        return showsService.getShowDetails();
    }


}
