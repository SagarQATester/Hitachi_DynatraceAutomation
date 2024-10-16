package expotel;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ExotelSingleCall {

    public static void main(String[] args) {
        try {
            // Define your API credentials
            String sid = "believeit1";  // Your Exotel SID
            String authToken = "NjE2NzlmMzFmNDI0YzJhOWIxZTMwOTc0NTMxNWQ5YzljOGZkY2U2YjI5NGEyYWRkOjRkOTkzNTY4OWNkMzE4ZTVlNzM3OGJmN2NmZjUzYmE5ZTI2NGVlNjhkZDk3YTUxMg==";  // Base64 encoded SID:Token
            String callerId = "02048556372";   // The Exotel virtual phone number (the caller)

            // Define recipient phone number and dynamic text message
            String toPhone = "09011734501";   // The number receiving the call
            String message = "Hello! This is a dynamic text message for you.";  // Dynamic message

            // Encode the dynamic text message to be URL-safe
            String encodedMessage = URLEncoder.encode(message, "UTF-8");

            // Create the URL for Exotel API
            String urlString = "https://api.exotel.com/v1/Accounts/" + sid + "/Calls/connect.json";
            URL url = new URL(urlString);

            // Open the connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Set the headers
            conn.setRequestProperty("Authorization", "Basic " + authToken);
            conn.setRequestProperty("accept", "application/json");

            // POST data: From (Exotel virtual number), To (recipient), CallerId (Exotel number), and the URL with dynamic text message (as TTS)
            String postData = "From=" + toPhone +  // Corrected: Caller ID should be the Exotel virtual number
                              "&To=" + toPhone +    // Recipient's phone number
                              "&CallerId=" + callerId +
                              "&Url=http://my.exotel.in/exoml/start_voice_tts/1?message=" + encodedMessage;

            // Write the data to the request
            OutputStream os = conn.getOutputStream();
            os.write(postData.getBytes());
            os.flush();

            // Get the response code
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Call successfully initiated for " + toPhone);
            } else {
                System.out.println("Failed to initiate the call for " + toPhone + ". Response code: " + responseCode);
            }

            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
