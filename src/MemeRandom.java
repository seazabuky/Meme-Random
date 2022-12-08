
import javax.swing.*;
import java.net.*;
import java.util.Random;   

//import awt
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class MemeRandom implements ActionListener{
    static UrlContent urlContent;
    static String api_key;
    static URL  url;
    static Icon icon;
    static JPanel panelB,panelE;
    static JButton nextBtn,changeTagBtn,previousBtn,urlBtn;
    static JLabel label;
    static JFrame frame;
    static DoubleLinkedList list = new DoubleLinkedList();
    public static void main(String[] args) throws Exception, MalformedURLException{
        try{
         api_key = "GYeHAfTxIoSwKJYD2tsY6XVfT1Q0yZfM";
        
        //Image zone
        urlContent = new UrlContent(api_key);
        url = new URL(urlContent.requestImage());
        list.insert(urlContent.getLink());
        icon = new ImageIcon(url);
        label = new JLabel(icon);
        frame = new JFrame("Meme random");


        //button zone
        panelB = new JPanel();
        panelE = new JPanel();

        previousBtn = new JButton("Previous");
        previousBtn.setFocusable(false);
        previousBtn.setBackground ( Color.BLACK );
        previousBtn.setForeground ( Color.WHITE );
        previousBtn.setOpaque(true);
        previousBtn.setBorderPainted(false);
        previousBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                previous();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.exit(0);
                }
             }
        });
        changeTagBtn = new JButton("Change tag");
        changeTagBtn.setFocusable(false);
        changeTagBtn.setBackground (new Color(0,0,0));
        changeTagBtn.setForeground (new Color(255,255,255));
        changeTagBtn.setOpaque(true);
        changeTagBtn.setBorderPainted(false);
        changeTagBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                    String tag = JOptionPane.showInputDialog("Input Tags");
                    tag=tag.toLowerCase().replace(" ","+");
                    urlContent.setNewTags(tag);
                    if(list.checkAtFirst()&&list.getNext()==null)
                        update();
                    else{
                        list.findLast();
                        update();
                    }
                    checkClick();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
             }
        });
        nextBtn = new JButton("Next");
        nextBtn.setFocusable(false);
        nextBtn.setBackground (new Color(0,0,0));
        nextBtn.setForeground (new Color(255,255,255));
        nextBtn.setOpaque(true);
        nextBtn.setBorderPainted(false);
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

        //Add Buttons to Frame
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
     //call this to show new image and store to list
     public static void update() throws MalformedURLException, Exception{
      randomColor();
      urlContent.netIsAvailable();
      url = new URL(urlContent.requestImage());
      list.insert(urlContent.getLink());
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.getContentPane().add(label);
      frame.setVisible(true);
   }
   //call this to show previous image
   public static void previous() throws MalformedURLException, Exception{
      randomColor();
      urlContent.netIsAvailable();
      list.findPrevious();
      String temp = (String)list.retrieve();
      urlContent.netIsAvailable();
      url = new URL(temp);
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.getContentPane().add(label);
      frame.setVisible(true);
      checkClick();
   }
   //call this to show next image if last image use update method to show new image
   public static void next() throws MalformedURLException, Exception{
      urlContent.netIsAvailable();
      if(list.getNext() != null){
      randomColor();
      list.findNext();
      String temp = (String)list.retrieve();
      url = new URL(temp);
      icon = new ImageIcon(url);
      label.setIcon(icon);
      frame.getContentPane().add(label);
      frame.setVisible(true);
      }else
        update();
      checkClick();
    
   }
   //check click for show previousBTN
   public static void checkClick(){
    if(list.checkAtFirst()){
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
//random colorBG
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



