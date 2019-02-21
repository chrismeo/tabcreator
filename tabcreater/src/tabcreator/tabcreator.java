package tabcreator;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class tabcreator {

	public static String piano_part_id ="";
	public static String step = "";
	public static String octave= "";
	public static String alter = "";
	public static Node pitch = null;
	public static Node staff = null;
	public static boolean chord = false;
	public static boolean alter_ = false;
	public static String fileName = "data.txt";
	public static JFrame myframe;
	public static JPanel panel, buttonpanel, buttonpanel2;
	public static JButton buttonselectmusicXML, buttonlowG, buttonhighG ,convertbutton, clearbutton, /*otherTAB,*/ makeTABfile, barline;
	public static Point mousePT;
	static int[] result = new int[]{-1,-1,-1,-1};
	public static String path="C:\\Users\\name\\workspace\\Test.txt";
	public static int numberofnotes;
	public static int[][] tabmatrix;
	public static boolean bar2 = false;
	public static JFileChooser filechooser;
	public static File xmlfile;
	public static HashMap<String, Integer> hash = new HashMap<String, Integer>();
	public static HashMap<Integer, String> hash_tab = new HashMap<Integer, String>();
	
	//{String4, String3, String2, String1}
	public static int[][] PSaiten = new int[][]{
		   {0,-1,-1,-1},
		   {1,-1,-1,-1},
		   {2,-1,-1,-1},
		   {3,-1,-1,-1},
		   {4,-1,-1,-1},
		   {5,0,-1,-1},
		   {6,1,-1,-1},
		   {7,2,-1,-1},
		   {8,3,-1,-1},
		   {9,4,0,-1},
		   {10,5,1,-1},
		   {11,6,2,-1},
		   {12,7,3,-1},
		   {-1,8,4,-1},
		   {-1,9,5,0},
		   {-1,10,6,1},
		   {-1,11,7,2},
		   {-1,12,8,3},
		   {-1,13,9,4},
		   {-1,14,10,5},
		   {-1,15,11,6},
		   {-1,16,12,7},
		   {-1,17,13,8},
		   {-1,-1,14,9},
		   {-1,-1,15,10},
		   {-1,-1,-1,11},
		   {-1,-1,-1,12},
		   {-1,-1,-1,13},
		   {-1,-1,-1,14},
		   {-1,-1,-1,15},		   
    };
	
    public static int[][] PSaitenHighG = new int[][]{
		   {5,0,-1,-1},
		   {6,1,-1,-1},
		   {7,2,-1,-1},
		   {8,3,-1,-1},
		   {9,4,0,-1},
		   {10,5,1,-1},
		   {11,6,2,-1},
		   {0,7,3,-1},
		   {1,8,4,-1},
		   {2,9,5,0},
		   {3,10,6,1},
		   {4,11,7,2},
		   {5,12,8,3},
		   {6,13,9,4},
		   {7,14,10,5},
		   {8,15,11,6},
		   {9,16,12,7},
		   {10,17,13,8},
		   {11,-1,14,9},
		   {-1,-1,15,10},
		   {-1,-1,-1,11},
		   {-1,-1,-1,12},
		   {-1,-1,-1,13},
		   {-1,-1,-1,14},
		   {-1,-1,-1,15},		   
    };
    		
    public static void fill_hashtab()
    {
    	hash_tab.put(-1, "-----");
    	hash_tab.put(0, "--0--");
    	hash_tab.put(1, "--1--");
    	hash_tab.put(2, "--2--");
    	hash_tab.put(3, "--3--");
    	hash_tab.put(4, "--4--");
    	hash_tab.put(5, "--5--");
    	hash_tab.put(6, "--6--");
    	hash_tab.put(7, "--7--");
    	hash_tab.put(8, "--0--");
    	hash_tab.put(9, "--9--");
    	hash_tab.put(10, "-10--");
    	hash_tab.put(11, "-11--");
    	hash_tab.put(12, "-12--");
    	hash_tab.put(13, "-13--");
    	hash_tab.put(14, "-14--");
    	hash_tab.put(15, "-15--");
    	hash_tab.put(16, "-16--");
    	hash_tab.put(17, "-17--");
    }
 
    public static void fill_hash_map()
    {
    	hash.put("barline", -1);
    	hash.put("G3no", 0);
		hash.put("G31", 1);
		hash.put("A3-1", 1);
		hash.put("A3no", 2);
		hash.put("A31", 3);
		hash.put("B3-1", 3);
		hash.put("B3no", 4);
		hash.put("B31", 15);
		hash.put("C4-1", 4);
		hash.put("C4no", 5);
		hash.put("C41", 6);
		hash.put("D4-1", 6);	      
		hash.put("D4no", 7);
		hash.put("D41", 8);
		hash.put("E4-1", 8);
		hash.put("E4no", 9);
		hash.put("E41", 10);
		hash.put("F4-1", 9);
		hash.put("F4no", 10);
		hash.put("F41", 11);	      
	    hash.put("G4-1",11); 	    
	    hash.put("G4no", 12);
		hash.put("G41", 13);
		hash.put("A4-1", 13);
		hash.put("A4no", 14);
		hash.put("A41", 15);		
		hash.put("B4-1", 15);
		hash.put("B4no", 16);	      
		hash.put("B41", 17);	      
	    hash.put("C5-1",16); 	    
	    hash.put("C5no", 17);
		hash.put("C51", 18);
		hash.put("D5-1", 18);
		hash.put("D5no", 19);
		hash.put("D51", 20);
		hash.put("E5-1", 20);
		hash.put("E5no", 21);
		hash.put("E51", 22);
		hash.put("F5-1", 21);
		hash.put("F5no", 22);
		hash.put("F51", 23);
		hash.put("G5-1", 23);	      
		hash.put("G5no", 24);	      
	    hash.put("G51",25); 	    
	    hash.put("A5-1", 25);
		hash.put("A5no", 26);
		hash.put("A51", 27);
		hash.put("B5-1", 27);
		hash.put("B5no", 28);
		hash.put("B51", 29);
		hash.put("C6-1", 28);
		hash.put("C6no", 29);
    }
    
    public static void save_tab()
    {
    	if(!bar2)
    	appendStrToFile(fileName, 
    			hash_tab.get(mypanel.tab[0])+hash_tab.get(mypanel.tab[1])+hash_tab.get(mypanel.tab[2])+hash_tab.get(mypanel.tab[3]));
    	if(bar2) appendStrToFile(fileName, "--|----|----|----|--");
    	for(int i=0;i<mypanel.P.length;i++)mypanel.P[i] = false;
		for(int j=0;j<mypanel.tab.length;j++)mypanel.tab[j] = -1;
		for(int k=0;k<result.length;k++)result[k] = -1;
		set_N_S_B_false();
		bar2=false;
    }
 
	public static void find_tab()
	{ 
        ArrayList wo = new ArrayList();
        int[] w = new int[]{-1,-1,-1,-1};
		List<MyArray> paths = new ArrayList<MyArray>();
		
	    numberofnotes=0; 
		for (int i=0;i<mypanel.P.length;i++)
		{
			if (mypanel.P[i]==true) {
				numberofnotes++;
				wo.add(i);
			}
		}
		
		tabmatrix = new int[numberofnotes][4];
		
		for(int v=0;v<numberofnotes;v++)
		{
			int t= (int) wo.get(v);
			if(mypanel.lowG) tabmatrix[v]=PSaiten[t];
			if(mypanel.highG) tabmatrix[v]=PSaitenHighG[t];
		}
		
		if(tabmatrix.length==1)
		{
			int last=0;
			for(int j=0;j<tabmatrix[0].length;j++)
    	    {
				if(tabmatrix[0][j]!=-1)
				{
					last =j;
				}
    	    }
			
			mypanel.tab[last]=tabmatrix[0][last];
		}
		
		if(tabmatrix.length==2)
		{
			for(int i=0;i<tabmatrix[0].length;i++)
	        {
	    	    for(int j=0;j<tabmatrix[1].length;j++)
	    	    {
	    	       if((i!=j) && (tabmatrix[0][i]!=-1) && (tabmatrix[1][j]!=-1) )
                   {
                	   paths.add(new MyArray(tabmatrix[0][i],tabmatrix[1][j],-1,-1));
                   }	    	    	
	    	    }
	        }
			
			int delta = -1;
			int tab = -1;
					
			for(int i=0;i<paths.size();i++)
			{
				int[] n = paths.get(i).getItems();
				int d = Math.abs(n[0]-n[1]);
				if((delta == -1) || (d<delta))
				{
					delta = d;
					tab = i;
				}
			}
			
			if(tab == -1) 
	        {
	        	for(int i=0;i<tabmatrix[0].length;i++) mypanel.tab[i]= -1;	
	        	mypanel.error = true;
	        	String str = "--x----x----x----x--";
	            appendStrToFile("data.txt", str); 
	        };
	        
	        if(tab > -1)
	        {
               w = paths.get(tab).getItems();
			
			   for(int j=0;j<2;j++)
			   {
			      for(int i=0;i<tabmatrix[0].length;i++)
		  	      {
				      if(tabmatrix[j][i]== w[j]) mypanel.tab[i]= w[j];
		 	      }
			   }
	        }
		}
		
		if(tabmatrix.length==3)
		{
			for(int i=0;i<tabmatrix[0].length;i++)
	        {
	    	    for(int j=0;j<tabmatrix[1].length;j++)
	    	    {
	    	    	for(int k=0;k<tabmatrix[2].length;k++)
	        	    {
	    	           if((i!=j) && (tabmatrix[0][i]!=-1) && (tabmatrix[1][j]!=-1) && (tabmatrix[2][k]!=-1) && (i!=k) && (k!=j))
                       {
                	      paths.add(new MyArray(tabmatrix[0][i],tabmatrix[1][j],tabmatrix[2][k],-1)); 
                       }
	        	    }
	    	    }
	        }
			
			int delta = -1;
			int tab = -1;
			
			for(int i=0; i<paths.size();i++)
	        {
	        	int[] n = paths.get(i).getItems();
	        	int min = n[0];
	        	int max = n[0];
	        	
	        	for(int j=1;j<3/*4*/;j++)
	        	{
	        	   if (n[j]< min) min = n[j];
	        	   if (n[j]> max) max = n[j];
	        	}
	        	
	            int d = max-min;
	        	if((i==0) || (d<delta)){
	        		delta = d;
	        		tab = i;
	        	}
	        }

			if(tab == -1) 
	        {
	        	for(int i=0;i<tabmatrix[0].length;i++) mypanel.tab[i]= -1;	
	        	mypanel.error = true;
	        	String str = "--x----x----x----x--";
	            appendStrToFile("data.txt", str); 
	        };
	        
	        if(tab > -1)
	        {			
			   w = paths.get(tab).getItems();
			
			   for(int j=0;j<3;j++)
			   {
			      for(int i=0;i<tabmatrix[0].length;i++)
		  	      {
				      if(tabmatrix[j][i]== w[j]) mypanel.tab[i]= w[j];
		 	      }
			   }
	        }
		}
		
		if(tabmatrix.length==4)
		{   
	        for(int i=0;i<tabmatrix[0].length;i++)
	        {
	    	    for(int j=0;j<tabmatrix[1].length;j++)
	    	    {
	    		    for(int k=0;k<tabmatrix[2].length;k++)
	        	    {
	    			    for(int l=0;l<tabmatrix[3].length;l++)
	    	    	    {
	    	    		    if((i!=j) && (j!=k) && (k!=i) && (l!=k) && (l!=j) && (l!=i))
	    	    		    {
	    	    			    if((tabmatrix[0][i]!=-1) && (tabmatrix[1][j]!=-1) && (tabmatrix[2][k]!=-1)&& (tabmatrix[3][l]!=-1))
	    	    			    {
	    	    			    	paths.add(new MyArray(tabmatrix[0][i],tabmatrix[1][j],tabmatrix[2][k],tabmatrix[3][l]));
	    	    			    	System.out.println(tabmatrix[0][i]+" , "+tabmatrix[1][j]+" , "+tabmatrix[2][k]+" , "+tabmatrix[3][l]);
	    	    			    }
	    	    		    }
	    	    	    }
	        	    }
	    	    }
	        }
	        
	        int delta = -1;
	        int tab = -1;
	        for(int i=0; i<paths.size();i++)
	        {
	        	int[] n = paths.get(i).getItems();
	        	int min = n[0];
	        	int max = n[0];
	        	
	        	for(int j=1;j<4;j++)
	        	{
	        	   if (n[j]< min) min = n[j];
	        	   if (n[j]> max) max = n[j];
	        	}
	        	
	            int d = max-min;
	        	if((i==0) || (d<delta)){
	        		delta = d;
	        		tab = i;
	        	}
	        }
	        
	        if(tab == -1) 
	        {
	        	for(int i=0;i<tabmatrix[0].length;i++) mypanel.tab[i]= -1;	
	        	mypanel.error = true;
	        	String str = "--x----x----x----x--";
	            appendStrToFile("data.txt", str); 
	        };
	        
	        if(tab > -1)
	        {
               w = paths.get(tab).getItems();
			
			   for(int j=0;j<4;j++)
			   {
			      for(int i=0;i<tabmatrix[0].length;i++)
		  	      {
				      if(tabmatrix[j][i]== w[j]) mypanel.tab[i]= w[j];
		 	      }
			   }
	        }
		}
	}
	
	
	
	public static void appendStrToFile(String fileName, String str) 
    { 
        try
        { 
            BufferedWriter out = new BufferedWriter( new FileWriter(fileName, true)); 
            out.write(str); 
            out.newLine();
            out.close(); 
        }
        
        catch (IOException e)
        { 
            System.out.println("exception occoured" + e); 
        } 
    } 
	
	public static void process_xmlfile()
	{ 
		File file = new File("pitch.txt"); 		
		file.deleteOnExit();
		BufferedReader countlines;
		String s;
		   
		try 
		{
			  countlines = new BufferedReader(new FileReader(file));
			  
			  while ((s = countlines.readLine()) != null)
			  {			
				  StringTokenizer st = new StringTokenizer(s);
				  while(st.hasMoreTokens())
				  {
				      String str2 = st.nextToken(); 
				      
				      if(str2.compareTo("barline")==0) bar2= true; 				      
				      if((str2.compareTo("barline")!=0) && (hash.get(str2) != null)) mypanel.P[hash.get(str2)] = true; 				    		  
			      }
				  
				  find_tab();	
				  save_tab();
			  }
			 
			  countlines.close();
			  makeTABfile.doClick();
		 }
	
		 catch (FileNotFoundException e2) {e2.printStackTrace();}
		 catch (IOException e) {e.printStackTrace();} 
	}
	
	public static void parse_xmlfile()
	{
		boolean line_end = false;
		int last= -1; //0 if 'no', 1 if 'chord'
		int now = -1; //0 if 'no', 1 if 'chord'
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{
        	File filepitch = new File("pitch.txt");
    		BufferedWriter out = new BufferedWriter(new FileWriter(filepitch, true));
        	dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        	DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc= db.parse(xmlfile);
			doc.getDocumentElement().normalize();
			
	        NodeList testlist =  doc.getElementsByTagName("part-name");
	       
	        for(int i=0;i<testlist.getLength();i++)
	        {
	        	if(testlist.item(i).getTextContent().compareTo("Piano")==0)
	        	{ 
	        	   Node scorepart = testlist.item(i).getParentNode();
	        	   Element scorepart_element = (Element) scorepart;
	        	   piano_part_id = scorepart_element.getAttribute("id");
	        	   System.out.println("ID: "+scorepart_element.getAttribute("id"));
	        	}
	        }
		
	       NodeList partlist = doc.getElementsByTagName("part");	       
	       
	       for(int j=0;j<partlist.getLength();j++)
	       {
	    	   Node part = partlist.item(j);
	    	   Element part_element = (Element) part;
	    	   
	    	   if(part_element.getAttribute("id").compareTo(piano_part_id)==0)
	    	   {   	    		   
	    		   for(int t=0;t<part.getChildNodes().getLength();t++)
	    		   {
	    		       if(part.getChildNodes().item(t).getNodeName().compareTo("measure") == 0)
	    		       {   	    		    	  	    		    	   
	    		    	   Node measure = part.getChildNodes().item(t);
	    		    	   for(int s=0;s<measure.getChildNodes().getLength();s++)
	    		    	   {
	    		    		   if(measure.getChildNodes().item(s).getNodeName().compareTo("note")==0)
	    		    		   { 
	    		    			   Node note = measure.getChildNodes().item(s);
	    		    			   chord = false; pitch = null; octave = ""; alter = ""; step = ""; alter_=false;
	    		    			   
	    		    			   for(int u=0;u<note.getChildNodes().getLength();u++)
	    		    			   {
	    		    				   if(note.getChildNodes().item(u).getNodeName().compareTo("chord")==0)  chord = true; 
	    		    				   
	    		    			       if(note.getChildNodes().item(u).getNodeName().compareTo("pitch")==0)  pitch = note.getChildNodes().item(u); 
	    		    			       
	    		    			       if((note.getChildNodes().item(u).getNodeName().compareTo("staff")==0) && (pitch!=null) &&
	    		    			    		   (Integer.valueOf(note.getChildNodes().item(u).getTextContent())==1))
	    		    			       {	  		    			    	 
	    		    			    	   for(int z=0;z<pitch.getChildNodes().getLength();z++)
	    		    			    	   {   
	    		    			    		   if(pitch.getChildNodes().item(z).getNodeName().compareTo("alter")==0)
	    		    			    		   {
	    		    			    			   alter = pitch.getChildNodes().item(z).getTextContent();
	    		    			    			   alter_ = true;
	    		    			    		   }
	    		    			    		   
	    		    			    		   if(pitch.getChildNodes().item(z).getNodeName().compareTo("step")==0) 
	    		    			    		   { 
	    		    			    			   step = pitch.getChildNodes().item(z).getTextContent();
	    		    			    		   }
	    		    			    		   
	    		    			    		   if(pitch.getChildNodes().item(z).getNodeName().compareTo("octave")==0) 
	    		    			    		   {
	    		    			    			   octave = pitch.getChildNodes().item(z).getTextContent();
	    		    			    		   }
	    		    			    	   }	
	    		    			    	   
	    		    			    	   String str = step+octave;
	    		    			    	   if(alter_) str = str+alter;
	    		    			    	   if(!alter_) str = str+"no";
	    		    			    	   if(!chord) now = 0;
	    		    			    	   if(chord) now = 1;
	    		    			    	   if(((now==0) && (last==0)) || ((last==1) && (now==0)) )line_end = true;		   
	    		    			    	   else line_end = false;
	    		    			    	   if(line_end)
	    		    			    	   {
	    		    			    		   out.newLine();
	    		    			    	       out.write(str+" ");
	    		    			    	   }
	    		    			    	   if(!line_end) out.write(str+" ");	    		    			    	   
	    		    			    	   last = now;
	    		    			    	   alter_ = false;
	    		    			    	   chord = false;	    		    			    	 
	    		    			       }	    		    			       	    		    			       
	    		    			   }
	    		    		   }
	    		    	   } 
	    		    	   out.newLine();
	    		    	   out.write("barline");
	    		       }
	    		   }	  		  	    		   
	    	   } 
	       }
	       out.close();	        
		}
		
		catch (ParserConfigurationException e) {e.printStackTrace();}
        catch (SAXException | IOException e) {e.printStackTrace();}
	}
	
	public static void set_N_S_B_false()
	{
		for(int i=0;i<mypanel.S.length;i++) mypanel.S[i]=false;
		for(int i=0;i<mypanel.B.length;i++) mypanel.B[i]=false;
		for(int i=0;i<mypanel.N.length;i++) mypanel.N[i]=false; 
	}
	
	private static void unzip(String zipFilePath, String destDir) throws IOException
	{
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        
        fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry ze = zis.getNextEntry();
        while(ze != null)
        {
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            System.out.println("Unzipping to "+newFile.getAbsolutePath());
            //create directories for sub directories in zip
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
            }
            fos.close();
            //close this ZipEntry
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        //close last ZipEntry
        zis.closeEntry();
        zis.close();
        fis.close();
	}
	
	public static void main(String[] args) 
	{	
		fill_hash_map();
		fill_hashtab();
        
        try { 
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName)); 
            out.close(); 
            
        } 
        
        catch (IOException e) { 
            System.out.println("Exception Occurred" + e); 
        } 	
        
	   myframe = new JFrame("ConvertNote");
	   myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   myframe.getContentPane().setLayout(new BoxLayout(myframe.getContentPane(),BoxLayout.Y_AXIS));   
	   buttonlowG = new JButton("Low G");
	   buttonhighG = new JButton("High G");
	   buttonselectmusicXML = new JButton("select musicXML File and convert");
	   panel = new mypanel();
	   panel.setPreferredSize(new Dimension(1000,500));
       panel.setBackground(/*Color.DARK_GRAY*/Color.WHITE);
       filechooser = new JFileChooser();
       
       buttonselectmusicXML.addActionListener(new ActionListener()
       {
    	  @Override
 		  public void actionPerformed(ActionEvent arg0)
 		  {
    		  int returnVal = filechooser.showOpenDialog(myframe); 
    		  
    		  if (returnVal == JFileChooser.APPROVE_OPTION)
    		  {
    		      xmlfile = filechooser.getSelectedFile(); 
    		      
    		      //if file ends with .mxl unzip fist
    		      String name = xmlfile.getName();
    		      int length = name.length();
    		      if(name.substring(length-3, length).compareTo("mxl")==0) 
    		      {
    		    	  System.out.println("mxl");    		    	        		     
    		    	  String zipFilePath = xmlfile.getAbsolutePath();
    		  		  String destDir = "output/";
    		  		  
    		  		  try 
    				  {
    					  unzip(zipFilePath, destDir);
    					  File folder = new File("output/");
    					  xmlfile = folder.listFiles()[0];
    					  System.out.println(xmlfile.getName());
    					  parse_xmlfile(); 
        	    	      process_xmlfile();
    				  } 
    				
    				  catch (IOException e) {e.printStackTrace();}
    		      }
    		      
    		      else
    		      {
    		          parse_xmlfile(); 
    	    	      process_xmlfile();
    		      }
    		  }
    		 
    		  else
    		  {
    		      System.out.println("File access cancelled by user.");
    		  }    
 		  }
       });
    	  
       buttonlowG.addActionListener(new ActionListener()
       {
    	  @Override
 		  public void actionPerformed(ActionEvent arg0)
    	  {    		 
    		  mypanel.lowG=true;
    		  mypanel.highG=false;
    		  mypanel.showtab = true;
    		  panel.repaint();
    	  }
       });  
       
       buttonhighG.addActionListener(new ActionListener()
       {
    	  @Override
 		  public void actionPerformed(ActionEvent arg0)
    	  {    		 
    		  mypanel.highG=true;
    		  mypanel.lowG=false;
    		  mypanel.showtab = true; System.out.println("highg");
    		  panel.repaint();
    	  }
       });  
       
       panel.addMouseListener(new MouseAdapter()
       {
           @Override
           public void mousePressed(MouseEvent e) {
               mousePT = e.getPoint();
               
               if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>330) && (mousePT.getY()<360)){
            	   mypanel.N[1] = !mypanel.N[1];
            	   mypanel.P[0] = mypanel.N[1];
               }
               
               if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>315) && (mousePT.getY()<345)){
            	   mypanel.N[2] = !mypanel.N[2];
            	   mypanel.P[2] = mypanel.N[2];
               }
               
               if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>300) && (mousePT.getY()<330)){
            	   mypanel.N[3] = !mypanel.N[3];
            	   mypanel.P[4] = mypanel.N[3];
               }
               
               if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>285) && (mousePT.getY()<315)){
            	   mypanel.N[4] = !mypanel.N[4];
            	   mypanel.P[5] = mypanel.N[4];
               }
               
               if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>270) && (mousePT.getY()<300)) {
            	   mypanel.N[5] = !mypanel.N[5];
            	   mypanel.P[7] = mypanel.N[5];
               }
               
               if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>255) && (mousePT.getY()<285)){
            	   mypanel.N[6] = !mypanel.N[6];
            	   mypanel.P[9] = mypanel.N[6];
               }
               
               if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>240) && (mousePT.getY()<270)){
            	   mypanel.N[7] = !mypanel.N[7];
            	   mypanel.P[10] = mypanel.N[7];
               }
               
               if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>225) && (mousePT.getY()<255)){
            	   mypanel.N[8] = !mypanel.N[8];
            	   mypanel.P[12] = mypanel.N[8];
               }
               
               if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>210) && (mousePT.getY()<240)){
            	   mypanel.N[9] = !mypanel.N[9];
            	   mypanel.P[14] = mypanel.N[9];
               }
               
               if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>195) && (mousePT.getY()<225)){
            	   mypanel.N[10] = !mypanel.N[10];
            	   mypanel.P[16] = mypanel.N[10];
               }
               
               if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>180) && (mousePT.getY()<210)){
            	   mypanel.N[11] = !mypanel.N[11];
            	   mypanel.P[17] = mypanel.N[11];
               }
               
               if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>165) && (mousePT.getY()<195)){
            	   mypanel.N[12] = !mypanel.N[12];
            	   mypanel.P[19] = mypanel.N[12];
               }
               
               if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>150) && (mousePT.getY()<180)){
            	   mypanel.N[13] = !mypanel.N[13];
            	   mypanel.P[21] = mypanel.N[13];
               }
               
               if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>135) && (mousePT.getY()<165)){
            	   mypanel.N[14] = !mypanel.N[14];
            	   mypanel.P[22] = mypanel.N[14];
               }
               
               if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>120) && (mousePT.getY()<150)){
            	   mypanel.N[15] = !mypanel.N[15];
            	   mypanel.P[24] = mypanel.N[15];
               }
               
               if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>105) && (mousePT.getY()<135)){
            	   mypanel.N[16] = !mypanel.N[16];
            	   mypanel.P[26] = mypanel.N[16];
               }
               
               if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>90) && (mousePT.getY()<120)) {
            	   mypanel.N[17] = !mypanel.N[17];
            	   mypanel.P[28] = mypanel.N[17];
               }
               
               if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>75) && (mousePT.getY()<105)) {
            	   mypanel.N[18] = !mypanel.N[18];
            	   mypanel.P[29] = mypanel.N[18];
               }
               
               //# und b markieren
               if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>330) && (mousePT.getY()<360)) {
            	   mypanel.S[1] = !mypanel.S[1];
            	   mypanel.N[1] = mypanel.S[1];
            	   mypanel.P[1] = mypanel.S[1];
               }
               
               if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>300) && (mousePT.getY()<330)){
            	   mypanel.S[3] = !mypanel.S[3];
            	   mypanel.N[3] = mypanel.S[3];
            	   mypanel.P[5] = mypanel.S[3];
            	   mypanel.B[3] = false;
               }
               
               if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>270) && (mousePT.getY()<300)){
            	   mypanel.S[5] = !mypanel.S[5];
            	   mypanel.N[5] = mypanel.S[5];
            	   mypanel.P[8] = mypanel.S[5];
            	   mypanel.B[5] = false;
               }
               
               if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>240) && (mousePT.getY()<270)){
            	   mypanel.S[7] = !mypanel.S[7];
            	   mypanel.N[7] = mypanel.S[7];
            	   mypanel.P[11] = mypanel.S[7];
            	   mypanel.B[7] = false;
               }
               
               if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>210) && (mousePT.getY()<240)){
            	   mypanel.S[9] = !mypanel.S[9];
            	   mypanel.N[9] = mypanel.S[9];
            	   mypanel.P[15] = mypanel.S[9];
            	   mypanel.B[9] = false;
               }
               
               if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>180) && (mousePT.getY()<210)){
            	   mypanel.S[11] = !mypanel.S[11];
            	   mypanel.N[11] = mypanel.S[11];
            	   mypanel.P[18] = mypanel.S[11];
            	   mypanel.B[11] = false;
               }
               
               if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>150) && (mousePT.getY()<180)){
            	   mypanel.S[13] = !mypanel.S[13];
            	   mypanel.N[13] = mypanel.S[13];
            	   mypanel.P[22] = mypanel.S[13];
            	   mypanel.B[13] = false;
               }
               
               if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>120) && (mousePT.getY()<150)){
            	   mypanel.S[15] = !mypanel.S[15];
            	   mypanel.N[15] = mypanel.S[15];
            	   mypanel.P[25] = mypanel.S[15];
            	   mypanel.B[15] = false;
               }
               
               if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>90) && (mousePT.getY()<120)){
            	   mypanel.S[17] = !mypanel.S[17];
            	   mypanel.N[17]= mypanel.S[17];
            	   mypanel.P[29] = mypanel.S[17];
            	   mypanel.B[17] = false;
               }
               
               if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>315) && (mousePT.getY()<345)){
            	   mypanel.S[2] = !mypanel.S[2];
            	   mypanel.N[2] = mypanel.S[2];
            	   mypanel.P[3] = mypanel.S[2];
            	   mypanel.B[2] = false;
               }
               
               if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>285) && (mousePT.getY()<315)){
            	   mypanel.S[4] = !mypanel.S[4];
            	   mypanel.N[4] = mypanel.S[4];
            	   mypanel.P[6] = mypanel.S[4];
            	   mypanel.B[4] = false;
               }
               
               if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>255) && (mousePT.getY()<285)){
            	   mypanel.S[6] = !mypanel.S[6];
            	   mypanel.N[6] = mypanel.S[6];
            	   mypanel.P[10] = mypanel.S[6];
            	   mypanel.B[6] = false;
               }
               
               if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>225) && (mousePT.getY()<255)){
            	   mypanel.S[8] = !mypanel.S[8];
            	   mypanel.N[8] = mypanel.S[8];
            	   mypanel.P[13] = mypanel.S[8];
            	   mypanel.B[8] = false;
               }
               
               if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>185) && (mousePT.getY()<225)){
            	   mypanel.S[10] = !mypanel.S[10];
            	   mypanel.N[10] = mypanel.S[10];
            	   mypanel.P[17]= mypanel.S[10];
            	   mypanel.B[10] = false;
               }
               
               if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>155) && (mousePT.getY()<185)){
            	   mypanel.S[12] = !mypanel.S[12];
            	   mypanel.N[12] = mypanel.S[12];
            	   mypanel.P[20] = mypanel.S[12];
            	   mypanel.B[12] = false;
               }
               
               if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>125) && (mousePT.getY()<155)){
            	   mypanel.S[14] = !mypanel.S[14];
            	   mypanel.N[14] = mypanel.S[14];
            	   mypanel.P[23] =mypanel.S[14];
            	   mypanel.B[14] = false;
               }
               
               if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>95) && (mousePT.getY()<125)){
            	   mypanel.S[16] = !mypanel.S[16];
            	   mypanel.N[16] = mypanel.S[16];
                   mypanel.P[27] = mypanel.S[16];
                   mypanel.B[16] = false;
               }
               
               if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>300) && (mousePT.getY()<330)){
            	   mypanel.B[3] = !mypanel.B[3];
            	   mypanel.N[3] = mypanel.B[3];
            	   mypanel.P[3] = mypanel.B[3];
            	   mypanel.S[3] = false;
               }
               
               if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>270) && (mousePT.getY()<300)){
            	   mypanel.B[5] = !mypanel.B[5];
            	   mypanel.N[5] = mypanel.B[5];
            	   mypanel.P[6] = mypanel.B[5];
            	   mypanel.S[5] = false;
               }
               
               if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>240) && (mousePT.getY()<270)){
            	   mypanel.B[7] = !mypanel.B[7];
            	   mypanel.N[7] = mypanel.B[7];
            	   mypanel.P[9] = mypanel.B[7];
            	   mypanel.S[7] = false;
               }
               
               if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>210) && (mousePT.getY()<240)){
            	   mypanel.B[9] = !mypanel.B[9];
            	   mypanel.N[9] = mypanel.B[9];
            	   mypanel.P[13] = mypanel.B[9];
            	   mypanel.S[9] = false;
               }
               
               if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>180) && (mousePT.getY()<210)){
            	   mypanel.B[11] = !mypanel.B[11];
            	   mypanel.N[11] = mypanel.B[11];
            	   mypanel.P[16] = mypanel.B[11];
            	   mypanel.S[11] = false;
               }
               
               if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>150) && (mousePT.getY()<180)){
            	   mypanel.B[13] = !mypanel.B[13];
            	   mypanel.N[13] = mypanel.B[13];
            	   mypanel.P[20] = mypanel.B[13];
            	   mypanel.S[13] = false;
               }
               
               if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>120) && (mousePT.getY()<150)){
            	   mypanel.B[15] = !mypanel.B[15];
            	   mypanel.N[15] = mypanel.B[15];
            	   mypanel.P[23] = mypanel.B[15];
            	   mypanel.S[15] = false;
               }
               
               if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>90) && (mousePT.getY()<120)){
            	   mypanel.B[17] = !mypanel.B[17];
            	   mypanel.N[17] = mypanel.B[17];
            	   mypanel.P[27] = mypanel.B[17];
            	   mypanel.S[17] = false;
               }
               
               if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>315) && (mousePT.getY()<345)){
            	   mypanel.B[2] = !mypanel.B[2];
            	   mypanel.N[2] = mypanel.B[2];
            	   mypanel.P[1] = mypanel.B[2];
            	   mypanel.S[2] = false;
               }
               
               if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>285) && (mousePT.getY()<315)){
            	   mypanel.B[4] = !mypanel.B[4];
            	   mypanel.N[4] = mypanel.B[4];
            	   mypanel.P[4] = mypanel.B[4];
            	   mypanel.S[4] = false;
               }
               
               if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>255) && (mousePT.getY()<285)){
                   mypanel.B[6] = !mypanel.B[6];
                   mypanel.N[6] = mypanel.B[6];
                   mypanel.P[8] = mypanel.B[6];
                   mypanel.S[6] = false;
               }    
                   
               if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>225) && (mousePT.getY()<255)){
            	   mypanel.B[8] = !mypanel.B[8];
            	   mypanel.N[8] = mypanel.B[8];
            	   mypanel.P[15] = mypanel.B[8];
            	   mypanel.S[8] = false;
               }
               
               if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>185) && (mousePT.getY()<225)) 
               {
            	   mypanel.B[10] = !mypanel.B[10];
            	   mypanel.N[10] = mypanel.B[10];
            	   mypanel.P[15] = mypanel.B[10];
            	   mypanel.S[10] = false;
               }
               
               if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>155) && (mousePT.getY()<185)){
            	   mypanel.B[12] = !mypanel.B[12];
            	   mypanel.N[12] = mypanel.B[12];
            	   mypanel.P[18] = mypanel.B[12];
            	   mypanel.S[12] = false;
               }
               
               if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>125) && (mousePT.getY()<155)){
            	   mypanel.B[14] = !mypanel.B[14];
            	   mypanel.N[14] = mypanel.B[14];
            	   mypanel.P[21] = mypanel.B[14];
            	   mypanel.S[14] = false;
               }
               
               if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>95) && (mousePT.getY()<125)){
            	   mypanel.B[16] = !mypanel.B[16];
            	   mypanel.N[16] = mypanel.B[16];
            	   mypanel.P[25] =mypanel.B[16];
            	   mypanel.S[16]= false;
               }
               
               if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>65) && (mousePT.getY()<95)){
            	   mypanel.B[18] = !mypanel.B[18];
            	   mypanel.N[18] = mypanel.B[18];
            	   mypanel.P[28] = mypanel.B[18];
               }
               
        	   panel.repaint();  
           }
       });
       
       convertbutton= new JButton("Convert");
       clearbutton = new JButton("Clear");
       makeTABfile = new JButton("makeTABfile");
       barline = new JButton("Insert bar '|'");
       
       clearbutton.addActionListener(new ActionListener()
       {
    	  @Override
 		  public void actionPerformed(ActionEvent arg0)
    	  {   
    		  save_tab();	   		 
    		  panel.repaint();
    	  }
       });
       
       convertbutton.addActionListener(new ActionListener()
       {
		  @Override
		  public void actionPerformed(ActionEvent arg0) {
	
            if((mypanel.N[1])&&(mypanel.S[1])){
               mypanel.P[0]=false;          
            }
     
            if((mypanel.N[2])&&((mypanel.S[2]) || (mypanel.B[2]))){
                mypanel.P[2]=false;          
            }
            
            if((mypanel.N[3])&&((mypanel.S[3]) || (mypanel.B[3]))){            		
                mypanel.P[4]=false;          
            }
            
            if((mypanel.N[4])&&((mypanel.S[4]) || (mypanel.B[4]))){
                mypanel.P[5]=false;          
            }
            
            if((mypanel.N[5])&&((mypanel.S[5]) || (mypanel.B[5]))){
                mypanel.P[7]=false;          
            }
            
            if((mypanel.N[6])&&((mypanel.S[6]) || (mypanel.B[6]))){
                mypanel.P[9]=false;          
            }
            
            if((mypanel.N[7])&&((mypanel.S[7]) || (mypanel.B[7]))){
                mypanel.P[10]=false;          
            }
            
            if((mypanel.N[8])&&((mypanel.S[8]) || (mypanel.B[8]))){
                mypanel.P[12]=false;          
            }
            
            if((mypanel.N[9])&&((mypanel.S[9]) || (mypanel.B[9]))){
                mypanel.P[14]=false;          
            }
            
            if((mypanel.N[10])&&((mypanel.S[10]) || (mypanel.B[10]))){
                mypanel.P[16]=false;          
            }
            
            if((mypanel.N[11])&&((mypanel.S[11]) || (mypanel.B[11]))){
                mypanel.P[17]=false;          
            }
            
            if((mypanel.N[12])&&((mypanel.S[12]) || (mypanel.B[12]))){
                mypanel.P[19]=false;          
            }
            
            if((mypanel.N[13])&&((mypanel.S[13]) || (mypanel.B[13]))){
                mypanel.P[21]=false;          
            }
            
            if((mypanel.N[14])&&((mypanel.S[14]) || (mypanel.B[14]))){
                mypanel.P[22]=false;          
            }
            
            if((mypanel.N[15])&&((mypanel.S[15]) || (mypanel.B[15]))){
                mypanel.P[24]=false;          
            }
            
            if((mypanel.N[16])&&((mypanel.S[16]) || (mypanel.B[16]))){
                mypanel.P[26]=false;          
            }
            
            if((mypanel.N[17])&&((mypanel.S[17]) || (mypanel.B[17]))){
                mypanel.P[28]=false;          
            }
            
            if((mypanel.N[18])&&(mypanel.B[18])){
                mypanel.P[29]=false;          
            }
            	
           	
            find_tab();          
            mypanel.showtab = true;
            panel.repaint();
		  }
       });
       
       barline.addActionListener(new ActionListener()
       {
		  @Override
		  public void actionPerformed(ActionEvent arg0)
		  {
			  bar2 = true;
		  }
       });
       
       makeTABfile.addActionListener(new ActionListener()
       {
		  @Override
		  public void actionPerformed(ActionEvent arg0)
		  {
			  File file = new File("data.txt"); file.deleteOnExit();
			  File f = new File("TABS.txt");
			  
			  if(f.exists() && !f.isDirectory())
			  { 
			      f.delete();
			  }
			  
			  File fileout = new File("TABS.txt");
			  int lines = 0;
			  BufferedReader countlines;
			  String s;
			  try 
			  {
				  countlines = new BufferedReader(new FileReader(file));
				  while ((s = countlines.readLine()) != null)
				  {
					 lines++;
				  }
				  countlines.close();
			  }
			  
			  catch (FileNotFoundException e2) {e2.printStackTrace();}
			  catch (IOException e) {e.printStackTrace();}
			  			  
			  BufferedReader br1;
			  BufferedReader br2;
			  BufferedReader br3;
			  BufferedReader br4;
			  
		 	  try
		 	  {
				 br4 = new BufferedReader(new FileReader(file));
				 br3 = new BufferedReader(new FileReader(file));
				 br2 = new BufferedReader(new FileReader(file));
				 br1 = new BufferedReader(new FileReader(file));
				 String st; 
			
				 BufferedWriter out = new BufferedWriter(new FileWriter(fileout, true)); 
				 
				 int length = 20; 
				 int loop = 0;
				 int wieoft = (int) Math.ceil(lines/length);
				 boolean cont = true;
				 
				 while(cont)
				 {  
					 int i1=0;
					 int i2=0; int i3=0; int i4=0;
					 if(loop==wieoft) cont = false;
					 out.write("A||");
					 					 
					 while (((st = br4.readLine()) != null) && (i4<length))
					 {
						  String string4 = st.substring(15,19);
						  out.write(string4); 
						  i4++;						 
					 }
					 
					 if(st!=null)
					 {
			             String string4 = st.substring(15,19);
			             out.write(string4);
			         }
			  
					 out.newLine();					 
					 out.write("E||");
					 
					 while (((st= br3.readLine()) != null) && (i3<length))
					 { 
						 String string3 = st.substring(10,14); 
						 out.write(string3);
						 i3++;
					 }
					 
					 if(st!=null)
					 {
					     String string3 = st.substring(10,14);
					     out.write(string3);
					 }
					 
	                 out.newLine();	                
	                 out.write("C||");
	                 
					 while (((st = br2.readLine()) != null) && (i2<length)) 
					 { 
						 String string2 = st.substring(5,9); 
						 out.write(string2);
						 i2++;
					 }
					 
					 if(st!=null)
					 {
					     String string2 = st.substring(5,9);
					     out.write(string2);
					 }
					 
	                 out.newLine();	                 
	                 out.write("G||");
	                 
					 while (((st = br1.readLine()) != null) && (i1<length)) 
					 { 
						 String string1 = st.substring(0,4); 
						 out.write(string1); 
						 i1++;
					 }
					 
					 if(st!=null)
					 {
					     String string1 = st.substring(0,4);
					     out.write(string1);
					 }
					 
					 out.newLine(); out.newLine(); out.newLine();out.newLine(); out.newLine(); 
					 loop++;
				 }
				 
				 out.close();
				 br4.close();
				 br3.close();
				 br2.close();
				 br1.close();
			  }
		 	  
		 	  catch (FileNotFoundException e1) {e1.printStackTrace();}		 	  
		 	  catch (IOException e) {e.printStackTrace();} 
		 	  
		      JOptionPane.showInternalMessageDialog(panel, "TAB file written");
		  }
       });
       
       buttonpanel2 = new JPanel();
       buttonpanel2.add(convertbutton);
       buttonpanel2.add(clearbutton);
       buttonpanel2.add(makeTABfile);
       buttonpanel2.add(barline);
       panel.add(buttonpanel2);
       panel.repaint();
       buttonpanel = new JPanel();
       buttonpanel.add(buttonlowG);
       buttonpanel.add(buttonhighG);
       buttonpanel.add(buttonselectmusicXML);
       myframe.getContentPane().add(buttonpanel);
	   myframe.getContentPane().add(panel);
	   myframe.pack();
	   myframe.setVisible(true);
	} 
}

