import javax.swing.*;
import java.net.*;



public class TestAPI {
   static UrlContent urlContent;
   static String API_KEY;
   static URL  url;
   static Icon icon;
   static JLabel label;
   static JFrame frame;
   static DoubleLinkedList list = new DoubleLinkedList();
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
               System.out.println(input);
               urlContent = new UrlContent(API_KEY,tag);
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
      list.insert(urlContent.getLink());
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.pack();
   }
   public static void previous() throws MalformedURLException, Exception{
      list.findPrevious();
      String temp = (String)list.retrive();
      url = new URL(temp);
      System.out.println(temp);
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.pack();
   }
   public static void next() throws MalformedURLException, Exception{
      if(list.getNext() != null){
      list.findNext();
      String temp = (String)list.retrive();
      url = new URL(temp);
      System.out.println(temp);
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.pack();
      }else{
         update();
      }
   }

}



