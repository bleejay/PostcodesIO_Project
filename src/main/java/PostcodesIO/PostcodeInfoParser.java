package PostcodesIO;

import org.json.simple.JSONObject;

public class PostcodeInfoParser {

    private JSONObject postcodeInfoFile;
    private JSONObject postcodeResult;

    public PostcodeInfoParser(String postcode) {
        PostcodeHttpManager postcodeHttpManager = new PostcodeHttpManager(postcode);
        JSONFactory jsonFactory = new JSONFactory();
        postcodeHttpManager.createPostcodeAsString();
        jsonFactory.setPostcodeInfoJSON(postcodeHttpManager.getPostcodeInfo());
        postcodeInfoFile = jsonFactory.getPostcodeInfoJSON();
        if (getStatus()){
            postcodeResult = getResult();
        }else{
            System.out.println(getError());}
    }

    private boolean getStatus(){
        if (postcodeInfoFile.get("status").toString().equalsIgnoreCase("200")) {
            return true;
        }
        return false;
    }

    public JSONObject getPostcodeInfoFile() {
        return postcodeInfoFile;
    }

    public JSONObject getPostcodeResult() {
        return postcodeResult;
    }

    public String getCountry(){return (String) postcodeResult.get("country");}

    public String getRegion(){return (String) postcodeResult.get("region");}

    public String getNuts(){return (String) postcodeResult.get("nuts");}

    private JSONObject getResult(){return (JSONObject) postcodeInfoFile.get("result");}

    private String getError(){return (String) postcodeInfoFile.get("error");}
}
