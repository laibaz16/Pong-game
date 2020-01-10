import java.applet.*; 
import java.awt.*; 
import java.awt.event.*;
import java.net.*;
//import java.net.URL;

public class ponggame extends Applet implements Runnable, KeyListener
{ 
  int x=200, y=217, radius=12, xsp=0, ysp=0, sizex=400, sizey=300, score=100;
  int recx=165,recy=230,recx_size=70, recy_size=7, rec_xsp=1, rec_ysp=1;
  String string="Click on the screen and press spacebar to start.";
  Image image; 
  private Image dbImage; 
  private Graphics dbg; 
  
  public void init()
  {
    image = getImage (getCodeBase (), "ball.jpg"); 
  }
  
  public void start(){     
    addKeyListener(this);
    Thread th=new Thread (this); 
    th.start (); 
  }
  
  public void stop() {}  
  
  public void destroy() {} 
  
  public void keyReleased (KeyEvent e){}   
  public void keyTyped(KeyEvent e){}  
  
  public void keyPressed (KeyEvent e)
  {
    int code =e.getKeyCode(); 
    
//    System.out.print(code); 
    
    if (code == KeyEvent.VK_LEFT) 
    { 
      recx=+(recx-3);
      recx-=rec_xsp;
      if( recx<0)
      {
        recx=0;
      }
    } 
    
    else if (code == KeyEvent.VK_RIGHT)   
    {        
      recx=+(recx+3);
      recx+=rec_xsp;
      if( recx>sizex-recx_size)
      {
        recx=330;
      }  
    }
    else if (code == KeyEvent.VK_SPACE)
    {
      xsp = -3;
      ysp = -2;
    string="";
   
    }
    else if (code == KeyEvent. VK_ENTER)
    {
      score=100;
      string="Press spacebar to start";
    }
  }
  
  public void run () { 
    Thread.currentThread().setPriority(Thread.MIN_PRIORITY); 
    while (true) 
    {
      if (x>sizex - radius) 
      {  
        xsp = -3;         
      } 
      else if (x < radius) 
      { 
        xsp = +3;
      } 
      x += xsp;
      if (y>sizey - radius) 
      {  
        ysp = -3;
      } 
      else if (y < radius) 
      { 
        ysp = +3; 
      } 
      y += ysp;
      
      
      if(y+radius>=recy && x+radius>=recx && x+radius<=+(recx_size+recx+20) && y+radius<=recy+recy_size)
      {
        System.out.print(y+radius);
        xsp = -3;
        ysp = -2;
        score=+(score+10);
        if (score>=200)
        {
          xsp = -5;
          ysp = -2;
        }
      }
      if(y+radius>=300)
      {
        x=200;
        y=217;
        xsp=0;
        ysp=0;
        recx=160;
        score=+(score-30);
        if(score<=0)
        {          
          string="Try Again. Press enter to restart";
          score=0;
        }
      }
      
      repaint();     
      try 
      { 
        Thread.sleep (20); 
      } 
      catch (InterruptedException ex) 
      {
      } 
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY); 
    }
  } 
  
  public void paint (Graphics g) {
    g.drawImage (image, 0, 0,400,300,this);  
    g.setColor(Color.BLACK);
    g.fillOval (x - radius, y - radius, 2 * radius, 2 * radius);
    g.setColor(Color.BLUE);
    g.fillRect(recx,recy,recx_size,recy_size);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
    g.drawString(""+string,30,170);
    g.setColor(Color.RED);
    g.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
    g.drawString("Score"+score, 0, 290);
    
  } 
  
  public void update (Graphics g) 
  { 
    if (dbImage == null) 
    { dbImage = createImage (this.getSize().width, this.getSize().height); 
      dbg = dbImage.getGraphics (); 
    } 
    
    dbg.setColor (getBackground ()); 
    dbg.fillRect (0, 0, this.getSize().width, this.getSize().height); 
    
    dbg.setColor (getForeground()); 
    paint (dbg); 
    
    g.drawImage (dbImage, 0, 0, this); 
  } 
} 
