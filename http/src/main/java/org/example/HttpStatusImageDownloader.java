package org.example;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusImageDownloader {
        public void downloadStatusImage(int code) throws Exception {
            HttpStatusChecker checker = new HttpStatusChecker();
            String imageUrl = checker.getStatusImage(code);

            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream(code + ".jpg")) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            connection.disconnect();
            System.out.println("Image downloaded successfully: " + code + ".jpg");
        }
}
