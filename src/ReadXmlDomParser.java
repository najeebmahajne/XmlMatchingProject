import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import com.darkprograms.speech.translator.GoogleTranslate;
import com.simplileam.mavenproject.JaccardDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;





public class ReadXmlDomParser {

  private static final String FIRSTFILENAME = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\ספר החוקים XML Hebrew\\ספר_החוקים-2364\\booklet.xml";
  private static final String SECONDFILENAME = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\ספר החוקים XML Hebrew\\ספר_החוקים-2367\\booklet.xml";
   public Scanner myReader = new Scanner(System.in);//, ParserConfigurationException, SAXException
  public static void main(String[] args) throws FileNotFoundException {
	  String arabicTXTFilePath1="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\2319-2382\\2364.txt";
	  String arabicTXTFilePath2="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\2319-2382\\2367.txt";
	
	 
	try {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		DocumentBuilder db = factory.newDocumentBuilder();
		Document firstDoc = db.parse(new File(SECONDFILENAME));
		firstDoc.getDocumentElement().normalize();
		System.out.println("Root Element :" + firstDoc.getDocumentElement().getNodeName());
		System.out.println("------");
		Document secondDoc = db.parse(new File(SECONDFILENAME));
		secondDoc.getDocumentElement().normalize();
		TextProccessing ArabicText=new TextProccessing(arabicTXTFilePath2,'A');
		 ArabicText.Normalization();
		// ArabicText.PrintSentences();
		 Node componentInfoTag=firstDoc.getElementsByTagName("componentInfo").item(0);
		 NodeList list = componentInfoTag.getChildNodes();
		 TrainedTokenizer tok=new TrainedTokenizer();
		 for(int current=0;current<list.getLength();current++)
		 {
			 Node componentDataTag = list.item(current);
			 if (componentDataTag.hasAttributes()) {
                 //Attr attr = (Attr) componentDataTag.getAttributes().getNamedItem("showAs");
				 NamedNodeMap attr = componentDataTag.getAttributes();
				 Node node = attr.getNamedItem("showAs");
			      
                 if (node != null) {
                     String attributeTXT= node.getTextContent();;                      
                     System.out.println("attribute: " + attributeTXT); 
                     StringProccessing str=new StringProccessing(attributeTXT);
                     
                      str.tokensTranslation();
                      for(int i=0;i<str.translatedTokens.length;i++) {
                      String token=str.translatedTokens[i]  ;  
                      token=token.replaceAll("ال", "");
                      boolean found=false;
                      	for(int j=0;j<ArabicText.sentences.length;j++) 
                      	{
                      		boolean result = ArabicText.sentences[j].contains(token);
                      	    if(result) {
                      	    	int index = ArabicText.sentences[j].indexOf(token);
                      	    	float JaccardThreshold=0.85f  ;
                      	    	if(i>0) {
                      	    		String token1=str.translatedTokens[i-1]  ;
                      	    		int index1 = ArabicText.sentences[j].indexOf(token1);
                      	    		if(index1!=-1)
                      	    			index = ArabicText.sentences[j].indexOf(token1);
                      	    		
                      	    		}
                      	    	
                      	    	int endIndex=index+str.translatedTokens.length;
                      	    	String[] Arabictokens=tok.tokenize(ArabicText.sentences[j]);
                      	    	String[] newArabictokens=Arrays.copyOfRange(Arabictokens, index, endIndex);
                      	    	String sentence = String.join(" ", newArabictokens);
                      	    	sentence = sentence.replaceAll("null", "");
                      	    	str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("،", "");
                      	    	str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("في", "");
                      	    	str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("'", "");
                      	    	String sentenceWithoutNumbers=removeAllDigit(sentence);
                      	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("-", "");
                      	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("  ", "");
                      	    	String translatedSentenceWithoutNumbers=removeAllDigit(str.TranslatedSentence1);
                      	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("  ", "");
                      	    	//sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll(".", "");
                      	    	int lavveDiff=calculate(sentenceWithoutNumbers,translatedSentenceWithoutNumbers);
                      	    	int maxLength=Math.max(sentenceWithoutNumbers.length(), translatedSentenceWithoutNumbers.length());
                      	    	System.out.println("Sentence Length: "+maxLength);
                      	    	System.out.println("Diffrence : "+lavveDiff);
                      	    	
                      	    	float LavveTheshold=maxLength/2;
                      	    	float ratio=(float)lavveDiff/maxLength;
                      	    	
                      	   
                      	    	//System.out.println("Ratio : "+(lavveDiff/maxLength));
                      	    	
                      	  		//str.TranslatedSentence1.replaceAll(")", "");
                      	    	
                      	  		System.out.println("Arabic sentence in PDF: "+sentenceWithoutNumbers);
                      	  		System.out.println("Arabic translated sentence from XML: "+translatedSentenceWithoutNumbers);
                      	  		

                      	  		
                      	  		System.out.println("J2(ArabicPDF, ArabicXML) = " + JaccardDistance.Jaccard2(sentenceWithoutNumbers, translatedSentenceWithoutNumbers));
                      	  		if(ratio>0.65f&&JaccardDistance.Jaccard2(sentenceWithoutNumbers, translatedSentenceWithoutNumbers)>JaccardThreshold) {
                      	  			System.out.println("Changing Failed");
                      	  			break;}
                      	  		node.setTextContent(ArabicText.sentences[j]);
                      	  		System.out.println("Changed Succusfully With : "+ArabicText.sentences[j]);
                      	  		ArabicText.sentences[j]="";
                      	  		System.out.println();
                      	  		List<String> stringList = new ArrayList<String>();
                      	  		Collections.addAll(stringList, sentence, str.TranslatedSentence1);
                      	  	JaccardDistance.JaccardWeight(stringList);
                      	  		System.out.println(JaccardDistance.weightMap);
                      	  		
                      	  	JaccardDistance.weightMap=new HashMap<String, Double>();
                      	  		
                      	  		
                      	  	
                      	    	found=true;
                      	    	break;
                      	    }
                      	    else {
                      	     continue;
                      	    }
                      	    
                      	}
                      	if(found)
                      		break;
                      
                      }
                     
                 }
             }
			 /*for (int i=0; i<attributes.getLength(); i++) {
		           
		        
			 }*/
		 }
		 Node secondcomponentInfoTag=firstDoc.getElementsByTagName("componentInfo").item(2);
		 NodeList secondList = secondcomponentInfoTag.getChildNodes();
		 for(int current=0;current<secondList.getLength();current++)
		 {
			 Node componentDataTag = secondList.item(current);
			 if (componentDataTag.hasAttributes()) {
				 NamedNodeMap attr = componentDataTag.getAttributes();
				 Node node = attr.getNamedItem("showAs");
			      
                 if (node != null) {
                     String attributeTXT= node.getTextContent();;                      
                     System.out.println("attribute: " + attributeTXT);
                     StringProccessing str=new StringProccessing(attributeTXT);
                     
                     str.tokensTranslation();
                     for(int i=0;i<str.translatedTokens.length;i++) {
                     String token=str.translatedTokens[i]  ;  
                     token=token.replaceAll("ال", "");
                     boolean found=false;
                     	for(int j=0;j<ArabicText.sentences.length;j++) 
                     	{
                     		boolean result = ArabicText.sentences[j].contains(token);
                     	    if(result) {
                     	    	int index = ArabicText.sentences[j].indexOf(token);
                     	    	float JaccardThreshold=0.85f  ;
                     	    	if(i>0) {
                     	    		String token1=str.translatedTokens[i-1]  ;
                     	    		int index1 = ArabicText.sentences[j].indexOf(token1);
                     	    		if(index1!=-1)
                     	    			index = ArabicText.sentences[j].indexOf(token1);
                     	    		
                     	    		}
                     	    	
                     	    	int endIndex=index+str.translatedTokens.length;
                     	    	String[] Arabictokens=tok.tokenize(ArabicText.sentences[j]);
                     	    	String[] newArabictokens=Arrays.copyOfRange(Arabictokens, index, endIndex);
                     	    	String sentence = String.join(" ", newArabictokens);
                     	    	sentence = sentence.replaceAll("null", "");
                     	    	str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("،", "");
                     	    	str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("في", "");
                     	    	str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("'", "");
                     	    	String sentenceWithoutNumbers=removeAllDigit(sentence);
                     	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("-", "");
                     	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("  ", "");
                     	    	String translatedSentenceWithoutNumbers=removeAllDigit(str.TranslatedSentence1);
                     	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("  ", "");
                     	    	//sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll(".", "");
                     	    	int lavveDiff=calculate(sentenceWithoutNumbers,translatedSentenceWithoutNumbers);
                     	    	int maxLength=Math.max(sentenceWithoutNumbers.length(), translatedSentenceWithoutNumbers.length());
                     	    	System.out.println("Sentence Length: "+maxLength);
                     	    	System.out.println("Diffrence : "+lavveDiff);
                     	    	
                     	    	float LavveTheshold=maxLength/2;
                     	    	float ratio=(float)lavveDiff/maxLength;
                     	    	
                     	   
                     	    	//System.out.println("Ratio : "+(lavveDiff/maxLength));
                     	    	
                     	  		//str.TranslatedSentence1.replaceAll(")", "");
                     	    	
                     	  		System.out.println("Arabic sentence in PDF: "+sentenceWithoutNumbers);
                     	  		System.out.println("Arabic translated sentence from XML: "+translatedSentenceWithoutNumbers);
                     	  		

                     	  		
                     	  		System.out.println("J2(ArabicPDF, ArabicXML) = " + JaccardDistance.Jaccard2(sentenceWithoutNumbers, translatedSentenceWithoutNumbers));
                     	  		if(ratio>0.65f&&JaccardDistance.Jaccard2(sentenceWithoutNumbers, translatedSentenceWithoutNumbers)>JaccardThreshold) {
                     	  			System.out.println("Changing Failed");
                     	  			break;}
                     	  		node.setTextContent(ArabicText.sentences[j]);
                     	  		System.out.println("Changed Succusfully With : "+ArabicText.sentences[j]);
                     	  		ArabicText.sentences[j]="";
                     	  		System.out.println();
                     	  		List<String> stringList = new ArrayList<String>();
                     	  		Collections.addAll(stringList, sentence, str.TranslatedSentence1);
                     	  	JaccardDistance.JaccardWeight(stringList);
                     	  		System.out.println(JaccardDistance.weightMap);
                     	  		
                     	  	JaccardDistance.weightMap=new HashMap<String, Double>();
                     	  		
                     	  		
                     	  	
                     	    	found=true;
                     	    	break;
                     	    }
                     	    else {
                     	     continue;
                     	    }
                     	    
                     	}
                     	if(found)
                     		break;
                     
                     }
                    
                }
            }
                 }
			 
		 
		 
		 } catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 /* TextProccessing ArabicText=new TextProccessing(ArabicFilePath,'A');
	  TextProccessing HebrewText=new TextProccessing(HebrewFilePath,'H');
	  ArabicText.Normalization();
	  HebrewText.Normalization();
	  ArabicText.PrintSentences();
	  HebrewText.PrintSentences();*/
	/*HashMap<String, String> defenitions = new HashMap<String, String>();
      defenitions.put("הגדרות", "تعاريف");
      defenitions.put("הטבות", "االنتفاعات");
      defenitions.put("השבת תמורה", "استعادة العوض");
      defenitions.put("חבילת תיור", "رزمة سياحية");
      defenitions.put("חניית ביניים","توقف لالستراحة");
      defenitions.put("טיסה","رحلة طيران");
      defenitions.put("טיסה שבוטלה","رحلة طيران ملغاة");
      defenitions.put("יעד סופי","المحطة النهائية");
      defenitions.put("כרטיס טיסה","تذكرة طيران");
      defenitions.put("כרטיס טיסה חלופי","تذكرة طيران بديلة");
      defenitions.put("מארגן","المنظم");
      defenitions.put("מפעיל טיסה","المشغل الجوي");
      defenitions.put("נותן שירותי סוכנות נסיעות","مقدم خدمات وكالة السفر");
      defenitions.put("פיצוי כספי","التعويض المالي");
      defenitions.put("עילה מזכה","علة االستحقاق");
      defenitions.put("רשות שדות התעופה","سلطة المطارات");
      defenitions.put("שירותי סוכנות נסיעות","خدمات وكالة السفر");
      defenitions.put("שירותי סיוע","خدمات اضافية");
      defenitions.put("השר","الوزير");
      defenitions.put("כמשמעותה","حسب مدلولها");
	  
	  
	  
	  File inputpath = new File("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\Hebrew.txt");
		 Scanner myReader = new Scanner(inputpath);
		 String HebrewText =myReader.next();
		 
		 
		 while (myReader.hasNextLine())
	      {
			
			 HebrewText+=" ";
			 HebrewText += myReader.next();	 
			  
	      }
	      myReader.close();
	      String[] HebrewSentences=HebrewText.split(";", 0);
	      String newHebrew="";
	      for (int i=0;i<HebrewSentences.length;i++) {
	    	  
				//System.out.println(i+": "+sentences[i]);
				newHebrew+=HebrewSentences[i];
			}
	      
	      TrainedTokenizer tok=new TrainedTokenizer();*/


	      
	      
	     //Arabic Proccessing
	   /*   File inputpath1 = new File("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\ArabicLaw.txt");
			 Scanner myReader1 = new Scanner(inputpath1);
			 String arabicText =myReader1.next();
			 while (myReader1.hasNextLine())
		      {
		    	  arabicText += myReader1.next();	 
		    	  arabicText+=" ";
		      }
		      myReader1.close();
		      AraNormalizer arn=new AraNormalizer();
			DiacriticsRemover dr=new DiacriticsRemover();
			PunctuationsRemover pr=new PunctuationsRemover();
				
			String ArabicNormalizedText=arn.normalize(arabicText);
			ArabicNormalizedText = dr.removeDiacritics(ArabicNormalizedText);
			ArabicNormalizedText = pr.removePunctuations(ArabicNormalizedText);
			
			String HebrewNormalizedText=dr.removeDiacritics(newHebrew);
			HebrewNormalizedText = pr.removePunctuations(HebrewNormalizedText);
			HebrewNormalizedText=HebrewNormalizedText.replaceAll("—", "");
			HebrewNormalizedText=HebrewNormalizedText.replaceAll("1", "");
			
		    String[] Arabictokens=tok.tokenize(ArabicNormalizedText);
		    String[] Hebrewtokens=tok.tokenize(HebrewNormalizedText);
		    
		    
		    SentenceDetector sd=new SentenceDetector();
		    String[] ArabicSentences=sd.detectSentences(arabicText);*/
		   // String[] ArabicSentences=arabicText.split(";", 0);
		  ///* for (int i=0;i<ArabicSentences.length;i++) {
		    	 // if(i<=128) {
		    		 // System.out.println(i+": "+ArabicSentences[i]);
		    	 // System.out.println(i+": "+HebrewSentences[i]);}
		    	 // else {
		    		//  System.out.println(i+": "+ArabicSentences[i]);
		    	//  }
		    		  
		    	  
				//}*/
		      
	     // /*for (int i=0;i<200;i++) {
	    	  
				//System.out.println(i+": "+Hebrewtokens[i]);
				//System.out.println(i+": "+Arabictokens[i]);
				
		//	}*/
	    /*  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	     dbf.setIgnoringElementContentWhitespace(true);
	      dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
	      DocumentBuilder db = dbf.newDocumentBuilder();
	      Document doc = db.parse(new File(FILENAME));
	      // optional, but recommended
	      // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	      doc.getDocumentElement().normalize();
	      System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	      System.out.println("------");

	          // get <staff>
	          NodeList list = doc.getElementsByTagName("point");
	          for (int temp = 0; temp < 20; temp++) {// TEMP<LIST.LENGTH();

	              Node node = list.item(temp);//point node
	             
	              if (node.getNodeType() == Node.ELEMENT_NODE) {
	            	  
	                  Element defTag = (Element) node.getFirstChild().getNextSibling().getFirstChild().getNextSibling().getFirstChild().getNextSibling();
	                  Element termTag = (Element) defTag.getFirstChild().getNextSibling();
	                  String termTagText =   termTag.getTextContent();
	                  termTagText=termTagText.replaceAll("\n", "");
	                  termTagText=termTagText.replaceAll("\t", "");
	                 // /*if(termTag.hasAttributes())
	                 // {
	                 // String termTagText =   termTag.getTextContent();
	                // String translatedTermTagText=GoogleTranslate.translate("ar", termTagText);
	                 // }
	                  String defTagText =   defTag.getLastChild().getTextContent();
	                  // build hash map instead of this 
	                  defTagText=defTagText.replaceAll("\n", "");
	                  defTagText=defTagText.replaceAll("\t", "");
	                  if(defTagText.length()<=2) {
	                	  defTagText =   termTag.getNextSibling().getTextContent();
	                	  defTagText=defTagText.replaceAll("\n", "");
		                  defTagText=defTagText.replaceAll("\t", "");
	                  }
	                  defTagText =defTagText.replaceAll(";","");
	                  defTagText =defTagText.replaceAll("—","");
	                  defTagText =defTagText.replaceAll(":","");
	                  defTagText =defTagText.replace('"', ' '); 
	                  
	                  try {
	            		  //try finding a match by tokenizing each word alone and then translate
	                	  String[] textTagTokens=tok.tokenize(defTagText);
	                	  String[] translatedTextTagTokens=new String[textTagTokens.length];
	                	  String TranslatedSentence1="";
	                	  
	                	  for(int i=0;i<textTagTokens.length;i++) {
	                		 translatedTextTagTokens[i]=GoogleTranslate.translate("ar", textTagTokens[i]);
	                		 TranslatedSentence1+=translatedTextTagTokens[i];
	                		 TranslatedSentence1+=" ";
	                		 
	                	  }
	                	  System.out.println(TranslatedSentence1);
	                	  
	                	  int firstIndex;
	                	  for(int i=0 ;i<ArabicSentences.length;i++)   
	                	  {
	                		  boolean changed=false;
	                		 
	                		  for(int j=translatedTextTagTokens.length-1 ;j>=0;j--) {
	                			  firstIndex=ArabicSentences[i].indexOf(translatedTextTagTokens[j]);
	                		  if(firstIndex!=-1)//-1 if no match found - positive number if found
	                		  {
	                			  String str=ArabicSentences[i].substring(firstIndex);
	                			  //normalization of the substring str 
	                			  str = dr.removeDiacritics(str);
	                			  str = pr.removePunctuations(str);
	                			  String[] tokenStr=tok.tokenize(str);
	                			  int sentenceSizeDiff=translatedTextTagTokens.length-tokenStr.length;
	                			 if( translatedTextTagTokens[j].contains("ال"))
	                				 translatedTextTagTokens[j]= translatedTextTagTokens[j].replace("ال", "");
	                			  int lastIndex=str.indexOf(translatedTextTagTokens[j]);
	                			  if(lastIndex!=-1)//last index match found
	                			  {
	                				  if(sentenceSizeDiff>2||sentenceSizeDiff<-2) {//if diff less than 2 take all the sentence
	                					  str=ArabicSentences[i].substring(firstIndex,lastIndex);
	                					  }
	                				  else
	                				  {
	                					  str=ArabicSentences[i].substring(firstIndex);
	                					  }
	                				  defTag.getLastChild().setTextContent(str);
	                				  System.out.println(node.getNodeName()+"Number - "+(temp+1));
	                				  System.out.println(defTag.getNodeName()+" ID - "+defTag.getAttribute("eId"));
	                				  System.out.println(termTag.getNodeName()+" ID - "+termTag.getAttribute("eId"));
	                				  System.out.println("Hebrew :"+defTagText);
	                				  System.out.println("Arabic :"+defTag.getLastChild().getTextContent());
	                				  changed=true;
	                				  break;
	                				  }
	                			  else
	                			  {
	                				  int levenshteinDistance=calculate(TranslatedSentence1,ArabicSentences[i]);
	                				  int maxLength=Math.max(TranslatedSentence1.length(),ArabicSentences[i].length());
	                				  if(levenshteinDistance<=maxLength/2) 
	                				  {
	                					  str=ArabicSentences[i].substring(firstIndex,lastIndex);
	                					  defTag.getLastChild().setTextContent(str);
		                				  System.out.println(node.getNodeName()+"Number - "+temp);
		                				  System.out.println(defTag.getNodeName());
		                				  System.out.println(termTag.getNodeName());
		                				  System.out.println("Hebrew :"+defTagText);
		                				  System.out.println("Arabic :"+defTag.getLastChild().getTextContent());
	                				  }
	                			}
	                			   
	                		  }
	                		  }
	                		  if(changed)
	                			  break;
	                		  
	                	  }
	                	 
	          		} catch (IOException e) {
	          			e.printStackTrace();
	          		}
	                  
	                  
	                   
	              	}*/
			
		} 
	  
	 
	  
