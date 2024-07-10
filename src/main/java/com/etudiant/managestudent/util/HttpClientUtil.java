package com.etudiant.managestudent.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class HttpClientUtil {
    private static final String BOUNDARY = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

    public String sendPostRequestWithFile(String urlString, File file, String nom, String postName, String preName, String email) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            String requestBody = "--" + BOUNDARY + "\r\n" +
                    "Content-Disposition: form-data; name=\"nom\"\r\n\r\n" + nom + "\r\n" +
                    "--" + BOUNDARY + "\r\n" +
                    "Content-Disposition: form-data; name=\"post-nom\"\r\n\r\n" + postName + "\r\n" +
                    "--" + BOUNDARY + "\r\n" +
                    "Content-Disposition: form-data; name=\"pre-nom\"\r\n\r\n" + preName + "\r\n" +
                    "--" + BOUNDARY + "\r\n" +
                    "Content-Disposition: form-data; name=\"email\"\r\n\r\n" + email + "\r\n" +
                    "--" + BOUNDARY + "\r\n" +
                    "Content-Disposition: form-data; name=\"photo\"; filename=\"" + file.getName() + "\"\r\n" +
                    "Content-Type: " + Files.probeContentType(file.toPath()) + "\r\n\r\n";

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());

            FileInputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();

            outputStream.write(("\r\n--" + BOUNDARY + "--\r\n").getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == 201) {
                return "Success";
            } else {
                return "Failed: HTTP error code " + responseCode;
            }

        } catch (Exception e) {
            return "Error";
        }
    }


}
