package PostcodesIO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFactory {

    private JSONObject postcodeInfoJSON;

    public JSONObject getPostcodeInfoJSON() {return postcodeInfoJSON;}

    public void setPostcodeInfoJSON(String latestRatesJSONString) {
        try {
            JSONParser parser = new JSONParser();
            this.postcodeInfoJSON = (JSONObject) parser.parse(latestRatesJSONString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
