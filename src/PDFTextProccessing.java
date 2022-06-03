import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PDFTextProccessing {
	String filePath;
	String text;
	String[] sentences;
	char languageLetter;
	int sentencesnumber;
	

	public PDFTextProccessing(String filePath,char languageLetter) throws FileNotFoundException {
		this.languageLetter=languageLetter;
		this.filePath=filePath;
		//PDFExtractionByAreas extract = new PDFExtractionByAreas(filePath);
		
		File file = new File(this.filePath);
		Scanner myReader = new Scanner(file);
		this.text =myReader.next();
		 
		 this.sentencesnumber=0;
		while (myReader.hasNextLine())
	     {
			
			this.text+=" ";
			this.text += myReader.nextLine();
			this.sentencesnumber++;
			
			  
	     }
		//String[] textArr=text.split("המחיר",0);
	     myReader.close();
	     
	  
	}
	public void Normalization() throws FileNotFoundException 
	{

	 	DiacriticsRemover diacRemover=new DiacriticsRemover();
	 	PunctuationsRemover puncRemover=new PunctuationsRemover();
	 	if(languageLetter=='A') {
	 		AraNormalizer arn=new AraNormalizer();
	 		
	 		String ArabicNormalizedText=arn.normalize(this.text);
			//ArabicNormalizedText = diacRemover.removeDiacritics(ArabicNormalizedText);
			//ArabicNormalizedText = puncRemover.removePunctuations(ArabicNormalizedText);

			this.sentences=ArabicNormalizedText.split("\\.");
			/*Scanner scanner = new Scanner(new File(filePath));
			int i=0;
			while (scanner.hasNextLine()) {
			   String line = scanner.nextLine();
			   this.sentences[i]=line;
			   i++;
			   // process the line
			}
			scanner.close();*/
	 	}else{
	 		String HebrewNormalizedText=diacRemover.removeDiacritics(this.text);
			HebrewNormalizedText = puncRemover.removePunctuations(HebrewNormalizedText);
			HebrewNormalizedText=HebrewNormalizedText.replaceAll("—", "");
			HebrewNormalizedText=HebrewNormalizedText.replaceAll("1", "");
			this.text=this.text.replaceAll(".", ";");
			this.sentences=this.text.split(";", 0);
			
	 	}
	    
	    
	}
	public void PrintSentences() 
	{
		if(this.sentences.length!=0) 
		{
			for(int i=0;i<this.sentences.length;i++) 
			{
				System.out.println("Sentence NO. "+(i+1)+" "+this.sentences[i]);
			}
		}
		
	}

}
