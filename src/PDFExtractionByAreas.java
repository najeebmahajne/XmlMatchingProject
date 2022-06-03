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
	PDDocument doc;
	String coverText;
	String bodyText;
	String marginsText;

	/*public PDFExtractionByAreas(String filepath) 
	{
		 try {
			this.doc=PDDocument.load(new File(filepath));
			Start(this.doc);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	private static String[] PDFFILESARRAY = new String[5];
	private static String[] BODYOUTPUTFILESARRAY = new String[5];
	private static String[] MARGINSOUTPUTFILESARRAY = new String[5];
	private static String[] COVERPAGEOUTPUTFILESARRAY = new String[5];
	private static String[] FOOTEROUTPUTFILESARRAY = new String[5];
	public static void main(String args[]) throws IOException {
		    // Instantiate the Factory
			//laws' pdf file 
			//File pdfFile = new File("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\28122812.pdf");
			//File pdfFile1 = new File("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\2319-2382\\2367.pdf");
		
		String[] documentsNumbers= {"2411"};//,"2804","2806","2813","2823"
		  for(int docNumber=0;docNumber<documentsNumbers.length;docNumber++) {
			  
			  String docNum=documentsNumbers[docNumber];
			  
		BODYOUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"+docNum+"\\"+docNum+"BODYTEXT.txt";
		PDFFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"+docNum+"\\"+docNum+".pdf";
		MARGINSOUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"+docNum+"\\"+docNum+"MARGINSTEXT.txt";
		COVERPAGEOUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"+docNum+"\\"+docNum+"COVERPAGETEXT.txt";
		FOOTEROUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"+docNum+"\\"+docNum+"FOOTERTEXT.txt";
		for(int fileNum=0;fileNum<1;fileNum++) {
			
		
		try {
			PDDocument doc = PDDocument.load(new File(PDFFILESARRAY[fileNum]));
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
		      
		      FileWriter myWriter2 = new FileWriter(new File(COVERPAGEOUTPUTFILESARRAY[fileNum]),true);
		      
		      PDPage coverPage=doc.getPage(0);
		      Rectangle coverRect = new Rectangle( 96, 146, 400, 560 );
		      stripper.addRegion( "cover", coverRect );
		      stripper.extractRegions( coverPage );
		      String coverText=stripper.getTextForRegion("cover");
		      String[] coverSentences=coverText.split("\r\n");
		      for(int i=0;i<coverSentences.length;i++) 
		      {
		    	  if(coverSentences[i].contains("57")&&coverSentences[i].contains("قانون")) {
		    		  int indx=coverSentences[i].indexOf("57");
		    		  if(indx!=-1&&indx+4<=coverSentences[i].length()) {
		    		  coverSentences[i]=coverSentences[i].substring(0, indx+4);
		    		  }else 
		    		  {
		    			 
		    			  
		    			  coverSentences[i]=coverSentences[i].substring(0, indx);
		    		  }
		    		  coverSentences[i]=coverSentences[i].replaceAll("\\d+\\s...", "");
		    		  
		    	  }else 
		    	  {
		    		  int indx1=coverSentences[i].indexOf("20");
		    		  if(indx1!=-1) {
	    			  coverSentences[i]=coverSentences[i].substring(0, indx1);
		    		  }
	    			  coverSentences[i]=coverSentences[i].replaceAll("\\d+\\.+", "");
		    		  
		    	  }
		      }
		      for(int i=0;i<coverSentences.length;i++) 
		      {
		    	  if(coverSentences[i].isBlank())
	            		continue;
		    	  myWriter2.write(coverSentences[i]+".\n");
		    	  //this.coverText+=coverSentences[i]+".\n";
		      }
		      myWriter2.close();
		      stripper.removeRegion("cover");
		      
		      for(int pageNum=1;pageNum<pagesNum;pageNum++) {
		      FileWriter myWriter = new FileWriter(new File(BODYOUTPUTFILESARRAY[fileNum]),true);
			  FileWriter myWriter1 = new FileWriter(new File(MARGINSOUTPUTFILESARRAY[fileNum]),true);
			  FileWriter myWriter3 = new FileWriter(new File(FOOTEROUTPUTFILESARRAY[fileNum]),true);
		    	  
		      Rectangle rect = new Rectangle( 96, 146, 360, 560 );
		      PDPage firstPage = doc.getPage(pageNum);
		      firstPage.getCOSObject();
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
		      
		     
		      
		      String[] bodySentences=text.split("[\\.|*|:|]");
		      String[] footerSentences=new String[bodySentences.length];
		      int footerIndex=0;
		      //.split(stripper.getParagraphStart()
		      for(int i=0;i<bodySentences.length;i++) 
		      {
		    	  if(bodySentences[i].contains("­")||bodySentences[i].contains("12016")||bodySentences[i].contains("12019")) {
		    		  bodySentences[i]=bodySentences[i].replaceAll("­", " ");
		    		  bodySentences[i]=bodySentences[i].replaceAll("12016", "2016");
		    		  bodySentences[i]=bodySentences[i].replaceAll("12019", "2019");
		    	  }
		    	  if(bodySentences[i].contains("قانون")) 
		    	  {
		    		  int index=bodySentences[i].indexOf("قانون");
		    		  if(index==0)
		    			  bodySentences[i]="";
		    		  
		    	  }
		    	  if(bodySentences[i].contains("أقرته")) {
		    		  if(i+2<=bodySentences.length-1&&bodySentences[i+2].contains("\r\n")) {
		    		  String[] str=bodySentences[i+2].split("\r\n");
		    		  bodySentences[i]+="'"+bodySentences[i+1]+str[0];
		    		  bodySentences[i+1]="";
		    		  bodySentences[i+2]="";}
		    		  if(bodySentences[i].contains("\r\n")) {
		    		  bodySentences[i]=bodySentences[i].replaceAll("\r\n", " ");
		    		  }
		    		  bodySentences[i]=bodySentences[i].replaceFirst("\\s+1\\s+", " ");
		    		  footerSentences[footerIndex]=bodySentences[i];
		    		  bodySentences[i]="";;
		    		  footerIndex++;
		    		  
		    	  }
		    	  if(bodySentences[i].contains("بنيامين")&&bodySentences[i].contains("رئيس الكنيست")) {
		    		  bodySentences[i]=bodySentences[i].replaceAll("تاري خ", "تاريخ");
		    		  bodySentences[i]=bodySentences[i].replaceAll("االنتخابات", "الانتخابات");
		    		  int firstIndx=bodySentences[i].indexOf("بنيامين");
		    		  int lastIndx=bodySentences[i].indexOf("رئيس الكنيست");
		    		  if(lastIndx>firstIndx&&lastIndx!=-1&&firstIndx!=-1)
		    			  bodySentences[i]=bodySentences[i].substring(0, firstIndx)+bodySentences[i].substring(lastIndx+12);
		    		  
		    		 
		    		  
		    	  }
		    	
		    	  
		      }
		      //BODY TEXT
		      for(int i=0;i<bodySentences.length;i++) 
		      {
		    	  if(bodySentences[i].isBlank())
	            		continue;
		    	  System.out.println(bodySentences[i]);
		    	  myWriter.write(bodySentences[i]+".\n");
		    	 
		      }
		      //FOOTER TEXT
		      for(int i=0;i<footerSentences.length;i++) 
		      {
		    	  if(footerSentences[i]==null)
	            		continue;
		    	  if(footerSentences[i].contains("كتاب")) 
		    	  {
		    		  int index=footerSentences[i].indexOf("كتاب");
		    		  if(index!=-1)
		    			  footerSentences[i]=footerSentences[i].substring(0, index);
		    		  
		    	  }
		    	  System.out.println(footerSentences[i]);
		    	  myWriter3.write(footerSentences[i]+".\n");
		    	 
		      }
		      
		      
		      
		      
		      
		    //  System.out.println(text);
		      
		     
		      //MARGIN TEXT
		      String marginsText=stripper.getTextForRegion("margins");
		      marginsText=marginsText.replaceAll("د ة", "دة");
		      marginsText=marginsText.replaceAll("د ة", "دة");
		      marginsText=marginsText.replaceAll("االنتخابا ت", "الانتخابات");
		      marginsText=marginsText.replaceAll("الكنيس ت", "الكنيست");
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
		      
		      
		      
		      String[] margins=marginsText.split("\r\n");
		    
		      for(int i=0;i<margins.length;i++)
	            {
		    	  if(margins[i].isBlank())
	            		continue;
		    	  
	            	System.out.println(margins[i]);
	            	
	            	myWriter1.write(margins[i]+".\n");
	            	// this.marginsText+=sentences[i]+".\n";
	            }
		      
		      myWriter3.close();
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

