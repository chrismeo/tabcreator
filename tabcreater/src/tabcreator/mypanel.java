package tabcreator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
              n18|b18 C6
B5 n17|b17|s17
              n16|b16|s16 A5
G5 n15|b15|s15  
              n14|b14|s14 F5
E5 n13|b13|s13
              n12|b12|s12 D5
C5 n11|b11|s11 
              n10|b10|s10 B4
A4 n9|b9|s9
              n8|b8|s8 G4
F4 n7|b7|s7   
              n6|b6|s6 E4
D4 n5|b5|s5
              n4|b4|s4 C4
B3 b3|s3|n3
              n2|s2|b2 A3
G3 s1|n1  

  TAB
  ----String1
  ----String2
  ----String3
  ----String4                               
                                                                                                   
*/


public class mypanel extends JPanel
{
     static boolean[] P = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
    		               false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
     static boolean[] N = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 
    		               false, false, false, false};
     static boolean[] S = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 
    		               false, false};
     static boolean[] B = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 
    		               false, false, false};
     static int[] tab = new int[]{-1,-1,-1,-1};
     static boolean showtab; 
     static boolean error = false;
     static boolean highG = false;
     static boolean lowG = true;
     
     static int tab_x = 530;
     static int tab_y1 = 195;
     static int tab_y2 = 225;
     static int tab_y3 = 255;
     static int tab_y4 = 285;
     
	 @Override
	 public void paintComponent(Graphics g)
	 {
	      super.paintComponent(g);
	      
	      try
	      {
	         final BufferedImage image = ImageIO.read(new File("clef.png"));
	         final BufferedImage image2 = ImageIO.read(new File("uke.png"));
	         g.drawImage(image, 0, 150, 60, 120, null);
	         g.drawImage(image2, 250, 20, 650, 440, null);
	      }
	      
	      catch (IOException e) {e.printStackTrace();}
	      
		  g.setColor(Color.GREEN);
		  g.drawLine(10, 150, 165, 150);
		  g.drawLine(10, 180, 165, 180);
		  g.drawLine(10, 210, 165, 210);
		  g.drawLine(10, 240, 165, 240);
		  g.drawLine(10, 270, 165, 270);
		  
		  g.drawLine(10, 149, 165, 149);
		  g.drawLine(10, 179, 165, 179);
		  g.drawLine(10, 209, 165, 209);
		  g.drawLine(10, 239, 165, 239);
		  g.drawLine(10, 269, 165, 269);
		  
		  g.drawLine(10, 151, 165, 151);
		  g.drawLine(10, 181, 165, 181);
		  g.drawLine(10, 211, 165, 211);
		  g.drawLine(10, 241, 165, 241);
		  g.drawLine(10, 271, 165, 271);
		  
		  g.drawLine(60, 300, 125, 300);
		  g.drawLine(60, 330, 125, 330);
		  
		  g.drawLine(60,  90, 125, 90);
		  g.drawLine(60, 120, 125, 120);
		  
		  g.setColor(Color.CYAN);
		  if(N[1]) g.fillOval(60, 330, 30, 30);
		  if(N[3]) g.fillOval(60, 300, 30, 30);
          if(N[5]) g.fillOval(60, 270, 30, 30);
		  if(N[7]) g.fillOval(60, 240, 30, 30);
          if(N[9]) g.fillOval(60, 210, 30, 30);
		  if(N[11]) g.fillOval(60, 180, 30, 30);
          if(N[13]) g.fillOval(60, 150, 30, 30);
		  if(N[15]) g.fillOval(60, 120, 30, 30);  
		  if(N[17]) g.fillOval(60, 90, 30, 30);
		  
		  g.setColor(Color.ORANGE);
		  if(!N[1])g.drawOval(60, 330, 30, 30);
		  if(!N[3])g.drawOval(60, 300, 30, 30);
          if(!N[5])g.drawOval(60, 270, 30, 30);
		  if(!N[7])g.drawOval(60, 240, 30, 30);
          if(!N[9]) g.drawOval(60, 210, 30, 30);
		  if(!N[11]) g.drawOval(60, 180, 30, 30);
          if(!N[13]) g.drawOval(60, 150, 30, 30);
		  if(!N[15]) g.drawOval(60, 120, 30, 30);  
		  if(!N[17]) g.drawOval(60, 90, 30, 30);
		  
		  g.setColor(Color.CYAN);
		  if(N[18]) g.fillOval(90, 75, 30, 30);
		  if(N[16]) g.fillOval(90, 105, 30, 30);
		  if(N[14]) g.fillOval(90, 135, 30, 30);
		  if(N[12]) g.fillOval(90, 165, 30, 30);
		  if(N[10]) g.fillOval(90, 195, 30, 30);
		  if(N[8]) g.fillOval(90, 225, 30, 30);
		  if(N[6]) g.fillOval(90, 255, 30, 30);
		  if(N[4]) g.fillOval(90, 285, 30, 30);
		  if(N[2]) g.fillOval(90, 315, 30, 30);
		  
		  g.setColor(Color.ORANGE);
		  if(!N[18]) g.drawOval(90, 75, 30, 30);
		  if(!N[16]) g.drawOval(90, 105, 30, 30);
		  if(!N[14]) g.drawOval(90, 135, 30, 30);
		  if(!N[12]) g.drawOval(90, 165, 30, 30);
		  if(!N[10]) g.drawOval(90, 195, 30, 30);
		  if(!N[8]) g.drawOval(90, 225, 30, 30);
		  if(!N[6]) g.drawOval(90, 255, 30, 30);
		  if(!N[4]) g.drawOval(90, 285, 30, 30);
		  if(!N[2]) g.drawOval(90, 315, 30, 30);
		 
		  //Draw # and b
		  Font font = new Font("Verdana", Font.ITALIC, 35);
	      g.setFont(font);
	      g.setColor(Color.CYAN);
	      if(S[1])g.drawString("#", 35, 358);
	      if(S[3])g.drawString("#", 35, 328);	     
	      if(S[5])g.drawString("#", 35, 298);
	      if(S[7])g.drawString("#", 35, 268);
	      if(S[9])g.drawString("#", 35, 238);
	      if(S[11])g.drawString("#", 35, 208);
	      if(S[13])g.drawString("#", 35, 178);
	      if(S[15])g.drawString("#", 35, 148);
	      if(S[17])g.drawString("#", 35, 118);
	      
	      if(S[2])g.drawString("#", 115, 343);		  
		  if(S[4]) g.drawString("#", 115, 313);
		  if(S[6]) g.drawString("#", 115, 283);
		  if(S[8]) g.drawString("#", 115, 253);
		  if(S[10]) g.drawString("#", 115, 223);
		  if(S[12]) g.drawString("#", 115, 193);
		  if(S[14]) g.drawString("#", 115, 163);
		  if(S[16]) g.drawString("#", 115, 133);
		  
		  if(B[17]) g.drawString("b", 10, 118);
		  if(B[15]) g.drawString("b", 10, 148);
		  if(B[13]) g.drawString("b", 10, 178);
		  if(B[11]) g.drawString("b", 10, 208);
		  if(B[9]) g.drawString("b", 10, 238);
		  if(B[7]) g.drawString("b", 10, 268);
		  if(B[5]) g.drawString("b", 10, 298);
		  if(B[3]) g.drawString("b", 10, 328);
		  
		  if(B[18]) g.drawString("b", 145, 103);
		  if(B[16]) g.drawString("b", 145, 133);
		  if(B[14]) g.drawString("b", 145, 163);
		  if(B[12]) g.drawString("b", 145, 193);
		  if(B[10]) g.drawString("b", 145, 223);
		  if(B[8]) g.drawString("b", 145, 253);
		  if(B[6]) g.drawString("b", 145, 283);
		  if(B[4]) g.drawString("b", 145, 313);
		  if(B[2]) g.drawString("b", 145, 343);
	      
	      g.setColor(Color.ORANGE);
		  if(!S[1]) g.drawString("#", 35, 358);
		  if(!S[3])g.drawString("#", 35, 328); 
		  if(!S[5])g.drawString("#", 35, 298);
		  if(!S[7])g.drawString("#", 35, 268);
	      if(!S[9])g.drawString("#", 35, 238);
	      if(!S[11])g.drawString("#", 35, 208);
	      if(!S[13])g.drawString("#", 35, 178);
	      if(!S[15])g.drawString("#", 35, 148);
	      if(!S[17])g.drawString("#", 35, 118);
	      
	      if(!S[2])g.drawString("#", 115, 343);		  
		  if(!S[4]) g.drawString("#", 115, 313);
		  if(!S[6]) g.drawString("#", 115, 283);
		  if(!S[8]) g.drawString("#", 115, 253);
		  if(!S[10]) g.drawString("#", 115, 223);
		  if(!S[12]) g.drawString("#", 115, 193);
		  if(!S[14]) g.drawString("#", 115, 163);
		  if(!S[16]) g.drawString("#", 115, 133);
		  
		  if(!B[17]) g.drawString("b", 10, 118);
		  if(!B[15]) g.drawString("b", 10, 148);
		  if(!B[13]) g.drawString("b", 10, 178);
		  if(!B[11]) g.drawString("b", 10, 208);
		  if(!B[9]) g.drawString("b", 10, 238);
		  if(!B[7]) g.drawString("b", 10, 268);
		  if(!B[5]) g.drawString("b", 10, 298);
		  if(!B[3]) g.drawString("b", 10, 328);
		  
		  if(!B[18]) g.drawString("b", 145, 103);
		  if(!B[16]) g.drawString("b", 145, 133);
		  if(!B[14]) g.drawString("b", 145, 163);
		  if(!B[12]) g.drawString("b", 145, 193);
		  if(!B[10]) g.drawString("b", 145, 223);
		  if(!B[8]) g.drawString("b", 145, 253);
		  if(!B[6]) g.drawString("b", 145, 283);
		  if(!B[4]) g.drawString("b", 145, 313);
		  if(!B[2]) g.drawString("b", 145, 343);
		
		  //Draw Tab
		  g.setColor(Color.RED);
		  g.drawLine(tab_x,tab_y1,tab_x+30,tab_y1);
		  g.drawLine(tab_x,tab_y2,tab_x+30,tab_y2);
		  g.drawLine(tab_x,tab_y3,tab_x+30,tab_y3);
		  g.drawLine(tab_x,tab_y4,tab_x+30,tab_y4);
		  
		  //LowG HighG
		  Font fontehl = new Font("Verdana", Font.ITALIC, 20);
		  g.setFont(fontehl);
		  if(lowG) g.drawString("LOW G", 20, 60);
		  if (highG) g.drawString("HIGH G", 20, 60);
		  
		  if(showtab)
		  {
		     g.setColor(Color.CYAN);
		     if(error) 
		     {
		    	 Font fonterror = new Font("Verdana", Font.ITALIC, 20);
			     g.setFont(fonterror);
		    	 g.drawString("NO TAB FOUND", 255, 160);
		     }
		     
		     if(!error)
		     {
		    	Font fonttab = new Font("Verdana", Font.ITALIC, 35);
			    g.setFont(fonttab);
		        if(tab[3] != -1) g.drawString(String.valueOf(tab[3]), tab_x, tab_y1+15);
		        if(tab[2] != -1) g.drawString(String.valueOf(tab[2]), tab_x, tab_y2+15);
		        if(tab[1] != -1) g.drawString(String.valueOf(tab[1]), tab_x, tab_y3+15);
		        if(tab[0] != -1) g.drawString(String.valueOf(tab[0]), tab_x, tab_y4+15);
		     }
		     showtab = false;
		     error = false;
		  }
	}
}







