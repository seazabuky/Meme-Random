import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlContent{
    URL url;
        public UrlContent(String API_KEY, String tag) throws MalformedURLException{
            url = new URL("https://api.giphy.com/v1/gifs/random?api_key="+API_KEY+"&tag="+tag+"&rating=g");
        }
        public UrlContent(String API_KEY) throws MalformedURLException{
            this(API_KEY,"meme");
        }
        public String getURL()throws Exception{
            try{
            BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));
    
            String inputLine,apiContent="";
            while ((inputLine = in.readLine()) != null)
                apiContent = apiContent + inputLine;
            in.close();
            
            //System.out.println(apiContent);
            apiContent = apiContent.substring(apiContent.indexOf("\"original\":{"),apiContent.indexOf("\"original_still\":{"));
            apiContent = apiContent.substring(apiContent.indexOf("\"url\":\"")+7,apiContent.indexOf("\",\"webp\":")).replace("\\","");
            apiContent = apiContent.substring(apiContent.indexOf("media/"));
            apiContent = "https://i.giphy.com/"+apiContent;
            return apiContent;
            }catch(Exception e){
                System.out.println(e);
                return null;
            }
        }

            
}