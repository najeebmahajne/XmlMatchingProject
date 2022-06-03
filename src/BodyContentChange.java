import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.darkprograms.speech.translator.GoogleTranslate;
import com.simplileam.mavenproject.JaccardDistance;

public class BodyContentChange {
	
	String[] TXTFILESARRAY = new String[5];
	 String[] NEWXMLFILESARRAY = new String[5];
	
	 
	 Document PDFPage;
	 File outputFile;
	
	
	public void bodyChanger(Document PDFFile) {
		this.PDFPage=PDFFile;
		this.outputFile=outputFile;
		
		  String[] TXTFILESARRAY = new String[5];
		 String[] NEWXMLFILESARRAY = new String[5];
		Dictionary defenitions=new Dictionary();
		
		
		//NEWXMLFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2754\\2754Arabic.xml";
		//NEWXMLFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2756\\2756Arabic.xml";
		NEWXMLFILESARRAY[2]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2762\\2762Arabic.xml";
		//NEWXMLFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2763\\2763Arabic.xml";
		//NEWXMLFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2765\\2765Arabic.xml";
		
		//TXTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2754\\2754BODYTEXT.txt";
		//TXTFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2756\\2756BODYTEXT.txt";
		TXTFILESARRAY[2]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2762\\2762BODYTEXT.txt";
		//TXTFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2763\\2763BODYTEXT.txt";
		//TXTFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2765\\2765BODYTEXT.txt";
		for(int fileNumber=2;fileNumber<3;fileNumber++) {
		try {
			
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder db = factory.newDocumentBuilder();
			Document firstDoc = db.parse(new File(NEWXMLFILESARRAY[fileNumber]));
			TransformerFactory tFactory =TransformerFactory.newInstance();
			Transformer transformer =tFactory.newTransformer();
			DOMSource source = new DOMSource(firstDoc);
			File myObj = new File(NEWXMLFILESARRAY[fileNumber]);  
		    StreamResult sresult = new StreamResult(myObj);
		    //transformer.transform(source, sresult);
			
			
			firstDoc.getDocumentElement().normalize();
			System.out.println("Root Element :" + firstDoc.getDocumentElement().getNodeName());
			System.out.println("------");
			PDFTextProccessing ArabicText=new PDFTextProccessing(TXTFILESARRAY[fileNumber],'A');
			 ArabicText.Normalization();
			/* SentenceDetector sd=new SentenceDetector();
			 String[] sentences=ArabicText.text.split("المادة");
			 int index=sentences[sentences.length-1].indexOf("بنيامين");*/
			 String x="المادة";
			 final String regex = x+"[\\s]*"+"[\\d]*"+"[\\s]*"+"[–]+";
			 //int index=ArabicText.text.indexOf(".", 0);
			 final Pattern pattern = Pattern.compile(regex);
			 final Matcher matcher = pattern.matcher(ArabicText.text);
			 
			 while (matcher.find()) {
				 
				    System.out.println( matcher.group() );
				    System.out.println(matcher.start()+" and ending at index "+matcher.end());   
				    
				    
				}
			 //sentences[sentences.length-1]=sentences[sentences.length-1].substring(0, index);
			/* String[] bodySentences=new String[sentences.length-1];
			 for(int i=1;i<sentences.length;i++)
			 {
				 bodySentences[i-1]=sentences[i];
				 if(sentences[i].contains("."))
					 bodySentences[i-1]=sentences[i].substring(0, sentences[i].indexOf("."));
			 }*/
			/* ArabicText.sentences=ArabicText.text.split("[\\.–:\\u002A]");
			 NodeList bodyPTags =firstDoc.getElementsByTagName("p");
	        
	         int counter=0;
	         for(int i=1;i<bodyPTags.getLength();i++) 
	         {
	        	 
	        	 Node PNode=bodyPTags.item(i);
	        	 //Node previousNode=PNode.getPreviousSibling().getPreviousSibling();
	        	 if(PNode.hasAttributes()) 
	        	 {
	        		 System.out.println("******************* - Skipped");
	        		 continue;
	        	 }
	        	 
	        	 //NodeList PNodeList=PNode.getChildNodes();
	        	 //if(PNodeList.getLength()>=3&&i>2) {
	        		 //System.out.println("**********************");
	        		// continue;
	        		// }
	        	 String text=PNode.getTextContent();
	        	 //if(text.contains("{{{footNoteRef|1}}}"))
	        		// text.replaceAll("{{{footNoteRef|1}}}", "");
	        	 if(text.contains("\n"))
	        		 continue;
	        	 System.out.println("*<P> BODY TAG TEXT*");
	        	translationAlgorithm1(text,ArabicText,PNode);
	             counter++;
	             
	             transformer.transform(source, sresult);
	         }*/
	         
			System.out.println("Count");
			
			System.out.println();
		
		}
		 catch (IOException | SAXException | ParserConfigurationException | TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
		
	}
	
	
	
	
	
	
	
	 public static <K extends Comparable<K>, V> Map.Entry<K, V>getMaxEntryInMapBasedOnKey(Map<K, V> map)
	    {
	        // To store the result
	        Map.Entry<K, V> entryWithMaxKey = null;
	  
	        // Iterate in the map to find the required entry
	        for (Map.Entry<K, V> currentEntry : map.entrySet()) {
	  
	            if (
	  
	                // If this is the first entry,
	                // set the result as this
	                entryWithMaxKey == null
	  
	                // If this entry's key is more than the max key
	                // Set this entry as the max
	                || currentEntry.getKey().compareTo(entryWithMaxKey.getKey())> 0) {
	  
	                entryWithMaxKey = currentEntry;
	            }
	        }
	  
	        // Return the entry with highest key
	        return entryWithMaxKey;
	    }
	  
	    // Print the map
	    public static void print(HashMap<String, String> map)
	    {
	  
	        System.out.print("Map: ");
	  
	        // If map does not contain any value
	        if (map.isEmpty()) {
	            System.out.println("[]");
	        }
	  
	        else {
	            System.out.println(map);
	        }
	    }
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
	    
	    public static PDFTextProccessing translationAlgorithm1(String attributeTXT,PDFTextProccessing ArabicText,Node node) throws FileNotFoundException, IOException {
			 TrainedTokenizer tok=new TrainedTokenizer();
			 Dictionary defenitions=new Dictionary();
			 XMLTagTextProccessing str1=new XMLTagTextProccessing(attributeTXT);
			 str1.sentencePrint();
			 str1.tokensTranslation();
			 int counter=0;
			// str1.str
			 
		       
	         for(int i=0;i<str1.translatedTokens.length;i++) {
	        	 
	       	  String token = null;
	   
	       	token=str1.translatedTokens[i];
	         boolean found=false;
	         	for(int j=0;j<ArabicText.sentences.length;j++) 
	         	{
	         		if(token.toCharArray().length<=1)
	         			break;
	         		boolean result = ArabicText.sentences[j].contains(token);
	         	    if(result) {
	         	    	//if(ArabicText.sentences[j].contains("المادة")&&token=="قانون")
	         	    		//break;
	         	    	/*if(ArabicText.sentences[j].length()/3<=(str1.tokens.length)||ArabicText.sentences[j].length()>=(str1.tokens.length*3)) {
	         	    		//System.out.println("* HUGE LENGTH DIFFRENCE BETWEEN SENTENCES - KEEP GOING *");
	         	    		//break;
	         	    	}*/
	         	    	int index = ArabicText.sentences[j].indexOf(token);
	         	    	float JaccardThreshold=0.85f  ;
	         	    	if(i>0) {
	         	    		String token1=str1.translatedTokens[i-1]  ;
	         	    		int index1 = ArabicText.sentences[j].indexOf(token1);
	         	    		if(index1!=-1)
	         	    			index = ArabicText.sentences[j].indexOf(token1);
	         	    		}
	         	    	
	         	    	int endIndex=index+str1.translatedTokens.length;
	         	    	String text=ArabicText.sentences[j];
	         	    	text=text.replaceAll("-", " ");		
	         	    	String[] Arabictokens=tok.tokenize(text);
	         	    	System.out.print("Arab PDF Sentence : ");
	         	    	
	         	    	String[] newArabictokens=Arrays.copyOfRange(Arabictokens, 0, endIndex);
	         	    	String sentence = String.join(" ", newArabictokens);
	         	    	sentence = sentence.replaceAll("null", "");
	         	    	sentence = sentence.replaceAll("–", "");
	         	    	sentence = sentence.replaceAll(":", "");
	         	    	sentence = sentence.replaceAll("\\.", "");
	         	    	System.out.println(sentence);
	         	    	str1.TranslatedSentence1=str1.TranslatedSentence1.replaceAll("،", "");
	         	    	//str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("في", "");
	         	    	str1.TranslatedSentence1=str1.TranslatedSentence1.replaceAll("'", "");
	         	    	str1.TranslatedSentence1=str1.TranslatedSentence1.replaceAll("התשע״ב", "");
	         	    	String sentenceWithoutNumbers=removeAllDigit(sentence);
	         	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("-", "");
	         	    	//sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("-", "");
	         	    	String translatedSentenceWithoutNumbers=removeAllDigit(str1.TranslatedSentence1);
	         	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("اا", "ا");
	         	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("بك", "ك");
	         	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("لل", "ل");
	         	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("عات", "ع");
	         	    	sentence=sentence.replaceAll("اا", "ا");
	         	    	sentence=sentence.replaceAll("بك", "ك");
	         	    	sentence=sentence.replaceAll("لل", "ل");
	         	    	sentence=sentence.replaceAll("عات", "ع");
	         	    	//translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("(", "");
	         	    	//translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll(")", "");translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("-", "");
	         	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("الكف", "كف");
	         	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("إ", "ا");
	         	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("S", "");
	         	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("T", "");
	         	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("\\.", "");
	         	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("\\.", " ");
	         	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("   ", " ");
	         	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll(";", "");
	         	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("\\\\", "");
	         	    	
	         	    	int lavveDiff=calculate(sentenceWithoutNumbers,translatedSentenceWithoutNumbers);
	         	    	int maxLength=Math.max(sentenceWithoutNumbers.length(), translatedSentenceWithoutNumbers.length());
	         	    	
	         	    	String translatedSentenceWithNumbers=str1.TranslatedSentence1;
	         	    	translatedSentenceWithNumbers=translatedSentenceWithNumbers.replaceAll("الكف", "كف");
	         	    	translatedSentenceWithNumbers=translatedSentenceWithNumbers.replaceAll("إ", "ا");
	         	    	
	         	    	//translatedSentenceWithNumbers=translatedSentenceWithNumbers.replaceAll("\\", " ");
	         	    	
	         	    	
	         	    	int lavveDiff1=calculate(sentence,translatedSentenceWithNumbers);
	         	    	int maxLength1=Math.max(sentence.length(), translatedSentenceWithNumbers.length());
	         	    	
	         	    	
	         	    	//without numbers ratio 
	         	    	float ratio=(float)lavveDiff/maxLength;
	         	    	
	         	    	//with numbers ratio 
	         	    	float ratio1=(float)lavveDiff1/maxLength1;
	         	    	
	         	    	//between with numbers and without numbers
	         	    	int lavveDiff2=calculate(sentence,translatedSentenceWithoutNumbers);
	         	    	int maxLength2=Math.max(sentence.length(), translatedSentenceWithoutNumbers.length());
	         	    	float ratio2=(float)lavveDiff2/maxLength2;
	         	    	
	         	    	double jaccard=JaccardDistance.Jaccard2(sentenceWithoutNumbers, translatedSentenceWithoutNumbers);
	         	    	double jaccard1=JaccardDistance.Jaccard2(sentence, translatedSentenceWithNumbers);
	         	    	double jaccard2=JaccardDistance.Jaccard2(sentence, translatedSentenceWithoutNumbers);
	         	    	double jaccardMin=Math.min(jaccard, jaccard1);
	         	    	jaccardMin=Math.min(jaccardMin, jaccard2);
	         	    	
	         	    	
	         	    	
	         	    	float betterRatio1=Math.min(ratio, ratio1);
	         	    	float betterRatio=Math.min(betterRatio1,ratio2);
	         	  		//str.TranslatedSentence1.replaceAll(")", "");System.out.println("Arabic sentence in PDF: "+sentenceWithoutNumbers);
	         	    	if(betterRatio==ratio||betterRatio==ratio2) {
	         	  		System.out.println("Arabic translated sentence from XML: "+translatedSentenceWithoutNumbers);
	         	  		
	         	    	}
	         	    	else
	         	    	{
	         	    		System.out.println("Arabic translated sentence from XML: "+translatedSentenceWithNumbers);
	             	  		
	         	    	}
	         	    	
	         	    	
	         	    	//System.out.println("**WITHOUT NUMBERS**");
	         	    	System.out.println("Sentence Length: "+maxLength);
	         	    	System.out.println("LavenRatio : "+betterRatio);
	         	    	System.out.println("JaccardRatio : "+jaccardMin);
	         	    	/*
	         	    	System.out.println("-----------------------");
	         	    	System.out.println("**WITH NUMBERS**");
	         	    	System.out.println("Sentence Length: "+maxLength1);
	         	    	System.out.println("Diffrence : "+lavveDiff1);
	         	    	System.out.println("Rati : "+ratio1);
	         	    	System.out.println("JaccardRatio : "+jaccard1);
	         	    	System.out.println("-----------------------");
	         	    	System.out.println("**ONE WITH NUMBERS AND THE SECOND WITHOUT**");
	         	    	System.out.println("Sentence Length: "+maxLength2);
	         	    	System.out.println("Diffrence : "+lavveDiff2);
	         	    	System.out.println("Rati : "+ratio2);
	         	    	System.out.println("JaccardRatio : "+jaccard2);
	         	    	System.out.println("-----------------------");*/
	         	    	
	         	    	
	         	    	
	         	    	
	         	    	
	         	    	
	         	  		if(betterRatio>0.65f&&jaccardMin>JaccardThreshold)
	         	  		{
	         	  			System.out.println("Changing Failed");
	         	  			break;
	         	  		}
	         	  		
	         	  		node.setTextContent(ArabicText.sentences[j]);
	         	  		System.out.println("Changed Succusfully With : "+ArabicText.sentences[j]);
	         	  		ArabicText.sentences[j]="";
	         	  		

	         	  		List<String> stringList = new ArrayList<String>();
	         	  		Collections.addAll(stringList, sentence, str1.TranslatedSentence1);
	         	  		JaccardDistance.JaccardWeight(stringList);
	         	  		System.out.println(JaccardDistance.weightMap);
	         	  		System.out.println("======================================");
	         	  		JaccardDistance.weightMap=new HashMap<String, Double>();
	         	  		found=true;
	         	    	//transformer.transform(source, sresult);
	         	    	break;
	         	    }
	         	    else {
	         	     continue;
	         	    }
	         	    
	         	}
	         	if(found)
	         		break;
	         
	         }

	         return ArabicText;
		 }
	    
	    
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
	    
	    
	    
	    

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	
}
