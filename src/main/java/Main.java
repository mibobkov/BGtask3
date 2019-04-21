import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;


public class Main {
    public static void main(String[] args) {
        URL url;
        String API_KEY = "trnsl.1.1.20190411T082708Z.d3931d750effc106.e0e78c425ffafa30b5d25ccb5bfc3e329c5fd778";
        String text = null;
        try {
            text = URLEncoder.encode( "Hello, my name is Misha", StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            url = new URL(String.format("https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s&text=%s&lang=ru", API_KEY, text));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            con.setDoOutput(true);

            // Send the request
            DataOutputStream wr = new DataOutputStream(
                    con.getOutputStream());
            wr.close();

            // Get the response
            InputStream is = con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject obj = jsonReader.readObject();
            jsonReader.close();
            System.out.println(new String(obj.getJsonArray("text").getString(0).getBytes(), "UTF-8")
                    + "\n\nPowered by Yandex.Translate\n"
                    + "http://translate.yandex.com/\n");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
    }
}