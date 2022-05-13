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






public class ReadXmlDomParser {

  private static final String FIRSTFILENAME = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2823\\booklet.xml";
  private static String[] XMLFILESARRAY = new String[1];
  private static String[] TXTFILESARRAY = new String[10];
  private static String[] OUTPUTFILESARRAY = new String[10];
 
  
   public Scanner myReader = new Scanner(System.in);//, ParserConfigurationException, SAXException
  public static void main(String[] args) throws FileNotFoundException {
	  
	//we have an excel dictionary that will be connected to a hashmap,
	  //but for now using this manual hashmap that contains word required from the excel dic
	  HashMap<String, String> defenitions = new HashMap<String, String>();
	  defenitions.put("تعديل", "תיקון");
      defenitions.put("הגדרות", "تعاريف");
      defenitions.put("חוק", "قانون");
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
      defenitions.put("בנימין נתניהו","بنيامين نتنياهو");
      defenitions.put("יעקב נאמן","يعقوب نئيمان");
      defenitions.put("שמעון פרס","شمعون بيرس");
      defenitions.put("ראובן ריבלין","رؤبين ريفلين");
      defenitions.put("ראש הממשלה","رئيس الحكومة");
      defenitions.put("שר המשפטים","وزير العدل");
      defenitions.put("נשיא המדינה","رئيس الدولة");
      defenitions.put("יושב ראש הכנסת","رئيس الكنيست");
      defenitions.put("הפרשי הצמדה וריבית","فروقات الربط والفائدة");
      defenitions.put("עבודה ברכוש המשותף","العمل بالممتلكات المشتركة");
      defenitions.put("תאריך כניסה לתוקף","حسب مدلولها");
      defenitions.put("מחבר המסמך","كاتب الملف");
      defenitions.put("רשומות","السجلات");
      defenitions.put("עורך המסמך","محرر الملف");
      defenitions.put("סיווג החוקים","تصنيف القوانين");
      defenitions.put("כניסה לתוקף","تاريخ السريان");
      defenitions.put("תכנית החיזוק","خطة التعزيز");
      defenitions.put("הכנסת","الكنيست");
      defenitions.put("יצחק אהרונוביץ","اسحاق اهارونوفيتش");
      defenitions.put("ספר החוקים","كتاب القوانين");
      defenitions.put("סמל מדינת ישראל","رمز دولة اسرائيل");
      defenitions.put("תאריך פרסום המסמך","تاريخ نشر الملف");
      defenitions.put("שלום שמחון","شالوم سمحون");
      defenitions.put("יריב לוין","يريف لفين");
      defenitions.put("שר התעשייה המסחר והתעסוקה","وزير الصناعة والتجارة والعمل");
      defenitions.put("יולי יואל אדלשטיין","يولي يوئيل ادلشتاين");
      defenitions.put("בצלאל סמוטריץ'","بتسلئيل سموتريتش");
      defenitions.put("שר התחבורה והבטיחות בדרכים","وزير المواصالت والسالمة في الطرق");
      defenitions.put("אמיר אוחנה","أمير اوحانا");
      defenitions.put("שר המשפטים","وزير العدل");
      defenitions.put("בנימין גנץ","بنيامين جانتس");
      defenitions.put("אבי ניסנקורן","آفي نيسان كورن");
      defenitions.put("השר לביטחון הפנים","");
      defenitions.put("יובל שטייניץ","يوفال شتاينيتس");
      defenitions.put("שר האנרגיה","وزير الطاقة");
      defenitions.put("ישראל כץ","يسرائيل كاتس");
      defenitions.put("שר האוצר","وزير المالية");
      defenitions.put("","");
      defenitions.put("","");
	  
	  //XMLFILES
	  XMLFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2790\\booklet.xml";
	  /* XMLFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2849\\booklet.xml";
	  XMLFILESARRAY[2]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2850\\booklet.xml";
	  XMLFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2852\\booklet.xml";
	  XMLFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2853\\booklet.xml";
	  XMLFILESARRAY[5]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2833\\booklet.xml";
	  XMLFILESARRAY[6]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2834\\booklet.xml";
	  XMLFILESARRAY[7]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2837\\booklet.xml";
	  XMLFILESARRAY[8]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2843\\booklet.xml";
	  XMLFILESARRAY[9]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2845\\booklet.xml";
	  */
	  //TXTFILES
	  TXTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2790\\2790.txt";/*
	  TXTFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2849\\2849.txt";
	  TXTFILESARRAY[2]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2850\\2850.txt";
	  TXTFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2852\\2852.txt";
	  TXTFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2853\\2853.txt";
	  TXTFILESARRAY[5]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2833\\2833.txt";
	  TXTFILESARRAY[6]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2834\\2834.txt";
	  TXTFILESARRAY[7]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2837\\2837.txt";
	  TXTFILESARRAY[8]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2843\\2843.txt";
	  TXTFILESARRAY[9]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2845\\2845.txt";*/
	  
	  //OUTPUT FILES
	  OUTPUTFILESARRAY[0]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2790\\2790ArabicXML.txt";/*
	  OUTPUTFILESARRAY[1]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2849\\2849ArabicXML.txt";
	  OUTPUTFILESARRAY[2]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2850\\2850ArabicXML.txt";
	  OUTPUTFILESARRAY[3]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2852\\2852ArabicXML.txt";
	  OUTPUTFILESARRAY[4]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2853\\2853ArabicXML.txt";
	  OUTPUTFILESARRAY[5]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2833\\2833ArabicXML.txt";
	  OUTPUTFILESARRAY[6]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2834\\2834ArabicXML.txt";
	  OUTPUTFILESARRAY[7]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2837\\2837ArabicXML.txt";
	  OUTPUTFILESARRAY[8]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2843\\2843ArabicXML.txt";
	  OUTPUTFILESARRAY[9]="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2845\\2845ArabicXML.txt";*/
	 
	 
	  for(int fileNumber=0;fileNumber<XMLFILESARRAY.length;fileNumber++) {
	  
	  
      
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
		Document secondDoc = db.parse(new File(XMLFILESARRAY[fileNumber]));
		secondDoc.getDocumentElement().normalize();
		TextProccessing ArabicText=new TextProccessing(TXTFILESARRAY[fileNumber],'A');
		 ArabicText.Normalization();
		// ArabicText.PrintSentences();
		 Node componentInfoTag=firstDoc.getElementsByTagName("componentInfo").item(0);
		 Node SECONDcomponentInfoTag=firstDoc.getElementsByTagName("componentInfo").item(1);
		 NodeList list = componentInfoTag.getChildNodes();
		 NodeList SECONDlist = SECONDcomponentInfoTag.getChildNodes();
		 TrainedTokenizer tok=new TrainedTokenizer();
		 
		 
		 //first and second component data change , because they have the same value in each document in all documents
		 //+PREFACE TAG +doctitle
		 for(int current=0;current<list.getLength();current++)
		 {
			 Node componentDataTag = list.item(current);
			 Node secondcomponentDataTag = SECONDlist.item(current);
			 if (componentDataTag.hasAttributes()) {
                 //Attr attr = (Attr) componentDataTag.getAttributes().getNamedItem("showAs");
				 NamedNodeMap attr = componentDataTag.getAttributes();
				 Node node = attr.getNamedItem("showAs");
				 NamedNodeMap secondattr = secondcomponentDataTag.getAttributes();
				 Node secondnode = secondattr.getNamedItem("showAs");
                 if (node != null) {
                     String attributeTXT= node.getTextContent();  
                     
                     System.out.println("attribute: " + attributeTXT); 
                     StringProccessing str=new StringProccessing(attributeTXT);
                     
                      str.tokensTranslation();
                      for(int i=0;i<str.translatedTokens.length;i++) {
                    	  String token = null;
                    if(defenitions.containsKey(str.tokens[i])) {
                       token=defenitions.get(str.tokens[i]);   }else {
                    	   token=str.translatedTokens[i];
                       } 
                      
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
                      	    	
                      	    	String text=ArabicText.sentences[j];
                      	    	text=text.replaceAll("-", " ");		
                      	    	String[] Arabictokens=tok.tokenize(text);
                      	    	System.out.print("Arab PDF Sentence : ");
                      	    	
                      	    	System.out.println();
                      	    	String[] newArabictokens=Arrays.copyOfRange(Arabictokens, 0, endIndex);
                      	    	String sentence = String.join(" ", newArabictokens);
                      	    	sentence = sentence.replaceAll("null", "");
                      	    	sentence = sentence.replaceAll("–", "");
                      	    	sentence = sentence.replaceAll(":", "");
                      	    	sentence = sentence.replaceAll("\\.", "");
                      	    	System.out.println(sentence);
                      	    	str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("،", "");
                      	    	//str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("في", "");
                      	    	str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("'", "");
                      	    	str.TranslatedSentence1=str.TranslatedSentence1.replaceAll("התשע״ב", "");
                      	    	String sentenceWithoutNumbers=removeAllDigit(sentence);
                      	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("-", "");
                      	    	//sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("-", "");
                      	    	
                      	    	String translatedSentenceWithoutNumbers=removeAllDigit(str.TranslatedSentence1);
                      	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("اا", "ا");
                      	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("بك", "ك");
                      	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("لل", "ل");
                      	    	sentenceWithoutNumbers=sentenceWithoutNumbers.replaceAll("عات", "ع");
                      	    	//translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("(", "");
                      	    	//translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll(")", "");
                      	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("-", "");
                      	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("الكف", "كف");
                      	    	translatedSentenceWithoutNumbers=translatedSentenceWithoutNumbers.replaceAll("إ", "ا");
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
                      	  		//<preface> Tag node 
                      	  	    Node documentRefTag=firstDoc.getElementsByTagName("documentRef").item(0);
                      	  	    NamedNodeMap attr1 = documentRefTag.getAttributes();
                      	  	    Node node1 = attr1.getNamedItem("showAs");
                      	  	    node1.setTextContent(ArabicText.sentences[j]);
                      	        //<docTitle> Tag node 
                      	     	Node docTitleTag=firstDoc.getElementsByTagName("docTitle").item(0);
                   		        Node textNode=docTitleTag.getFirstChild();
                   		        textNode.setTextContent(ArabicText.sentences[j]);
                   		        
                   		        
                   		        //second componentinfo tag
                      	  		secondnode.setTextContent(ArabicText.sentences[j]);
                      	  		System.out.println("Changed Succusfully With : "+ArabicText.sentences[j]);
                      	  		ArabicText.sentences[j]="";
                      	  		System.out.println();
                      	  		List<String> stringList = new ArrayList<String>();
                      	  		Collections.addAll(stringList, sentence, str.TranslatedSentence1);
                      	  		JaccardDistance.JaccardWeight(stringList);
                      	  		System.out.println(JaccardDistance.weightMap);
                      	  		JaccardDistance.weightMap=new HashMap<String, Double>();
                      	  		found=true;
                      	    	transformer.transform(source, sresult);
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
                     if(defenitions.containsKey(attributeTXT)) {
                    	 node.setTextContent(defenitions.get(attributeTXT));
                    	 
                   
                    	 System.out.println("Successfully CHANGED : "+attributeTXT+" - *WITH* - "+node.getTextContent());
                    	 System.out.println();
                     }
                   }
            }
			// File myObj = new File("C:\\Users\\HP\\Desktop\\filename.txt");  
			 //Files.writeString("C:\\Users\\HP\\Desktop\\filename.txt", transformer.transform(source, sresult),StandardCharsets.UTF_8);
		 }
		 
		 //<organization> Tag text change
		 Node organizationTag=firstDoc.getElementsByTagName("organization").item(0).getFirstChild();
		 if(defenitions.containsKey(organizationTag.getTextContent())) 
		 {
			 organizationTag.setTextContent(defenitions.get(organizationTag.getTextContent()));
			 System.out.println("<Orginaization> Tag Succuessfully Subtitute");
		 }
		 
		 
		//<docType> Tag text change
		 Node docTypeTag=firstDoc.getElementsByTagName("docType").item(0).getFirstChild();
		 if(defenitions.containsKey(docTypeTag.getTextContent())) 
		 {
			 docTypeTag.setTextContent(defenitions.get(docTypeTag.getTextContent()));
			 System.out.println("<docType> Tag Succuessfully Subtitute");
		 }
		 
		 
		 
		 
		 
		 NodeList referencesList=firstDoc.getElementsByTagName("references");
		 //all  <refernces> tag attr text change
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
                     if(defenitions.containsKey(attributeTXT)) {
                    	 node.setTextContent(defenitions.get(attributeTXT));
                    	 System.out.println("Successfully CHANGED : "+attributeTXT+" - *WITH* - "+node.getTextContent());
                    	 System.out.println();
                     }
                    	 
				 }
			 }
		 }
		 }
		 
		/* //third <references> Tah Child 'showAs' Attr txt subtitute
		 Node thirdReferencesTag=firstDoc.getElementsByTagName("references").item(2);
		 NodeList thirdReferencesTagChilds = thirdReferencesTag.getChildNodes();
		 for(int i=0;i<thirdReferencesTagChilds.getLength();i++)
		 {
			 Node thirdReferencesTagChild = thirdReferencesTagChilds.item(i);
			 
			 if (thirdReferencesTagChild.hasAttributes()) {
				 NamedNodeMap attr = thirdReferencesTagChild.getAttributes();
				 Node node = attr.getNamedItem("showAs");
				 
				 if (node != null) 
				 {
					 System.out.println("* 3RD REFRENCES TAG's * ");
					 String attributeTXT= node.getTextContent();                      
                     System.out.println("attribute: " + attributeTXT);
                     if(defenitions.containsKey(attributeTXT)) {
                    	 node.setTextContent(defenitions.get(attributeTXT));
                    	 System.out.println("Successfully CHANGED : "+attributeTXT+" - *WITH* - "+node.getTextContent());
                    	 System.out.println();
                     }
                    	 
				 }
			 }
		 }*/
		 
		 
		 //<publication> tag showas attr text change
		 Node publicationTag=firstDoc.getElementsByTagName("publication").item(0);
		 NamedNodeMap publicationTagAttributes = publicationTag.getAttributes();
		 Node node = publicationTagAttributes.getNamedItem("showAs");
		 System.out.println("* PUBLICATION TAG 'showAs' Attribute txt subtitute * ");
		 String attributeTXT= node.getTextContent();                      
         System.out.println("attribute: " + attributeTXT);
         if(defenitions.containsKey(attributeTXT)) {
        	 node.setTextContent(defenitions.get(attributeTXT));
        	 System.out.println("Successfully CHANGED : "+attributeTXT+" - *WITH* - "+node.getTextContent());
        	 System.out.println();
         }
         
         
         
         
       //first <refrences> all child tags showas attr text change
         Node firstReferencesTag=firstDoc.getElementsByTagName("references").item(0);
         NodeList firstReferencesTagChilds = firstReferencesTag.getChildNodes();
         for(int current=0;current<firstReferencesTagChilds.getLength();current++)
		 {
			 Node firstReferencesTagChild = firstReferencesTagChilds.item(current);
			 if (firstReferencesTagChild.hasAttributes()) {
				 NamedNodeMap attr = firstReferencesTagChild.getAttributes();
				 Node node1 = attr.getNamedItem("showAs");	
				 if (node1 != null) 
				 {
					 System.out.println("* FIRST REFERENCES CHILDS 'showAs' Attribute txt subsitute * ");
					 String attributeTXT1= node1.getTextContent();                      
                     System.out.println("attribute: " + attributeTXT1);
                     if(defenitions.containsKey(attributeTXT1)) {
                    	 node1.setTextContent(defenitions.get(attributeTXT1));
                    	 System.out.println("Successfully CHANGED : "+attributeTXT1+" - *WITH* - "+node.getTextContent());
                    	 System.out.println();
                     }else 
                     {
                    	 System.out.println("Not found in dictionary or an english word !");
                     }
                    	 
				 }
			 }
		 }
         
         
         
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
        		 if(defenitions.containsKey(PersontagStr)) {
        			 Persontag.setTextContent(defenitions.get(PersontagStr));
                	 System.out.println("Successfully CHANGED Signature <Person> tag text!");
                	 
                 }else {
                	 System.out.println("<Person> tag failed to find a match in dictionary!");
                	 System.out.println("");
                 }
        		 if(defenitions.containsKey(RoletagStr)) {
        			 Roletag.setTextContent(defenitions.get(RoletagStr));
                	 System.out.println("Successfully CHANGED Signature <Role> tag text!");
                	 System.out.println("");
                	 System.out.println("************************************************");
                 }else {
                	 System.out.println("<Role> tag failed to find a match in dictionary!");
                	 System.out.println("************************************************");
                 }
        		 
         		}
		 }
         
         
         
		 
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
         
         transformer.transform(source, sresult);
		 System.out.println("DOCUMENT SAVED!");
         
        //proccessing text content in a diifrent approach 
         String numbersCleanText=removeAllDigit(ArabicText.text);
         defenitions.put("تعديل", "תיקון");
         ArrayList<String> connectionWords = new ArrayList<String>();
			
			connectionWords.add("في");
			connectionWords.add("كل");
			numbersCleanText=numbersCleanText.replaceAll("–", "");
			numbersCleanText=numbersCleanText.replaceAll(":", "");
			numbersCleanText=numbersCleanText.replaceAll("؛", "");
			numbersCleanText=numbersCleanText.replaceAll("؛", "");
			numbersCleanText=numbersCleanText.replaceAll("//", "");
			numbersCleanText=numbersCleanText.replaceAll("\\)", "");
			numbersCleanText=numbersCleanText.replaceAll("\\(", "");
			numbersCleanText=numbersCleanText.replaceAll("\\.", "");
			numbersCleanText=numbersCleanText.replaceAll("ס\"ח", "");
			numbersCleanText=numbersCleanText.replaceAll("ISSN", "");
			numbersCleanText=numbersCleanText.replaceAll("-", " ");
			numbersCleanText=numbersCleanText.replaceAll("أل", "لأ");
			
			TrainedTokenizer tokenizer=new TrainedTokenizer();
			String[] tokens=tokenizer.tokenize(numbersCleanText);
			System.out.println("");
			String[] translatedTokens=new String[tokens.length];
			for(int i=0;i<tokens.length;i++) 
			{
				String str1=tokens[i];
				if(connectionWords.contains(str1)) 
				{
					if(connectionWords.contains(tokens[i+1])) {
						String str=str1+" "+tokens[i+1]+" "+tokens[i+2];
						
						String str2=GoogleTranslate.translate("he",str);
						str2=removeVowels(str2);
						tokens[i]=str;
						translatedTokens[i]=str2;
						
						
					}
					else 
					{
						String str=str1+" "+tokens[i+1];
						String str2=GoogleTranslate.translate("he",str);
						str2=removeVowels(str2);
						tokens[i]=str;
						defenitions.put(str2, str);
						translatedTokens[i]=str2;
					}
				
				}
				else
				{
					if(defenitions.containsKey(str1))
					{
					translatedTokens[i]=defenitions.get(str1);
					}
					else {
						str1=GoogleTranslate.translate("he",tokens[i]);
						str1=removeVowels(str1);
						translatedTokens[i]=str1;
					}
					
				}
				
			}
			System.out.println("Proccessing Done!");
       //a<content> Tags list 
        /* NodeList contentTags =firstDoc.getElementsByTagName("content");
         
         Node contentTag=contentTags.item(0);
         NodeList contentTagChildrensList=contentTag.getChildNodes();
         
         for(int current=0;current<contentTagChildrensList.getLength();current++)
		 {
        	 Node child = contentTagChildrensList.item(current);
        	 if (child.getNodeType() == Node.ELEMENT_NODE) 
         		{ 
        		 if(child.hasAttributes()==false) {
        			 Node pChild=child.getFirstChild();
        			 Node pLastChild=child.getLastChild();
        			 String str1=pChild.getTextContent();
        			 String str2=pLastChild.getTextContent();
        			 System.out.println("First P Child Text : "+str1);
        			 System.out.println("Second P Child Text : "+str2);
        			 String[] str1Tokens=tok.tokenize(str1);
        			 String firstWord=str1Tokens[0];
        			 String lastWord=str1Tokens[str1Tokens.length-1];
        			 firstWord=removeVowels(firstWord);
        			 lastWord=removeVowels(lastWord);
        			 int index=0;
        			 int lastIndex=0;
        			 for(int i=0;i<translatedTokens.length;i++) {
        				
        				 if(translatedTokens[i].contains(firstWord)) 
        				 {
        					 String arabicFirstWordTranslation=defenitions.get(firstWord);
        					 index=numbersCleanText.indexOf(arabicFirstWordTranslation);
        					 if(index!=-1) 
        					 {
        						 for(int j=index;j<translatedTokens.length;i++) 
        						 {
        							 lastWord=lastWord.replaceAll("הכ", "כ");
        							 if(translatedTokens[j].contains(lastWord)) 
        							 {
        								  arabicFirstWordTranslation=defenitions.get(lastWord);
        								  lastIndex=numbersCleanText.indexOf(arabicFirstWordTranslation);
        								  if(lastIndex!=-1) {
        									  String newStr=numbersCleanText.substring(index, lastIndex);
        									  pChild.setTextContent(newStr);
        									  System.out.println(" Sucssesfully Changed HEBREW WITH - "+newStr+" ");
        								  }
        							 }
        						 }
        					 }
        					 
        				 }
        			 }
        			 
        		 }
        		 
         		}
		 }*/
         
		 transformer.transform(source, sresult);
		 System.out.println("DOCUMENT SAVED!");
		 
		 
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
/*
	  
	  
	  
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
		        if(hebString.charAt(j)<1425 || hebString.charAt(j)>1479)
		            newString = newString + hebString.charAt(j);
		    }
		    return newString;
		}
  

}
