

public class MatchingPercent {

	 public static int compareStringsPercentage(String s1, String s2) {
	        int percentage = 0;

	        int total = 0;
	        int fullMatch = 0;
	        // Check for each character at same location
	        total += charMatch(s1, s2);
	        fullMatch += charMatch(s1, s1);

	        // Calc percentage
	        percentage = (int) Math.round(total / (fullMatch / 100.0));
	        return percentage;
	    }

	    /**
	     * Check how many characters of string1 are in the same location as string2
	     * @param s1
	     * @param s2
	     * @return
	     */
	    private static int charMatch(String s1, String s2) {
	        char[] as1 = s1.toCharArray();
	        char[] as2 = s2.toCharArray();
	        int match = 0;
	        for (int i = 0; i < as1.length; i++) {
	            char c = as1[i];
	            if (i < as2.length) {
	                if (as2[i] == c) {
	                    match++;
	                }
	            }

	        }
	        return match;
	    }
	}

