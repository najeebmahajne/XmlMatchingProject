import java.io.FileNotFoundException;

public class CalculateMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String arabicTXTFilePathManual="C:\\Users\\HP\\Downloads\\textset\\2845\\booklet-ar.txt";
		String arabicTXTFilePath="C:\\Users\\HP\\Downloads\\textset\\2845\\2845ArabicXML.txt";
		try {
			TextProccessing ManualArabicText=new TextProccessing(arabicTXTFilePathManual,'A');
			TextProccessing ArabicText=new TextProccessing(arabicTXTFilePath,'A');
			MatchingPercent per=new MatchingPercent();
			int num=per.compareStringsPercentage(ManualArabicText.text,ArabicText.text);
			System.out.println(num);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
