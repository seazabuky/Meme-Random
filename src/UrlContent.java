import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import org.json.*;
public class UrlContent{
    URL url;
    String api_key,tags,link;
    int status=0;
        public UrlContent(String apikey, String tag) throws MalformedURLException,Exception{
            this.api_key = apikey;
            this.tags = tag;
            //this.url = new URL("https://api.giphy.com/v1/gifs/translate?api_key="+apikey+"&s="+this.tags);
            this.url = new URL("https://api.giphy.com/v1/gifs/random?api_key="+this.api_key+"&tag="+this.tags+"&rating=r");
            if(!netIsAvailable())
                throw new Exception("Error: Please check your internet connection");
        }
        public UrlContent(String apikey) throws Exception{
            this(apikey,"meme");
        }
        public String requestImage()throws Exception{
            try{
                netIsAvailable();
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine,apiContent="";
                while ((inputLine = in.readLine()) != null)
                    apiContent = apiContent + inputLine;
                in.close();
                JSONObject obj = new JSONObject(apiContent);
                link = obj.getJSONObject("data").getJSONObject("images").getJSONObject("original").getString("url");
                link=link.substring(link.indexOf("media/"));
                link="https://i.giphy.com/"+link;
                status = obj.getJSONObject("meta").getInt("status");
                
                if (checkStatus()){
                    return this.link;
                }else
                    throw new Exception("Status: "+status);
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.exit(0);
                return null;
            }
        }
        public String getLink(){
            return this.link;
        }
        private boolean checkStatus(){
            switch(status){
                case 200:
                    System.out.println("OK");
                    return true;
                case 400:
                    System.out.println("Bad Request");
                    return false;
                case 401:
                    System.out.println("Unauthorized");
                    return false;
                case 403:
                    System.out.println("Forbidden");
                    return false;
                case 404:
                    System.out.println("Not Found");
                    return false;
                case 414:
                    System.out.println("URI Too Long");
                    return false;
                case 429:
                    System.out.println("Too Many Requests");
                    return false;
                case 503:
                    System.out.println("Service Unavailable");
                    return false;
                case 504:
                    System.out.println("Gateway Timeout");
                    return false;
                default:
                    System.out.println("Unknown Error");
                    return false;
            }
        }
        public void setNewTags(String tag) throws MalformedURLException{
            this.tags = tag;
            this.url = new URL("https://api.giphy.com/v1/gifs/random?api_key="+this.api_key+"&tag="+this.tags+"&rating=r");
            System.out.println("New tags: "+this.tags);
        }
        public String getTags(){
            return this.tags;
        }
        public void setNewapikey(String apikey){
            this.api_key = apikey;
        }
        public String getapikey(){
            return this.api_key;
        }
        public boolean netIsAvailable() throws Exception {
            try {
                URL urlTest = new URL("http://www.google.com");
                final URLConnection conn =urlTest.openConnection();
                conn.connect();
                conn.getInputStream().close();
                return true;
            } catch (MalformedURLException e) {
                throw new Exception("Error: Please check your internet connection");
            } catch (IOException e) {
                throw new Exception("Error: Please check your internet connection");
            }
        }

            
}