	  /*
         for (int temp = 0; temp < 20; temp++) {// TEMP<LIST.LENGTH();

              Node node = list.item(temp);//point node
             
              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element defTag = (Element) node.getFirstChild().getFirstChild().getFirstChild();
                  Element termTag = (Element) defTag.getFirstChild().getNextSibling();
                  String textTag =   defTag.getLastChild().getTextContent();
                   defTag.getLastChild().setTextContent(sentences[temp]);
                   String STR = defTag.getLastChild().getTextContent();
                  System.out.println(node.getNodeName()); 
                  System.out.println(defTag.getNodeName());
                  System.out.println(termTag.getNodeName());
                  System.out.println("Hebrew :"+textTag);
                  System.out.println("Arabic :"+STR);
                  
                  
                  
                  // get staff's attribute
                  String eId = defTag.getAttribute("eId");
                 if(temp==6||temp==7) {
                	 String definition = defTag.getElementsByTagName("def").item(0).getTextContent();
                	 definition=definition.replaceAll("\n", "");
                     definition=definition.replaceAll("\t", "");
                     defTag.getElementsByTagName("def").item(0).setTextContent(sentences[temp]);
                     definition=sentences[temp];
                	 System.out.println("Current Element :" + node.getNodeName());
                     System.out.println("eId : " + eId);
                     System.out.println("Definition : " + definition);
                     System.out.println();
                     
     
                 }else {
                  // get textv
                	
                  String definition = defTag.getElementsByTagName("def").item(0).getTextContent();
                  String term = defTag.getElementsByTagName("term").item(0).getTextContent();
                  term=term.replaceAll("\n", "");
                  term=term.replaceAll("\t", "");
                  definition=definition.replaceAll("\n", "");
                  definition=definition.replaceAll("\t", "");
                  defTag.getElementsByTagName("def").item(0).getLastChild().setTextContent(sentences[temp]);
                  definition=sentences[temp];
                  
                  String termContent=defenitions.get(term);
                  term=termContent;
                  defTag.getElementsByTagName("term").item(0).setTextContent(termContent);               
                 
                  
                  
                  System.out.println("Current Element :" + node.getNodeName());
                  System.out.println("eId : " + eId);
                  System.out.println("Term : " + term);  
                  System.out.println("Definition : " + definition);
                  System.out.println();
                     }                  
              }
          }
          

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }*/

  
  static int calculate(String x, String y) {
	    int[][] dp = new int[x.length() + 1][y.length() + 1];

	    for (int i = 0; i <= x.length(); i++) {
	        for (int j = 0; j <= y.length(); j++) {
	            if (i == 0) {
	                dp[i][j] = j;
	            }
	            else if (j == 0) {
	                dp[i][j] = i;
	            }
	            else {
	                dp[i][j] = min(dp[i - 1][j - 1] 
	                 + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
	                  dp[i - 1][j] + 1, 
	                  dp[i][j - 1] + 1);
	            }
	        }
	    }

	    return dp[x.length()][y.length()];
	}

	public static int costOfSubstitution(char a, char b) {
      return a == b ? 0 : 1;
  }
	 public static int min(int... numbers) {
	        return Arrays.stream(numbers)
	          .min().orElse(Integer.MAX_VALUE);
	    }
	 //removing numbers
	 public static String removeAllDigit(String str)
	    {
	        // Converting the given string
	        // into a character array
	        char[] charArray = str.toCharArray();
	        String result = "";
	  
	        // Traverse the character array
	        for (int i = 0; i < charArray.length; i++) {
	  
	            // Check if the specified character is not digit
	            // then add this character into result variable
	            if (!Character.isDigit(charArray[i])) {
	                result = result + charArray[i];
	            }
	        }
	  
	        // Return result
	        return result;
	    }
  

}
