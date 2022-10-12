import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlContent{
    URL url;
    String API_KEY,tags,status = "0",link;
        public UrlContent(String APIKEY, String tag) throws MalformedURLException,Exception{
            this.API_KEY = APIKEY;
            this.tags = tag;
            url = new URL("https://api.giphy.com/v1/gifs/random?api_key="+APIKEY+"&tag="+tag+"&rating=r");
        }
        public UrlContent(String APIKEY) throws Exception{
            this(APIKEY,"meme");
        }
        public String getURL()throws Exception{
            try{
            BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));
    
            String inputLine,apiContent="";
            while ((inputLine = in.readLine()) != null)
                apiContent = apiContent + inputLine;
            in.close();
            
            status = apiContent.substring(apiContent.indexOf("\"meta\":{"));
            status = status.substring(status.indexOf("\"status\":")+9,status.indexOf(",\"response_id\":"));
            if (checkStatus()){
            apiContent = apiContent.substring(apiContent.indexOf("\"original\":{"),apiContent.indexOf("\"original_still\":{"));
            apiContent = apiContent.substring(apiContent.indexOf("\"url\":\""),apiContent.indexOf("\",\"webp\":")).replace("\\","");
            apiContent = apiContent.substring(apiContent.indexOf("media/"));
            apiContent = "https://i.giphy.com/"+apiContent;
            System.out.println(apiContent);
            this.link = apiContent;
            return apiContent;
        }else
            throw new Exception("Status: "+status);
            }catch(Exception e){
                System.out.println(e);
                return null;
            }
        }
        public String getLink(){
            return this.link;
        }
        private boolean checkStatus(){
            switch(status){
                case "200":
                    System.out.println("OK");
                    return true;
                case "400":
                    System.out.println("Bad Request");
                    return false;
                case "401":
                    System.out.println("Unauthorized");
                    return false;
                case "403":
                    System.out.println("Forbidden");
                    return false;
                case "404":
                    System.out.println("Not Found");
                    return false;
                case "414":
                    System.out.println("URI Too Long");
                    return false;
                case "429":
                    System.out.println("Too Many Requests");
                    return false;
                case "503":
                    System.out.println("Service Unavailable");
                    return false;
                case "504":
                    System.out.println("Gateway Timeout");
                    return false;
                default:
                    System.out.println("Unknown Error");
                    return false;
            }
        }
        public void setNewTags(String tag){
            this.tags = tag;
        }
        public String getTags(){
            return this.tags;
        }
        public void setNewAPIKEY(String APIKEY){
            this.API_KEY = APIKEY;
        }
        public String getAPIKEY(){
            return this.API_KEY;
        }

            
}