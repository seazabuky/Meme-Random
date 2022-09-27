import javax.swing.*;
import java.net.*;



public class TestAPI {
    public static void main(String[] args) throws Exception, MalformedURLException {
        try{
        String API_KEY = "GYeHAfTxIoSwKJYD2tsY6XVfT1Q0yZfM";
        UrlContent urlContent = new UrlContent(API_KEY);
        URL  url = new URL(urlContent.getURL());
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        JFrame frame = new JFrame("Meme random");
        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
     }catch(Exception e){
         System.out.println(e);
     }

}
}