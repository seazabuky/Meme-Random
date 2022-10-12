import javax.swing.*;
import java.net.*;
import java.awt.Desktop;



public class TestAPI {
   static UrlContent urlContent;
   static String API_KEY;
   static URL  url;
   static Icon icon;
   static JLabel label;
   static JFrame frame;
   static DoubleLinkedList list = new DoubleLinkedList();
   static Desktop desk=Desktop.getDesktop();
    public static void main(String[] args) throws Exception, MalformedURLException {
        try{
         String input = "";
         API_KEY = "GYeHAfTxIoSwKJYD2tsY6XVfT1Q0yZfM"; 
        urlContent = new UrlContent(API_KEY);
        url = new URL(urlContent.getURL());
        list.insert(urlContent.getLink());
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
      if(input.equals("exit")){
               System.exit(0);
               break;
        }else if(input.equals("n")){
            next();
        }
        else if(input.equals("p")){
            previous();
        }
        else if(input.equals("t")){
               String tag = JOptionPane.showInputDialog("Input Tags");
               tag=tag.toLowerCase().replace(" ","-");
               urlContent = new UrlContent(API_KEY,tag);
               list.findLast();
               update();
        }else if(input.equals("url")){
            desk.browse(new URI((String)list.retrieve()));
  }
      }
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, e.getMessage());
          System.exit(0);
      }
   }
     
     public static void update() throws MalformedURLException, Exception{
      url = new URL(urlContent.getURL());
      list.insert(urlContent.getLink());
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.pack();
   }
   public static void previous() throws MalformedURLException, Exception{
      list.findPrevious();
      String temp = (String)list.retrieve();
      url = new URL(temp);
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.pack();
   }
   public static void next() throws MalformedURLException, Exception{
      if(list.getNext() != null){
      list.findNext();
      String temp = (String)list.retrieve();
      url = new URL(temp);
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.pack();
      }else
         update();
      
   }

}



