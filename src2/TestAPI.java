import javax.swing.*;
import java.net.*;



public class TestAPI {
   static UrlContent urlContent;
   static String API_KEY;
   static URL  url;
   static Icon icon;
   static JLabel label;
   static JFrame frame;
    public static void main(String[] args) throws Exception, MalformedURLException {
        try{
         String input = "";
         API_KEY = "GYeHAfTxIoSwKJYD2tsY6XVfT1Q0yZfM"; 
        urlContent = new UrlContent(API_KEY);
        url = new URL(urlContent.getURL());
        icon = new ImageIcon(url);
        label = new JLabel(icon);
        frame = new JFrame("Meme random");
        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        while(true){
        input = JOptionPane.showInputDialog("Input");
        if(input==null){
            throw new Exception("exit");
        }
         if(input.equals("")){
               update();
            }else if(input.equals("exit")){
               System.exit(0);
               break;
        }else{
               input=input.toLowerCase().replace(" ","-");
               System.out.println(input);
               urlContent = new UrlContent(API_KEY,input);
               update();
        }
      }
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, e.getMessage());
          System.exit(0);
      }
   }
     
     public static void update() throws MalformedURLException, Exception{
      url = new URL(urlContent.getURL());
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.pack();
   }
}


