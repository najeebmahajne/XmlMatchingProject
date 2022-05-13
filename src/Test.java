import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.darkprograms.speech.translator.GoogleTranslate;

public class Test {

	public static void main(String[] args) {
		/*HashMap<String, String> dictionary=new HashMap<String, String>();
		String str="על דעת עצמו";
		dictionary.put(str, "من تلقاء نفسه");
		dictionary.put("מ", "من");
		dictionary.put("עצמו", "نفسه");
		HashMap<String, String> stringDictionary=dictionaryMatchingAlgorithm(dictionary,str);
		print(stringDictionary);
		System.out.println(
	            "Entry with highest key: "
	            + getMaxEntryInMapBasedOnKey(stringDictionary));*/
		try {
			String OrginalWord="סעיף";
			
			String translated=GoogleTranslate.translate("ar", OrginalWord);
			String translated1=GoogleTranslate.translate("he", translated);
			System.out.println("ORGINAL WORD     - "+OrginalWord);
			System.out.println("HEBREW TO ARABIC - "+translated);
			System.out.println("ARABIC TO HEBREW - "+translated1);
			System.out.println("*****************");
			String ArabicWord="قسم";
			String translated2=GoogleTranslate.translate("he", ArabicWord);
			String translated3=GoogleTranslate.translate("ar", translated2);
			System.out.println("ORGINAL WORD     - "+ArabicWord);
			System.out.println("ARABIC TO HEBREW - "+translated2);
			System.out.println("HEBREW TO ARABIC - "+translated3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public static HashMap<String,String> dictionaryMatchingAlgorithm(HashMap<String, String> dictionary,String xmlText) 
	{
		HashMap<String, String> results=new HashMap<String, String>();
		TrainedTokenizer tok=new TrainedTokenizer();
		String[] tokens;
		try {
			tokens = tok.tokenize(xmlText);
			for(int i=0;i<tokens.length;i++)
			{
				if(dictionary.containsKey(tokens[i])) 
				{
					results.put(tokens[i], dictionary.get(tokens[i]));
				}	
			}
			//2-gram tokenize
			for(int i=0;i<tokens.length-1;i++)
			{
				if(tokens.length>=2)
				{
					String str=tokens[i]+" "+tokens[i+1];
					if(dictionary.containsKey(str)) 
					{
						results.put(str, dictionary.get(tokens[i]));
					}
					
				}	
			}
			//3-gram tokenize
			for(int i=0;i<tokens.length-2;i++)
			{
				if(tokens.length>=3)
				{
					String str=tokens[i]+" "+tokens[i+1]+tokens[i+2];
					if(dictionary.containsKey(str)) 
					{
						results.put(str, dictionary.get(tokens[i]));
					}
					
				}	
			}
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
}
