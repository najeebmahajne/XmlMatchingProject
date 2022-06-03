import java.io.FileNotFoundException;
import java.io.IOException;

import com.darkprograms.speech.translator.GoogleTranslate;

public class XMLTagTextProccessing {

	String str;
	TrainedTokenizer tokenizer;
	String[] tokens;
	String[] translatedTokens;
	String[] stemmedTokens;
	String TranslatedSentence1="";
	String sentence="";
	Dictionary defenitions=new Dictionary();
	public XMLTagTextProccessing(String str)
	{
		this.str=str;
		/*if(this.str.contains("בסעיף קטן"))
			this.str=this.str.replaceAll("בסעיף קטן", "בסעיףקטן");*/
		this.str=this.str.replaceAll("־", " ");
		this.str=this.str.replaceAll("-", " ");
		this.str=this.str.replaceAll(",", "");
		this.str=this.str.replaceAll(",", "");
		this.str=this.str.replaceAll("'", "");
		this.str=this.str.replaceAll(";", "");
		this.str=this.str.replaceAll("\"", "");
		this.str=this.str.replaceAll("\\u0022\\s+", "");
		
		//this.str=this.str.replaceAll("\\u0028\\u0028+", "");
		//this.str=this.str.replaceAll("\\u0029\\u0029+", "");
		
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
	public void sentencePrint() 
	{
		for(int i=0;i<this.tokens.length;i++) 
		{
			if(i==0) {
				if(tokens[i]!=null)
					sentence+=tokens[i];
			      
			}else 
			{
				if(tokens[i]!=null)
					sentence+=" "+tokens[i];
				
			}
		}
		
		System.out.println("Hebrew XML Sentence : "+sentence);
	}
	public void tokensTranslation() 
	{
		if(this.tokens!=null) 
		{
			translatedTokens=new String[this.tokens.length];
			
			 
          	  
          	  for(int i=0;i<this.tokens.length;i++) {
          		try {
          			if(defenitions.containsKey(this.tokens[i])) 
          			{
          				translatedTokens[i]=defenitions.getValue(this.tokens[i]);
          			}else 
          			{
          				translatedTokens[i]=GoogleTranslate.translate("ar", this.tokens[i]);
          				
          				
          			}
					
					//stemmedTokens[i]=findStem(translatedTokens[i]);
					
          			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
				}
          		if(translatedTokens[i]!=null&&translatedTokens[i]!="Peresomoya"&&translatedTokens[i]!="\\"&&translatedTokens[i]!="/") {
          			
          		 TranslatedSentence1+=translatedTokens[i];
          		 TranslatedSentence1+=" ";}
          		
          		
          		 
          	  }//System.out.println(TranslatedSentence1);
			}
		}
	public static boolean isProbablyArabic(String s) {
	    for (int i = 0; i < s.length();) {
	        int c = s.codePointAt(i);
	        if (c >= 0x0600 && c <= 0x06E0)
	            return true;
	        i += Character.charCount(c);            
	    }
	    return false;
	  }
	
	public static boolean isProbablyHebrew(String s) {
	    for (int i = 0; i < s.length();) {
	        int c = s.codePointAt(i);
	        if (c >= 0x0580  && c <= 0x05CA)
	            return true;
	        i += Character.charCount(c);            
	    }
	    return false;
	  }
	public String findStem(String token)
	{
		String stem="";
		token=token.trim();
		String[] txtToken=token.split("\\s");
		if (txtToken.length > 1)
		{
			System.out.println("error: findStem function finds a stem for one token each time!");
            return stem;
		}
		else
		{
			if (token.length() >= 4 && token.charAt(0) == '\u0648')
			{
				token = token.substring(1);
			}
			if (token.length() >= 4 && 
			   ((token.charAt(0) == '\u0627' && token.charAt(1) == '\u0644')
			      ||(token.charAt(0) == '\u0644' && token.charAt(1) == '\u0644')))
			{
				token = token.substring(2);
			}
			
	        if (token.length() >= 5 && 
	           ((token.charAt(0) == '\u0628' && token.charAt(1) == '\u0627' && token.charAt(2) == '\u0644')
	             ||(token.charAt(0) == '\u0648' && token.charAt(1) == '\u0627' && token.charAt(2) == '\u0644')
			     ||(token.charAt(0) == '\u0643' && token.charAt(1) == '\u0627' && token.charAt(2) == '\u0644')
			     ||(token.charAt(0) == '\u0641' && token.charAt(1) == '\u0627' && token.charAt(2) == '\u0644')))
	         {
		        token = token.substring(3);
	         }
	        
	        if (token.length()>=4 && token.charAt(token.length()-2) == '\u0647' && token.charAt(token.length()-1) == '\u0627')
	         {
	        	token = token.substring(0,token.length()-2);
	         }
	        
	        if (token.length()>=4 && token.charAt(token.length()-2) == '\u0627' && token.charAt(token.length()-1) == '\u0646')
	         {
	        	token = token.substring(0,token.length()-2);
	         }
	        
	        if (token.length()>=4 && token.charAt(token.length()-2) == '\u0627' && token.charAt(token.length()-1) == '\u062a')
	         {
	        	token = token.substring(0,token.length()-2);
	         }
	        
	        if (token.length()>=4 && token.charAt(token.length()-2) == '\u0648' && token.charAt(token.length()-1) == '\u0646')
	         {
	        	token = token.substring(0,token.length()-2);
	         }
	        
	        if (token.length()>=4 && token.charAt(token.length()-2) == '\u064a' && token.charAt(token.length()-1) == '\u0646')
	         {
	        	token = token.substring(0,token.length()-2);
	         }
	        
	        if (token.length()>=4 && token.charAt(token.length()-2) == '\u064a' && token.charAt(token.length()-1) == '\u0647')
	         {
	        	token = token.substring(0,token.length()-2);
	         }
	        
	        if (token.length()>=4 && token.charAt(token.length()-2) == '\u064a' && token.charAt(token.length()-1) == '\u0629')
	         {
	        	token = token.substring(0,token.length()-2);
	         }
	        
	        if (token.length()>=3 && token.charAt(token.length()-1) == '\u0647')
	         {
	        	token = token.substring(0,token.length()-1);
	         }
	        
	        if (token.length()>=3 && token.charAt(token.length()-1) == '\u0629')
	         {
	        	token = token.substring(0,token.length()-1);
	         }
	        
	        if (token.length()>=3 && token.charAt(token.length()-1) == '\u064a')
	         {
	        	token = token.substring(0,token.length()-1);
	         }
			
	        stem=token;
			return stem;
		}
	}
	}

