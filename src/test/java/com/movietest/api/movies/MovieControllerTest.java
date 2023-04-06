package com.movietest.api.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String getMoviesURL = "/moviestore/v1/movie";
    private final String getMoviesByIdURL = "/moviestore/v1/movie/id";
    private final String getQuotesURL = "/moviestore/v1/movie/id/quote";

    @Test
    public void testGetMovieSuccess() throws Exception {
        mvc.perform(get(getMoviesURL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    public void testGetMovieByIdSuccess() throws Exception {
        mvc.perform(get(getMoviesByIdURL.replaceAll("id","993504c50923199f0a069c75c9ffe048"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$['title']").value("The Battle of the Five Armies"));
    }

    @Test
    public void testGetMovieByIdFailure() throws Exception {
        mvc.perform(get(getMoviesByIdURL.replaceAll("id","wrongmovieid"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetQuoteByIdSuccess() throws Exception {
        mvc.perform(get(getQuotesURL.replaceAll("id","993504c50923199f0a069c75c9ffe048"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void testGetQuoteByIdFailure() throws Exception {
        mvc.perform(get(getQuotesURL.replaceAll("id","wrongmovieid"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
