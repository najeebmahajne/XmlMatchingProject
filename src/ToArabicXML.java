

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Bidi;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;


public class ToArabicXML {
	/*
	 * https://www.tutorialspoint.com/java_xml/java_jdom_modify_document.htm
	 * 
	 * samar abu hdeeb 19.03.2022
	 */
	//private static final String FILENAME = "C:\\Users\\Student\\Desktop\\mavenproject\\file\\lawBook2360@.xml";

	public static void main(String[] args) throws IOException {
		    // Instantiate the Factory
			//laws' pdf file 
			File pdfFile = new File("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2853\\2853.pdf");
			//File pdfFile1 = new File("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\2319-2382\\2367.pdf");
			PDDocument doc2364 = PDDocument.load(pdfFile); // Instantiate PDFTextStripper class
			//PDDocument doc2367 = PDDocument.load(pdfFile1);
			PDFTextStripper pdfStripper = new PDFTextStripper(); // Retrieving text from PDF document 
			
			//System.out.println(text);
			//write (and read) from pdf to txt 
			FileWriter txt=new FileWriter("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2853\\2853.txt");
			//FileWriter txt1=new FileWriter("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\2319-2382\\2367.txt");
			String text2364=pdfStripper.getText(doc2364);
			//String text2367=pdfStripper.getText(doc2367);
			
			txt.write( text2364);
			//txt1.write( text2367);
			
			txt.close();
			//txt1.close();
			doc2364.close();
			//doc2367.close();
			}
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
	private static void bidiReorder(String text)
    {
        try {
        Bidi bidi = new Bidi((new ArabicShaping(ArabicShaping.LETTERS_SHAPE)).shape(text), 127);
         //   bidi.setReorderingMode(0);
           // return bidi.writeReordered(2);
        }
        catch (Exception ase3) {
        return ;}
    }
        public static boolean isInt(String Input)
        {
            try{Integer.parseInt(Input);return true;}
            catch(NumberFormatException e){return false;}
        }
        public static String reverseNumbersInString(String Input)
        {
            char[] Separated = Input.toCharArray();int i = 0;
            String Result = "",Hold = "";
            for(;i<Separated.length;i++ )
            {
                if(isInt(Separated[i]+"") == true)
                {
                    while(i < Separated.length && (isInt(Separated[i]+"") == true ||  Separated[i] == '.' ||  Separated[i] == '-'))
                    {
                        Hold += Separated[i];
                        i++;
                    }
                   // Result+=reverse(Hold);
                    Hold="";
                }
                else{Result+=Separated[i];}
            }
            return Result;
        }
    }

