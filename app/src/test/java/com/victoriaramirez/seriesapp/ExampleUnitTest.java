package com.victoriaramirez.seriesapp;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.victoriaramirez.seriesapp.models.Image;
import com.victoriaramirez.seriesapp.models.Serie;
import com.victoriaramirez.seriesapp.restApi.model.SerieResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_parse_request_json()
    {

        String data = "{medium:\"https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg\",original:\"https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg\"}";
        Gson gson = new Gson();
        Image response =  gson.fromJson(data, Image.class);

        //assertTrue(response.size()>0);
        assertNotNull(response.getMedium());
        assertNotNull(response.getOriginal());
    }
}