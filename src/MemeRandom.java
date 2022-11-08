/*
 @author saltswater
 */
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;   

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;



public class MemeRandom implements ActionListener{
    static UrlContent urlContent;
    static String API_KEY;
    static URL  url;
    static Icon icon;
    static JPanel panelB,panelE;
    static JButton nextBtn,changeTagBtn,previousBtn,urlBtn;
    static JLabel label;
    static JFrame frame;
    static DoubleLinkedList list = new DoubleLinkedList();
    static Desktop desk=Desktop.getDesktop();
    static int click=0;
    public static void main(String[] args) throws Exception, MalformedURLException{
        try{
         API_KEY = "GYeHAfTxIoSwKJYD2tsY6XVfT1Q0yZfM";
        
        //Image zone
        urlContent = new UrlContent(API_KEY);
        url = new URL(urlContent.requestImage());
        list.insert(urlContent.getLink());
        icon = new ImageIcon(url);
        label = new JLabel(icon);
        frame = new JFrame("Meme random");
        // frame.getContentPane().add(label);

        //button zone
        panelB = new JPanel();
        panelE = new JPanel();

        previousBtn = new JButton("Previous");
        previousBtn.setFocusable(false);
        previousBtn.setBackground ( Color.BLACK );
        previousBtn.setForeground ( Color.WHITE );
        previousBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                previous();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
             }
        });
        changeTagBtn = new JButton("Change tag");
        changeTagBtn.setFocusable(false);
        changeTagBtn.setBackground (new Color(0,0,0));
        changeTagBtn.setForeground (new Color(255,255,255));
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
        nextBtn = new JButton("Next");
        nextBtn.setFocusable(false);
        nextBtn.setBackground (new Color(0,0,0));
        nextBtn.setForeground (new Color(255,255,255));
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                next();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
             }
        });

        urlBtn = new JButton();
        ImageIcon icon = new ImageIcon("res/share.png");
        urlBtn.setFocusable(false);
        urlBtn.setIcon(icon);
        urlBtn.setBackground(new Color(255,255,255));
        urlBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                    // desk.browse(new URI(urlContent.getLink()));
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
		            Clipboard clipboard = toolkit.getSystemClipboard();
		            StringSelection strSel = new StringSelection(urlContent.getLink());
		            clipboard.setContents(strSel, null);
                    JOptionPane.showMessageDialog(null,"Link copied to clipboard");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
             }
        });
        panelB.add(previousBtn);
        panelB.add(changeTagBtn);
        panelB.add(nextBtn);
        panelE.add(urlBtn);
        checkClick();

        //Adding to Frame
        frame.getContentPane().add( BorderLayout.EAST, panelE);
        frame.getContentPane().add(BorderLayout.SOUTH, panelB);
        frame.getContentPane().add(BorderLayout.CENTER, label);
        randomColor();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setVisible(true);
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, e.getMessage());
          System.exit(0);
      }
   }
     
     public static void update() throws MalformedURLException, Exception{
      url = new URL(urlContent.requestImage());
      list.insert(urlContent.getLink());
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.getContentPane().add(label);
      frame.setVisible(true);
   }
   public static void previous() throws MalformedURLException, Exception{
      click--;
      checkClick();
      randomColor();
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
      checkClick();
      randomColor();
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
   
   public static void checkClick(){
    if(click==0){
        previousBtn.setVisible(false);
        previousBtn.setEnabled(false);
    }else{
        previousBtn.setVisible(true);
        previousBtn.setEnabled(true);
    }
   }

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    
}

private static void randomColor(){
    Random rand = new Random();
    int r = rand.nextInt(255);
    int g = rand.nextInt(255);
    int b = rand.nextInt(255);
    Color randomColor = new Color(r,g,b);
    panelB.setBackground(randomColor);
    panelE.setBackground(randomColor);
    frame.getContentPane().setBackground(randomColor);
}

}



