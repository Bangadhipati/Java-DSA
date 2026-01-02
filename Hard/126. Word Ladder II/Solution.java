import java.util.*;

class Solution {
    // This map stores "Word -> List of Parents"
    // e.g., "dog" -> ["dot", "log"]
    Map<String, List<String>> adj = new HashMap<>();
    List<List<String>> results = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return new ArrayList<>(); // Edge case

        // BFS Setup
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);
        
        boolean found = false;
        
        // Remove start word from dict so we don't visit it again
        dict.remove(beginWord);

        // PHASE 1: BFS (Building the graph)
        while (!currentLevel.isEmpty() && !found) {
            // We use a new set for the next level to process all siblings simultaneously
            Set<String> nextLevel = new HashSet<>();
            
            // We remove all words in the current level from the dictionary
            // to prevent loops or longer paths to the same word.
            dict.removeAll(currentLevel);

            for (String word : currentLevel) {
                char[] chars = word.toCharArray();
                
                // Try changing every letter (h-o-t -> a-o-t, b-o-t...)
                for (int i = 0; i < chars.length; i++) {
                    char originalChar = chars[i];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        chars[i] = c;
                        String newWord = new String(chars);
                        
                        // If this new word is in our dictionary
                        if (dict.contains(newWord)) {
                            // If we found the end, mark it, but finish the level!
                            if (newWord.equals(endWord)) {
                                found = true;
                            }
                            
                            // Add to next level
                            nextLevel.add(newWord);
                            
                            // Map the relationship: Child -> Parent
                            // "newWord" came from "word"
                            adj.putIfAbsent(newWord, new ArrayList<>());
                            adj.get(newWord).add(word);
                        }
                    }
                    // Restore char for next iteration
                    chars[i] = originalChar; 
                }
            }
            currentLevel = nextLevel;
        }

        // PHASE 2: DFS (Backtracking to build paths)
        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            backtrack(endWord, beginWord, path);
        }

        return results;
    }

    // A recursive function to walk backward from End to Start
    private void backtrack(String word, String start, List<String> path) {
        if (word.equals(start)) {
            // We reached the start! Reverse the path to get Start -> End
            List<String> finalPath = new ArrayList<>(path);
            Collections.reverse(finalPath);
            results.add(finalPath);
            return;
        }

        // Look at who created this word (its parents)
        List<String> parents = adj.get(word);
        if (parents != null) {
            for (String parent : parents) {
                path.add(parent);
                backtrack(parent, start, path);
                path.remove(path.size() - 1); // Backtrack (undo step)
            }
        }
    }
}