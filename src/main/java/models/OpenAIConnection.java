import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.classic.methods.HttpPost;

import org.apache.hc.core5.http.io.entity.StringEntity;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

public class OpenAIConnection {
    // PDF file path
    String filePath = ""

    // Extract the resume text from a pdf
    public static String ExtractPDFText(String filePath) throws IOException {
        PDDocument document = PDDocument.load(new File(filepath));
        PDFTextStripper stripper = new PDFTextStripper();
        String text = pdfStripper.getText(document)

        document.close()

        return text
    }

    // !! DANGER !!
    //  MAKE SURE TO NOT PUSH YOUR ACTUAL API KEY INTO A PUBLIC REPO
    // THERE ARE GITHUB BOTS THAT STEAL API KEYS EVEN IF THEY ARE ONLY POSTED FOR A SECOND

    // Replace with your own OpenAI key
    String OPENAI_API_KEY = "https://api.openai.com/v1/completions";
    
    String file = "";
    String fileText = "";

    // Send the resume text to OpenAI along with a prompt for information we want
    public String static SendToOpenAI(string text) throws Exception {
        String APIUrl = "https://api.openai.com/v1/completions"

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(APIUrl);

            // Set the prompt
            String prompt = "Give me only the following information in a comma serperated string from the following resume: Name, Phone Number, Email Address, Years of Experience, Highest Completed Edcuation, Major, Skills.\n\nResume Text:\n" + fileText;
            
            // Set the POST Payload for the API Call
            String jsonPayload = "{"
                + "\"model\": \"gpt-4\","
                + "\"prompt\": \"" + prompt.replace("\n", "\\n") + "\","
                + "\"max_tokens\": 500,"
                + "\"temperature\": 0.5"
                + "}";
            post.setHeader("Authorization", "Bearer " + OPENAI_API_KEY);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(jsonPayload, StandardCharsets.UTF_8));

            // Send the POST request
            CloseableHttpResponse response = httpClient.execute(post);
            
            // Get the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseText.append(line);
            }

            // Parse the reponse
            if (response.getCode() == 200) {
                JsonObject jsonResponse = JsonParser.parseString(responseText.toString()).getAsJsonObject();
                return jsonResponse.getAsJsonArray("choices").get(0).getAsJsonObject().get("text").getAsString().trim();
            }

            // From here you can use the information however you wish
            // Feel free to edit the code to better fit the program
        }
    }
}