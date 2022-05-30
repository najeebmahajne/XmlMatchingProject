import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;
import org.apache.pdfbox.util.Matrix;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;


public class PDFExtractionByAreas {
	ArrayList<String> arrlist= new ArrayList<String>(5); 
	
	private static String[] PDFFILESARRAY = new String[5];
	private static String[] BODYOUTPUTFILESARRAY = new String[5];
	private static String[] MARGINSOUTPUTFILESARRAY = new String[5];
	private static String[] COVERPAGEOUTPUTFILESARRAY = new String[5];
	public static void main(String[] args) throws IOException {
		    // Instantiate the Factory
			//laws' pdf file 
			//File pdfFile = new File("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\28122812.pdf");
			//File pdfFile1 = new File("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\2319-2382\\2367.pdf");
		
		BODYOUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2790\\2790BODYTEXT.txt";
		//BODYOUTPUTFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2756\\2756BODYTEXT.txt";
		//BODYOUTPUTFILESARRAY[2]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2762\\2762BODYTEXT.txt";
		//BODYOUTPUTFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2763\\2763BODYTEXT.txt";
		//BODYOUTPUTFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2765\\2765BODYTEXT.txt";
		PDFFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2790\\2790.pdf";
		//PDFFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2756\\2756.pdf";
		//PDFFILESARRAY[2]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2762\\2762.pdf";
		//PDFFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2763\\2763.pdf";
		//PDFFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2765\\2765.pdf";
		MARGINSOUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2790\\2790MARGINSTEXT.txt";
		//MARGINSOUTPUTFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2756\\2756MARGINSTEXT.txt";
		//MARGINSOUTPUTFILESARRAY[2]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2762\\2762MARGINSTEXT.txt";
		//MARGINSOUTPUTFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2763\\2763MARGINSTEXT.txt";
		//MARGINSOUTPUTFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2765\\2765MARGINSTEXT.txt";
		COVERPAGEOUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2790\\2790COVERPAGETEXT.txt";
		for(int fileNum=0;fileNum<5;fileNum++) {
			
		
		try {
			PDDocument doc = PDDocument.load(new File(PDFFILESARRAY[fileNum])); // Instantiate PDFTextStripper class
			PDDocumentInformation info = doc.getDocumentInformation();
	        System.out.println("Page Count= " + doc.getNumberOfPages());
	        System.out.println("Title = " + info.getTitle());
	        System.out.println("Author = " + info.getAuthor());
	        
	        COSDocument info1=doc.getDocument();
	        System.out.println("StartXref = " + info1.getStartXref());
	        System.out.println("Version = " + info1.getVersion());
	        
			
	        
	       
	        
			PDFTextStripperByArea stripper = new PDFTextStripperByArea(); // Retrieving text from PDF document 
			stripper.setSortByPosition( true );
			
			
		
		      int pagesNum=doc.getNumberOfPages();
		      FileWriter myWriter = new FileWriter(new File(BODYOUTPUTFILESARRAY[fileNum]),true);
		      FileWriter myWriter1 = new FileWriter(new File(MARGINSOUTPUTFILESARRAY[fileNum]),true);
		      FileWriter myWriter2 = new FileWriter(new File(COVERPAGEOUTPUTFILESARRAY[fileNum]),true);
		      
		      PDPage coverPage=doc.getPage(0);
		      Rectangle coverRect = new Rectangle( 96, 146, 400, 560 );
		      stripper.addRegion( "cover", coverRect );
		      stripper.extractRegions( coverPage );
		      String coverText=stripper.getTextForRegion("cover");
		      String[] coverSentences=coverText.split("\r\n");
		      for(int i=0;i<coverSentences.length;i++) 
		      {
		    	  if(coverSentences[i].contains("5")&&coverSentences[i].contains("قانون")) {
		    		  int indx=coverSentences[i].indexOf("5");
		    		  coverSentences[i]=coverSentences[i].substring(0, indx+4);
		    		  coverSentences[i]=coverSentences[i].replaceAll("\\d\\d\\s...", "");
		    	  }
		      }
		      for(int i=0;i<coverSentences.length;i++) 
		      {
		    	  if(coverSentences[i].isBlank())
	            		continue;
		    	  myWriter2.write(coverSentences[i]+".\n");
		      }
		      myWriter2.close();
		      stripper.removeRegion("cover");
		      for(int pageNum=1;pageNum<pagesNum;pageNum++) {
		      Rectangle rect = new Rectangle( 96, 146, 360, 560 );
		      PDPage firstPage = doc.getPage(pageNum);
			  Rectangle rect1 = new Rectangle( 450, 146, 42, 535 );
		      stripper.addRegion("content", rect );
		      stripper.addRegion("margins", rect1 );
		      stripper.extractRegions( firstPage );
		      String text=stripper.getTextForRegion("content");
				
				 
				// if(pageNum==1) {
		    //  stripper.setParagraphStart(regex);}
		      //stripper.setParagraphEnd("\\.");
		     // Reshaper reshape=new Reshaper();
		     
		    // List<TextPositionSequence>list= s.findSubwords(doc, pageNum, x);
		 /*    for(int i=0;i<list.size();i++) 
		      {
		    	 System.out.println(list.get(i));
		      }*/
		      
		     
		      
		      String[] sentences=text.split("[\\.|*]");
		      //.split(stripper.getParagraphStart()
		      for(int i=0;i<sentences.length;i++) 
		      {
		    	  if(sentences[i].contains("بنيامين")&&sentences[i].contains("الكنيست")) {
		    		  int firstIndx=sentences[i].indexOf("بنيامين");
		    		  int lastIndx=sentences[i].indexOf("الكنيست");
		    		  if(lastIndx>firstIndx)
		    			  sentences[i]=sentences[i].substring(0, firstIndx)+sentences[i].substring(lastIndx+7);
		    		  System.out.println(sentences[i]);
		    		 
		    		  
		    	  }else {
		    		  System.out.println(sentences[i]);
		    		 
		    		
		    	  }
		    	
		    	  
		      }
		      for(int i=0;i<sentences.length;i++) 
		      {
		    	  if(sentences[i].isBlank())
	            		continue;
		    	  myWriter.write(sentences[i]+".\n");
		      }
		      
		      
		      
		      
		      
		    //  System.out.println(text);
		      
		     
		      
		      String marginsText=stripper.getTextForRegion("margins");
		      marginsText=marginsText.replaceAll("د ة", "دة");
		      String regexMargins="\r\n\\d+";
		      Pattern pattern = Pattern.compile(regexMargins);
		      
		      // get a matcher object
		      Matcher matcher = pattern.matcher(marginsText); 
		      ArrayList<String> arrli= new ArrayList<String>(5); 
		      while(matcher.find()) {
		         //Prints the start index of the match.
		    	   arrli.add(matcher.group());
		         System.out.println("Match String start(): "+matcher.start());
		      }
		      for(int i=0;i<arrli.size();i++) {
		    	  String str=arrli.get(i);
		    	  String replacment=str.replaceAll("\r\n", " ");
		    	  marginsText=marginsText.replaceAll(str, replacment);
		      }
		      System.out.println(marginsText);
		      
		      
		      String[] margins=marginsText.split("\r\n");
		    
		      for(int i=0;i<margins.length;i++)
	            {
		    	  if(margins[i].isBlank())
	            		continue;
		    	  
	            	System.out.println(margins[i]);
	            	
	            	myWriter1.write(margins[i]+".");
	            }
		      
		      
		      myWriter1.close(); 
		      myWriter.close();
	            
	            
	            
		      }
		     
		      
		      
		      
		}
		catch(Exception exception) 
		{
			System.out.println("not found");
		}
		}
		
		
		
		
		 
		    }
		
		
	    
			
			
		      
		      
		      
		      
		      
		      
		      
		      
