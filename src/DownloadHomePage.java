import java.io.*;
import java.net.*;

public class DownloadHomePage {
    public static void main(String[] args) {
        String websiteURL = "https://www.google.com";
        String outputHomePage = "homepage.html";

        try {
            // Create a URL object
            URL url = new URL(websiteURL);

            // Open a connection to URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Check if the response code is 200
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                // Read data from the input stream
                BufferedReader reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputHomePage));

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }

                reader.close();
                writer.close();

                System.out.println(("Homepage downloaded successfully to:" + outputHomePage));
            } else {
                System.out.println("Failed to connect. HTTP response code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}