package expotel;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExotelVoiceCall {

    public static void main(String[] args) {
        try {
            // Define your API credentials
            String sid = "believeit1";  // Your Exotel SID
            String authToken = "NjE2NzlmMzFmNDI0YzJhOWIxZTMwOTc0NTMxNWQ5YzljOGZkY2U2YjI5NGEyYWRkOjRkOTkzNTY4OWNkMzE4ZTVlNzM3OGJmN2NmZjUzYmE5ZTI2NGVlNjhkZDk3YTUxMg==";  // Base64 encoded SID:Token

            // Define phone numbers and CallerId
            String fromPhone = "09011734501";  // The number making the call
            String toPhone = "09011734501";    // The number receiving the call
            String callerId = "02048556372";   // The Exotel virtual phone number

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

            // Prepare the POST data
            String postData = "From=" + fromPhone +
                              "&To=" + toPhone +
                              "&CallerId=" + callerId;

            // Write the data to the request
            OutputStream os = conn.getOutputStream();
            os.write(postData.getBytes());
            os.flush();

            // Get the response code
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Call successfully initiated!");
            } else {
                System.out.println("Failed to initiate the call. Response code: " + responseCode);
            }

            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
