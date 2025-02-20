package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusChecker {
    private static final String BASE_URL = "https://http.cat/";

    public String getStatusImage(int code) throws Exception {
        String imageUrl = BASE_URL + code + ".jpg";

        if (isValidImage(imageUrl)) {
            return imageUrl;
        } else {
            throw new Exception("Image not found for status code: " + code);
        }
    }

    private boolean isValidImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();

        int responseCode = connection.getResponseCode();
        connection.disconnect();

        return responseCode == 200;
    }
}