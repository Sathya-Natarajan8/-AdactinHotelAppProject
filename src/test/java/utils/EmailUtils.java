package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.*;

public class EmailUtils {
    private static final String MAILINATOR_DOMAIN = "mailinator.com";
    private static final String MAILINATOR_API_KEY = "YOUR_MAILINATOR_API_KEY";  // Optional if using private inbox

    // Fetch the verification link from Mailinator
    public static String getMailinatorVerificationLink(String inbox) {
        try {
            // Fetch messages
            Response response = RestAssured.given()
                    .header("Authorization", "Bearer " + MAILINATOR_API_KEY)  // Optional if private inbox
                    .when()
                    .get("https://mailinator.com/api/v2/domains/public/inboxes/" + inbox + "/messages");

            if (response.getStatusCode() != 200) {
                System.out.println("Failed to fetch emails from Mailinator. Status Code: " + response.getStatusCode());
                return null;
            }

            JSONObject jsonResponse = new JSONObject(response.getBody().asString());
            JSONArray messages = jsonResponse.getJSONArray("messages");

            if (messages.length() == 0) {
                System.out.println("No emails found in Mailinator inbox: " + inbox);
                return null;
            }

            // Get latest email ID
            String emailId = messages.getJSONObject(0).getString("id");

            // Fetch email content
            Response emailResponse = RestAssured.given()
                    .header("Authorization", "Bearer " + MAILINATOR_API_KEY)
                    .when()
                    .get("https://mailinator.com/api/v2/domains/public/messages/" + emailId);

            if (emailResponse.getStatusCode() != 200) {
                System.out.println("Failed to fetch email content. Status Code: " + emailResponse.getStatusCode());
                return null;
            }

            JSONObject emailJson = new JSONObject(emailResponse.getBody().asString());
            JSONArray emailParts = emailJson.getJSONArray("parts");

            // Extract email body
            String emailBody = "";
            for (int i = 0; i < emailParts.length(); i++) {
                if (emailParts.getJSONObject(i).has("body")) {
                    emailBody = emailParts.getJSONObject(i).getString("body");
                    break;
                }
            }

            if (emailBody.isEmpty()) {
                System.out.println("Email body is empty!");
                return null;
            }

            // Extract verification link using regex
            String linkRegex = "https?://[^\"]*Verify\\.php\\?vcode=[^\"]*";
            Pattern pattern = Pattern.compile(linkRegex);
            Matcher matcher = pattern.matcher(emailBody);

            return matcher.find() ? matcher.group() : null;
        } catch (JSONException e) {
            System.out.println("JSON Parsing error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
