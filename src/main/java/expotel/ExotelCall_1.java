package expotel;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.util.Base64;

public class ExotelCall_1 {
    public static void main(String[] args) {
        try {
            String sid = "believeit1"; // Exotel SID
            String apiKey="61679f31f424c2a9b1e309745315d9c9c8fdce6b294a2add";
            String token = "4d9935689cd318e5e7378bf7cff53ba9e264ee68dd97a512"; // Exotel Token
            String subdomain="api.exotel.com";
            String exoPhone = "02048556372"; // Your Exotel virtual number
            String toPhoneNumber = "09011734501"; // Number to call
            String dynamicText = "Hello, this is a reminder call. Your appointment is scheduled for tomorrow."; // Dynamic text

            // Prepare the URL for the Exotel API
            String urlString="https://"+apiKey+":"+token+subdomain+"/v1/Accounts/"+sid+"/Calls/connect";
         //   String urlString = "https://api.exotel.com/v1/Accounts/" + sid + "/Calls/connect";
            URL url = new URL(urlString);

            // Create the HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Basic " +
                    Base64.getEncoder().encodeToString((sid + ":" + token).getBytes()));

            // Prepare the request body
            String postData = "From=" + exoPhone +
                    "&To=" + toPhoneNumber +
                    "&CallerId=" + exoPhone +
                    "&Url=http://my.exotel.in/exoml/start_voice_tts/1?message=" + dynamicText.replace(" ", "%20") +
                    "&CallType=trans";

            // Send the request
            OutputStream os = conn.getOutputStream();
            os.write(postData.getBytes());
            os.flush();

            // Check the response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Call successfully initiated.");
            } else {
                System.out.println("Failed to initiate the call, response code: " + responseCode);
            }

            // Close the connection
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

