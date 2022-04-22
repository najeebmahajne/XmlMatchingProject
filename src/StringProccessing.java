import java.io.FileNotFoundException;
import java.io.IOException;

import com.darkprograms.speech.translator.GoogleTranslate;

public class StringProccessing {

	String str;
	TrainedTokenizer tokenizer;
	String[] tokens;
	String[] translatedTokens;
	String TranslatedSentence1="";
	public StringProccessing(String str)
	{
		this.str=str;
		TrainedTokenizer tokenizer=new TrainedTokenizer();
		try {
			tokens=tokenizer.tokenize(this.str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void tokensTranslation() 
	{
		if(this.tokens!=null) 
		{
			translatedTokens=new String[this.tokens.length];
			
          	  
          	  
          	  for(int i=0;i<this.tokens.length;i++) {
          		try {
					translatedTokens[i]=GoogleTranslate.translate("ar", this.tokens[i]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
				}
          		if(translatedTokens[i]!=null) {
          		 TranslatedSentence1+=translatedTokens[i];
          		 TranslatedSentence1+=" ";}
          		 
          	  }System.out.println(TranslatedSentence1);
			}
		}
	}

