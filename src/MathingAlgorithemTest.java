import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import com.darkprograms.speech.translator.GoogleTranslate;

public class MathingAlgorithemTest {
	public Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		 File inputpath = new File("C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\Hebrew.txt");
		 Scanner sc = new Scanner(inputpath);
		 /*String HebrewText =sc.next();
		 
		 
		 while (sc.hasNextLine())
	      {
			
			 HebrewText+=" ";
			 HebrewText += sc.next();	 
			  
	      }
	      sc.close();
	      
	      DiacriticsRemover dr=new DiacriticsRemover();
			PunctuationsRemover pr=new PunctuationsRemover();
	      
	      String HebrewNormalizedText=dr.removeDiacritics(HebrewText);
			HebrewNormalizedText = pr.removePunctuations(HebrewNormalizedText);
			String[] HebrewSentences1=HebrewNormalizedText.split(";", 0);
			TrainedTokenizer tok=new TrainedTokenizer();
			String[] tokenizedSentences=new String[HebrewSentences1.length];
			String[] translatedSentences=new String[tokenizedSentences.length];
			for(int i=0 ;i<HebrewSentences1.length;i++) 
			{
				System.out.println("Sentence NO."+i+" "+HebrewSentences1[i]);
				tokenizedSentences=tok.tokenize(HebrewSentences1[i]);
				for(int j=0 ;j<tokenizedSentences[i].length();j++) 
				{
					String translated=GoogleTranslate.translate("ar", tokenizedSentences[i]);
				}
				translatedSentences[i]=GoogleTranslate.translate("ar", tokenizedSentences[i].);
				System.out.println("TranslatedSentence NO."+i+" "+translatedSentences[i]);
			}
			
			
      	  
      	  
      	  
      	  System.out.println();*/
		 String str=GoogleTranslate.translate("ar", "חוק המקרקעין (חיזוק בתים משותפים מפני רעידות אדמה)");
		 String str1="قانون العقارات تقوية العمارات المشتركة من الهزات األريية";
		 System.out.println("Translated: "+str+ " - Length : "+str.length());
		 System.out.println("Original  : "+str1+ " - Length : "+str1.length());
		 int diff=calculate(str,str1);
		 System.out.println("Diffrence : "+diff);
		 
			
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
