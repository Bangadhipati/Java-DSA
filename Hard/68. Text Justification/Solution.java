import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>((words.length + 1) / 2);
        int i = 0, n = words.length;
        char[] spaces = new char[maxWidth];
        Arrays.fill(spaces, ' ');

        while (i < n) {
            int len = words[i].length();
            int j = i + 1;
            while (j < n && len + 1 + words[j].length() <= maxWidth) {
                len += 1 + words[j].length();
                j++;
            }

            char[] line = new char[maxWidth];
            Arrays.fill(line, ' '); // fill all spaces first

            if (j == n || j - i == 1) { // last line or single word
                int pos = 0;
                for (int k = i; k < j; k++) {
                    String w = words[k];
                    for (int c = 0; c < w.length(); c++) {
                        line[pos++] = w.charAt(c);
                    }
                    if (k < j - 1) line[pos++] = ' ';
                }
            } else { // fully justified
                int wordsLen = 0;
                for (int k = i; k < j; k++) wordsLen += words[k].length();
                int spacesNeeded = maxWidth - wordsLen;
                int gaps = j - i - 1;
                int spaceEach = spacesNeeded / gaps;
                int extra = spacesNeeded % gaps;

                int pos = 0;
                for (int k = i; k < j; k++) {
                    String w = words[k];
                    for (int c = 0; c < w.length(); c++) {
                        line[pos++] = w.charAt(c);
                    }
                    if (k < j - 1) {
                        int spaceCount = spaceEach + (extra-- > 0 ? 1 : 0);
                        pos += spaceCount; // already spaces in array
                    }
                }
            }
            res.add(new String(line));
            i = j;
        }
        return res;
    }
}
