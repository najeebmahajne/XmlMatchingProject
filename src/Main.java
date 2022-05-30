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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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






public class Main {

  
  public static String[] XMLFILESARRAY = new String[5];
  public static String[] TXTFILESARRAY = new String[5];
  public static String[] OUTPUTFILESARRAY = new String[5];
 
 
  
   public Scanner myReader = new Scanner(System.in);//, ParserConfigurationException, SAXException
   //static HashMap<String, String> defenitions = new HashMap<String, String>();
   static HashMap<String, String> noMatchingFoundDictionary = new HashMap<String, String>();
  public static void main(String[] args) throws FileNotFoundException {
	  Dictionary defenitions=new Dictionary();
	//we have an excel dictionary that will be connected to a hashmap,
	  //but for now using this manual hashmap that contains word required from the excel dic
	  
	  
	  //XMLFILES
	 // XMLFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2754\\booklet.xml";
	 // XMLFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2756\\booklet.xml";
	  XMLFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2762\\booklet.xml";
	 // XMLFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2763\\booklet.xml";
	 //XMLFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2765\\booklet.xml";
	  /*XMLFILESARRAY[5]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2833\\booklet.xml";
	  XMLFILESARRAY[6]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2834\\booklet.xml";
	  XMLFILESARRAY[7]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2837\\booklet.xml";
	  XMLFILESARRAY[8]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2843\\booklet.xml";
	  XMLFILESARRAY[9]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2845\\booklet.xml";
	  */
	  //TXTFILES
	  //TXTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2754\\2754.txt";
	 // TXTFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2756\\2756.txt";
	  TXTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2762\\2762.txt";
	  //TXTFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2763\\2763.txt";
	  //TXTFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2765\\2765.txt";//
	 /* TXTFILESARRAY[5]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2833\\2833.txt";
	  TXTFILESARRAY[6]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2834\\2834.txt";
	  TXTFILESARRAY[7]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2837\\2837.txt";
	  TXTFILESARRAY[8]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2843\\2843.txt";
	  TXTFILESARRAY[9]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2845\\2845.txt";*/
	  
	  //OUTPUT FILES
	  //OUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2754\\2754Arabic.xml";
	  //OUTPUTFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2756\\2756Arabic.xml";
	  OUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2762\\2762Arabic.xml";
	 // OUTPUTFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2763\\2763Arabic.xml";
	  //OUTPUTFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2765\\2765Arabic.xml";/*
	  //OUTPUTFILESARRAY[5]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2833\\2833ArabicXML.txt";
	  //OUTPUTFILESARRAY[6]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2834\\2834ArabicXML.txt";
	  //OUTPUTFILESARRAY[7]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2837\\2837ArabicXML.txt";
	  //OUTPUTFILESARRAY[8]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2843\\2843ArabicXML.txt";
	  //OUTPUTFILESARRAY[9]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2845\\2845ArabicXML.txt";
	  
	  
	  
	  
	 
	  for(int fileNumber=0;fileNumber<1;fileNumber++) {
	  
	  
      
	try {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		DocumentBuilder db = factory.newDocumentBuilder();
		Document firstDoc = db.parse(new File(XMLFILESARRAY[fileNumber]));
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer =tFactory.newTransformer();
		DOMSource source = new DOMSource(firstDoc);
		File myObj = new File(OUTPUTFILESARRAY[fileNumber]);  
	    StreamResult sresult = new StreamResult(myObj);
	    //transformer.transform(source, sresult);
		
		
		firstDoc.getDocumentElement().normalize();
		System.out.println("Root Element :" + firstDoc.getDocumentElement().getNodeName());
		System.out.println("------");
		//Document secondDoc = db.parse(new File(XMLFILESARRAY[fileNumber]));
		//secondDoc.getDocumentElement().normalize();
		PDFTextProccessing ArabicText=new PDFTextProccessing(TXTFILESARRAY[fileNumber],'A');
		 ArabicText.Normalization();
		// ArabicText.PrintSentences();
		 Node componentInfoTag=firstDoc.getElementsByTagName("componentInfo").item(0);
		 Node SECONDcomponentInfoTag=firstDoc.getElementsByTagName("componentInfo").item(1);
		 NodeList list = componentInfoTag.getChildNodes();
		 NodeList SECONDlist = SECONDcomponentInfoTag.getChildNodes();
		 
		 //collectionBody Tag
		 Node collectionBodyTag=firstDoc.getElementsByTagName("collectionBody").item(0);
		 NodeList thirdList = collectionBodyTag.getChildNodes();
		 
		 //intro P TAG
		// Node introTag=firstDoc.getElementsByTagName("intro").item(0);
		 //Node PTag=introTag.getFirstChild().getNextSibling();
		 
		 
		 
		 
		 
		 //First two componentsdata Tags change , because they have the same value in each document in all documents
		//+docTitle Tag
		 int iForDocTitleTag=0;
		 
		 for(int current=0;current<list.getLength();current++)
		 {
			 Node componentDataTag = list.item(current);
			 Node secondcomponentDataTag = SECONDlist.item(current);
			 Node collectionBodyTagChild = thirdList.item(current);
			 if (componentDataTag.hasAttributes()) {
                 //Attr attr = (Attr) componentDataTag.getAttributes().getNamedItem("showAs");
				 NamedNodeMap attr = componentDataTag.getAttributes();
				 Node node = attr.getNamedItem("showAs");
				 NamedNodeMap secondattr = secondcomponentDataTag.getAttributes();
				 Node secondnode = secondattr.getNamedItem("showAs");
				 NamedNodeMap thirdattr = collectionBodyTagChild.getAttributes();
				 Node thirdnode = thirdattr.getNamedItem("showAs");
                 if (node != null) {
                	 
                	 String attributeTXT= node.getTextContent();  
                	 
                	// ArabicText=translationAlgorithm(attributeTXT,ArabicText,node);
                	 translationAlgorithm(attributeTXT,ArabicText,node);
                	 secondnode.setTextContent(node.getTextContent()); 
                	 thirdnode.setTextContent(node.getTextContent());
                	//<docTitle> Tag node 
           	     	 Node docTitleTag=firstDoc.getElementsByTagName("docTitle").item(iForDocTitleTag);
           	         docTitleTag.setTextContent(node.getTextContent());
           	         //INTRO P TAG
           	        // if(iForDocTitleTag==0)
           	        	//PTag.setTextContent(node.getTextContent());
           	         iForDocTitleTag++;
                	          
                   
                 }
             }
			// transformer.transform(source, sresult); 
		 }
		 //the third <componentInfo> tag - we called it second because the first two component Tags share same code in the previous loop
		 Node secondcomponentInfoTag=firstDoc.getElementsByTagName("componentInfo").item(2);
		 NodeList secondList = secondcomponentInfoTag.getChildNodes();
		 for(int current=0;current<secondList.getLength();current++)
		 {
			 Node componentDataTag = secondList.item(current);
			 if (componentDataTag.hasAttributes()) {
				 NamedNodeMap attr = componentDataTag.getAttributes();
				 Node node = attr.getNamedItem("showAs");
				 if (node != null) {
                     String attributeTXT= node.getTextContent();                      
                     System.out.println("attribute: " + attributeTXT);
                     if(defenitions.defenitions.containsKey(attributeTXT)) {
                    	 node.setTextContent(defenitions.defenitions.get(attributeTXT));
                    	 System.out.println("Successfully CHANGED : "+attributeTXT+" - *WITH* - "+node.getTextContent());
                    	 System.out.println();
                     }else 
                     {
                    	 noMatchingFoundDictionary.put(node.getLocalName(), attributeTXT);
                    	 System.out.println("SUBTITUE FAILED - NOT IN DICTIONARY");
                    	 System.out.println();
                     }
                   }
            } 
		 }
		
		 //<organization> Tag text change
		 Node organizationTag=firstDoc.getElementsByTagName("organization").item(0).getFirstChild();
		 if(defenitions.defenitions.containsKey(organizationTag.getTextContent())) 
		 {
			 organizationTag.setTextContent(defenitions.defenitions.get(organizationTag.getTextContent()));
			 System.out.println("<Orginaization> Tag Succuessfully Subtitute");
		 }else 
		 {
			 noMatchingFoundDictionary.put(organizationTag.getLocalName(), organizationTag.getTextContent());
			 System.out.println("SUBTITUE FAILED - NOT IN DICTIONARY");
        	 System.out.println();
		 }
		 
		 
		//<docType> Tag text change
		 Node docTypeTag=firstDoc.getElementsByTagName("docType").item(0).getFirstChild();
		 if(defenitions.defenitions.containsKey(docTypeTag.getTextContent())) 
		 {
			 docTypeTag.setTextContent(defenitions.defenitions.get(docTypeTag.getTextContent()));
			 System.out.println("<docType> Tag Succuessfully Subtitute");
		 }else 
		 {
			 noMatchingFoundDictionary.put(organizationTag.getLocalName(), organizationTag.getTextContent());
			 System.out.println("SUBTITUE FAILED - NOT IN DICTIONARY");
        	 System.out.println();
		 }
		 
		 NodeList tocHeadingList = firstDoc.getElementsByTagName("tocHeading");
		 //<tocHeading>
		 for(int i=1;i<tocHeadingList.getLength();i++) {
		 Node tocHeadingTag=tocHeadingList.item(i);
		 String tocHeadingText = tocHeadingTag.getTextContent();
		 tocHeadingText=tocHeadingText.replaceAll("התש\"ף", "");
		 if(defenitions.defenitions.containsKey(tocHeadingText)) 
		 {
			  tocHeadingTag.setTextContent(defenitions.defenitions.get(tocHeadingText));
			  System.out.println("<tocHeading> Tag Succuessfully Subtitute"); 
		 }else 
		 {
			 translationAlgorithm(tocHeadingText,ArabicText,tocHeadingTag);

		 }

		 }
		 
		 
		 
		//all  <refernces> tag attr text change
		
		 NodeList referencesList=firstDoc.getElementsByTagName("references");
		 for(int num=0;num<referencesList.getLength();num++) {
		 Node secondReferencesTag=firstDoc.getElementsByTagName("references").item(num);
		 NodeList secondReferencesTagChilds = secondReferencesTag.getChildNodes();
		 
		 for(int current=0;current<secondReferencesTagChilds.getLength();current++)
		 {
			 Node secondReferencesTagChild = secondReferencesTagChilds.item(current);
			 
			 if (secondReferencesTagChild.hasAttributes()) {
				 NamedNodeMap attr = secondReferencesTagChild.getAttributes();
				 Node node = attr.getNamedItem("showAs");
				 
				 if (node != null) 
				 {
					 System.out.println("*  REFRENCES TAG's * ");
					 String attributeTXT= node.getTextContent();                      
                     System.out.println("attribute: " + attributeTXT);
                     if(defenitions.defenitions.containsKey(attributeTXT)) {
                    	 node.setTextContent(defenitions.defenitions.get(attributeTXT));
                    	 System.out.println("Successfully CHANGED : "+attributeTXT+" - *WITH* - "+node.getTextContent());
                    	 System.out.println();
                     }else {
                    	 noMatchingFoundDictionary.put(node.getLocalName(), attributeTXT);
                    	 System.out.println("SUBTITUE FAILED - NOT IN DICTIONARY : ["+attributeTXT+"]");
                    	 System.out.println();
                     }
                    	 
				 }
			 }
		 }
		 }
		 
		 
		 
		 
		 //<publication> tag showas attr text change
		 Node publicationTag=firstDoc.getElementsByTagName("publication").item(0);
		 NamedNodeMap publicationTagAttributes = publicationTag.getAttributes();
		 Node node = publicationTagAttributes.getNamedItem("showAs");
		 System.out.println("* PUBLICATION TAG 'showAs' Attribute txt subtitute * ");
		 String attributeTXT= node.getTextContent();                      
         System.out.println("attribute: " + attributeTXT);
         if(defenitions.defenitions.containsKey(attributeTXT)) {
        	 node.setTextContent(defenitions.defenitions.get(attributeTXT));
        	 System.out.println("Successfully CHANGED : "+attributeTXT+" - *WITH* - "+node.getTextContent());
        	 System.out.println();
         }else {
        	 noMatchingFoundDictionary.put(node.getLocalName(), attributeTXT);
        	 System.out.println("SUBTITUE FAILED - NOT IN DICTIONARY");
        	 System.out.println();}
         
         
         
         
	
        //<signature> Tags list - Childs txt subtitute
         NodeList signatureTags =firstDoc.getElementsByTagName("signature");
        
         for(int current=0;current<signatureTags.getLength();current++)
		 {
        	 Node signatureTag = signatureTags.item(current);
        	 if (signatureTag.getNodeType() == Node.ELEMENT_NODE) 
         		{
        		 Node temp=signatureTags.item(current).getFirstChild().getNextSibling();
        		 Node Persontag=temp.getFirstChild().getNextSibling();
        		 Node Roletag=Persontag.getNextSibling().getNextSibling();
        		 
        		 String PersontagStr=Persontag.getTextContent();
        		 String RoletagStr=Roletag.getTextContent();
        		 System.out.println("Signature Number - "+current);
        		 System.out.println();
        		 System.out.println("<PERSON> TXT : "+PersontagStr);
        		 System.out.println("<Role> TXT : "+RoletagStr);
        		 if(defenitions.defenitions.containsKey(PersontagStr)) {
        			 Persontag.setTextContent(defenitions.defenitions.get(PersontagStr));
                	 System.out.println("Successfully CHANGED Signature <Person> tag text!");
                 }
        		 else
        		 {
                	 System.out.println("<Person> tag failed to find a match in dictionary!");
                	 System.out.println("");
                	 noMatchingFoundDictionary.put(Persontag.getLocalName(), PersontagStr);
                 }
        		 if(defenitions.defenitions.containsKey(RoletagStr)) {
        			 Roletag.setTextContent(defenitions.defenitions.get(RoletagStr));
                	 System.out.println("Successfully CHANGED Signature <Role> tag text!");
                	 System.out.println("");
                	 System.out.println("************************************************");
                 }else {
                	 System.out.println("<Role> tag failed to find a match in dictionary!");
                	 System.out.println("************************************************");
                	 noMatchingFoundDictionary.put(Roletag.getLocalName(), RoletagStr);
                 }
        		 
         		}
		 }
         
         
         
         
         
         
         
         
         //BODY TAGS CONTENT
         ///TextProccessing bodyText=new TextProccessing
        		/// ("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2812\\body.txt",'A');
        /// bodyText.Normalization();
		/// bodyText.sentences=bodyText.text.split("[\\.–:]");
         
        /// NodeList bodyPTags =firstDoc.getElementsByTagName("p");
         //boolean flag=false;
        /// for(int i=1;i<bodyPTags.getLength()-counter;i++) 
       ///  {
        	 
        	 ///Node PNode=bodyPTags.item(i);
        	/* if(PNode.hasAttributes()) 
        	 {
        		 flag=true;
        		 break;
        	 }*/
        	 
        	/// NodeList PNodeList=PNode.getChildNodes();
        	 //if(PNodeList.getLength()>3)
        		// break;
        	/// String text=PNode.getTextContent();
        	/// System.out.println("<P> BODY TAG TEXT ");
        	 
        	/// translationAlgorithm1(text,bodyText,PNode);
        	
       ///  }
		 //preface tag change 
		/* Node prefaceTag=firstDoc.getElementsByTagName("preface").item(0);
		 NodeList prefaceTagChilds = prefaceTag.getChildNodes();
		 Node containerTag = prefaceTagChilds.item(1);
		 Node pTag = containerTag.getFirstChild().getNextSibling();
		 for(int current=0;current<pTag.getChildNodes().getLength();current++)
		 {
			 Node pTagChild = pTag.getChildNodes().item(current);
			 if (pTagChild.hasAttributes()) 
			 {
				 System.out.println("*Preface Child Tag* ");
				 NamedNodeMap attr = pTagChild.getAttributes();
				 String attributeTXT= pTagChild.getFirstChild().getTextContent();                      
                 System.out.println("attribute: " + attributeTXT);
                 String arabictxt=firstDoc.getElementsByTagName("componentInfo").item(0).getChildNodes().item(1).getTextContent();
                 pTagChild.getFirstChild().setTextContent(arabictxt);
                 System.out.println("Successfully CHANGED : "+attributeTXT+" - *WITH* - "+pTagChild.getFirstChild().getTextContent());
				 
			 }
		 }*/
         
         
		 
		// System.out.println("Words that Failed Finding a match in Dictionary :");
		// System.out.println();
		// noMatchingFoundDictionary.entrySet().forEach(entry -> {
			  //  System.out.println(entry.getKey() + " " + entry.getValue());
			//});
		 
		
         
         
         transformer.transform(source, sresult);
		 System.out.println("DOCUMENT SAVED!");
		 System.out.println();
		 
         	 
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  }

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
	 
	 public static long filesCompareByLine(Path path1, Path path2) throws IOException {
		    try (BufferedReader bf1 = Files.newBufferedReader(path1);
		         BufferedReader bf2 = Files.newBufferedReader(path2)) {
		        
		        long lineNumber = 1;
		        String line1 = "", line2 = "";
		        while ((line1 = bf1.readLine()) != null) {
		            line2 = bf2.readLine();
		            if (line2 == null || !line1.equals(line2)) {
		                return lineNumber;
		            }
		            lineNumber++;
		        }
		        if (bf2.readLine() == null) {
		            return -1;
		        }
		        else {
		            return lineNumber;
		        }
		    }
		}
	 private static String removeVowels(String hebString){
		    String newString = "";
		    for(int j=0; j<hebString.length() ; j++) {
		        char c = hebString.charAt(j);
		        if(hebString.charAt(j)<1425 || c>1479)
		            newString = newString + c;
		    }
		    return newString;
		}
	 public static PDFTextProccessing translationAlgorithm(String attributeTXT,PDFTextProccessing ArabicText,Node node) throws FileNotFoundException, IOException {
		 TrainedTokenizer tok=new TrainedTokenizer();
		 Dictionary defenitions=new Dictionary();
		 XMLTagTextProccessing str1=new XMLTagTextProccessing(attributeTXT);
		 str1.sentencePrint();
		 str1.tokensTranslation();
		 int counter=0;
		 
         for(int i=0;i<str1.translatedTokens.length;i++) {
        	 
       	  String token = null;
       if(defenitions.defenitions.containsKey(str1.tokens[i])) {
    	   
          token=defenitions.defenitions.get(str1.tokens[i]);  
          
          str1.TranslatedSentence1=str1.TranslatedSentence1.replaceAll(str1.translatedTokens[i], token);
          str1.translatedTokens[i]=token;
          counter++;
          String withoutNums=removeAllDigit(str1.sentence);
          String[] arrWithoutNums=tok.tokenize(withoutNums);
        if(arrWithoutNums.length==counter) {
        	  System.out.println("Successfully Changed By Dictionary");
        	  System.out.println("************************");
        	  node.setTextContent(token);
        	  return ArabicText;
          }
        		  }else
        		  {
       	   token=str1.translatedTokens[i];
          }
       
       token=token.replaceAll("ال", "");
      // if(token.contains("من"))
    	   //break;
       
         boolean found=false;
         	for(int j=0;j<ArabicText.sentences.length;j++) 
         	{
         		boolean result = ArabicText.sentences[j].contains(token);
         	    if(result) {
         	    	if(ArabicText.sentences[j].contains("المادة")&&token=="قانون")
         	    		break;
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
         	    	//sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll(".", "");
         	    	
         	    	int lavveDiff=calculate(sentenceWithoutNumbers,translatedSentenceWithoutNumbers);
         	    	int maxLength=Math.max(sentenceWithoutNumbers.length(), translatedSentenceWithoutNumbers.length());
         	    	
         	    	String translatedSentenceWithNumbers=str1.TranslatedSentence1;
         	    	translatedSentenceWithNumbers=translatedSentenceWithNumbers.replaceAll("الكف", "كف");
         	    	translatedSentenceWithNumbers=translatedSentenceWithNumbers.replaceAll("إ", "ا");
         	    	
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
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public static PDFTextProccessing translationAlgorithm1(String attributeTXT,PDFTextProccessing ArabicText,Node node) throws FileNotFoundException, IOException {
		 TrainedTokenizer tok=new TrainedTokenizer();
		 Dictionary defenitions=new Dictionary();
		 XMLTagTextProccessing str1=new XMLTagTextProccessing(attributeTXT);
		 str1.sentencePrint();
		 str1.tokensTranslation();
		 int counter=0;
		 
	       
         for(int i=0;i<str1.translatedTokens.length;i++) {
        	 
       	  String token = null;
       if(defenitions.defenitions.containsKey(str1.tokens[i])) {
    	   
          token=defenitions.defenitions.get(str1.tokens[i]);  
          
          str1.TranslatedSentence1=str1.TranslatedSentence1.replaceAll(str1.translatedTokens[i], token);
          str1.translatedTokens[i]=token;
          counter++;
          String withoutNums=removeAllDigit(str1.sentence);
          String[] arrWithoutNums=tok.tokenize(withoutNums);
        if(arrWithoutNums.length==counter) {
        	  System.out.println("Successfully Changed By Dictionary");
        	  System.out.println("************************");
        	  node.setTextContent(token);
        	  return ArabicText;
          }
        		  }else
        		  {
        			  
       	   token=str1.translatedTokens[i];
          }
       
      
       
         boolean found=false;
         	for(int j=0;j<ArabicText.sentences.length;j++) 
         	{
         		
         		boolean result = ArabicText.sentences[j].contains(token);
         	    if(result) {
         	    	if(ArabicText.sentences[j].length()>str1.TranslatedSentence1.length()*3||ArabicText.sentences[j].length()*3<str1.TranslatedSentence1.length())
         	    		break;
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
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
  

}
