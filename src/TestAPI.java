/*
 @author saltswater
 */
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;



public class TestAPI implements ActionListener{
   static UrlContent urlContent;
   static String API_KEY;
   static URL  url;
   static Icon icon;
   static JLabel label;
   static JFrame frame;
   static DoubleLinkedList list = new DoubleLinkedList();
   static Desktop desk=Desktop.getDesktop();
   static int click=0;
    public static void main(String[] args) throws Exception, MalformedURLException {
        try{
        //  String input = "";
         API_KEY = "GYeHAfTxIoSwKJYD2tsY6XVfT1Q0yZfM";
        
        //Image zone
        urlContent = new UrlContent(API_KEY);
        url = new URL(urlContent.getURL());
        list.insert(urlContent.getLink());
        icon = new ImageIcon(url);
        label = new JLabel(icon);
        frame = new JFrame("Meme random");
        // frame.getContentPane().add(label);

        //button zone
        JPanel panel = new JPanel();
        JButton previousBtn = new JButton("Previous");

        JButton changeTagBtn = new JButton("Change tag");

        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){
                try{
                next();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
             }
        });
        previousBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){
                try{
                previous();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
             }
        });
        changeTagBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){
                try{
                    String tag = JOptionPane.showInputDialog("Input Tags");
                    tag=tag.toLowerCase().replace(" ","-");
                    urlContent = new UrlContent(API_KEY,tag);
                    list.findLast();
                    update();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
             }
        });
        panel.add(previousBtn);
        panel.add(changeTagBtn);
        panel.add(nextBtn);

        //Adding to Frame
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setVisible(true);
        

//         while(true){
//         input = JOptionPane.showInputDialog("Input");
//         if(input==null){
//             throw new Exception("exit");
//         }
//       if(input.equals("exit")){
//                System.exit(0);
//                break;
//         }else if(input.equals("n")){
//             next();
//         }
//         else if(input.equals("p")){
//             previous();
//         }
//         else if(input.equals("t")){
//                String tag = JOptionPane.showInputDialog("Input Tags");
//                tag=tag.toLowerCase().replace(" ","-");
//                urlContent = new UrlContent(API_KEY,tag);
//                list.findLast();
//                update();
//         }else if(input.equals("url")){
//             desk.browse(new URI((String)list.retrieve()));
//   }
//       }
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
      frame.getContentPane().add(label);
      frame.setVisible(true);
   }
   public static void previous() throws MalformedURLException, Exception{
      click--;
      list.findPrevious();
      String temp = (String)list.retrieve();
      url = new URL(temp);
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.getContentPane().add(label);
      frame.setVisible(true);
   }
   public static void next() throws MalformedURLException, Exception{
      click++;
      if(list.getNext() != null){
      list.findNext();
      String temp = (String)list.retrieve();
      url = new URL(temp);
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.getContentPane().add(label);
      frame.setVisible(true);
      }else
         update();
   }
   
//    public static void checkClick(){
//     if(click==0){
//         previousBtn.setVisible(false);
//         previousBtn.setEnabled(false);
//     }else{
//         previousBtn.setVisible(true);
//         previousBtn.setEnabled(true);
//     }
//    }

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    
}


}



