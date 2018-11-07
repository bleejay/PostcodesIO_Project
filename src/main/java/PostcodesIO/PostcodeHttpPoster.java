package PostcodesIO;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import java.io.IOException;

public class PostcodeHttpPoster {

    private String baseURL;
    private JSONObject postcodes = new JSONObject();
    private String postcodesInfo;

    public PostcodeHttpPoster(String[] postCodeList) {
        baseURL = "https://api.postcodes.io/postcodes/";
        createJSONPostcodes(postCodeList);
    }

    private void createJSONPostcodes(String[] postCodeList){
        postcodes.put("postcodes", postCodeList);
    }

    public void postPostcodes(){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost postPostcodes = new HttpPost(baseURL);
            postPostcodes.addHeader("Content-Type", "application/json");
            Gson gson = new Gson();
            postPostcodes.setEntity(new StringEntity(gson.toJson(postcodes)));
            CloseableHttpResponse response = httpClient.execute(postPostcodes);
            postcodesInfo = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getPostcodes() {
        return postcodes;
    }

    public String getPostcodesInfo() {
        return postcodesInfo;
    }
}
