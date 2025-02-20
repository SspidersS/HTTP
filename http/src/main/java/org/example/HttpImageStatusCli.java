package org.example;

import java.util.Scanner;

public class HttpImageStatusCli {
    public void askStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter HTTP status code: ");

        if (scanner.hasNextInt()) {
            int code = scanner.nextInt();
            HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

            try {
                downloader.downloadStatusImage(code);
            } catch (Exception e) {
                System.out.println("There is not image for HTTP status " + code);
            }
        } else {
            System.out.println("Please enter valid number");
        }
    }
}