			//FileWriter txt=new FileWriter("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2853\\2853.txt");
			//FileWriter txt1=new FileWriter("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\2319-2382\\2367.txt");
			//String text2364=stripper.getText(doc2364);
			//String text2367=pdfStripper.getText(doc2367);
			
			
			
			
			 // string from question
		   
            
			//xml editor
			/*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			doc.getDocumentElement().normalize();
			NodeList list = doc.getElementsByTagName("docTitle");

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);	
				Element element = (Element) node;
				String id = element.getAttribute("eId");
				String TitleText=element.getTextContent();
				//	System.out.println(element.getTextContent());
				switch (i) {
				case 0:
					element.setTextContent(LawTitleInArabic);
					break;
				case 1:
					element.setTextContent(LawTitleInArabic);

					break;
				case 2:
					element.setTextContent(LawTitleInArabic);
					break;
				}
				//System.out.println(element.getTextContent());
			}


		*/
		

	//}
	
	protected void writeString1(String string, List<TextPosition> textPositions) throws IOException {
        for (TextPosition text : textPositions) {
            System.out.println(text.getUnicode()+ " [(X=" + text.getXDirAdj() + ",Y=" +
                    text.getYDirAdj() + ") height=" + text.getHeightDir() + " width=" +
                    text.getWidthDirAdj() + "]");
        }
    }
	
    }

