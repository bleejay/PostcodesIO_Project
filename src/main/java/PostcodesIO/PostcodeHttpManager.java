package PostcodesIO;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class PostcodeHttpManager {

    private String baseURL;
    private String postcode;
    private String postcodeInfo;


    public PostcodeHttpManager(String postcode) {
        baseURL = "https://api.postcodes.io/postcodes/";
        this.postcode = postcode;
    }

    public void createPostcodeAsString(){
        try {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getPostcodeDetails = new HttpGet(baseURL + postcode);
        CloseableHttpResponse response = httpClient.execute(getPostcodeDetails);
        postcodeInfo = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPostcodeInfo() {
        return postcodeInfo;
    }
}
