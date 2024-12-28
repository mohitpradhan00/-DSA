// Find the Longest SubString in the words such that every prefix of it is also in words.
// words[] = {"a","banana","app","appl","ap","apply","apple"};
// ans = "apple"


public class LongestWordWithAllPrefixesProblem {
    static class Node {
        Node []children = new Node[26];
        boolean endOfWord;
    
        public Node(){
            for(int i = 0; i<26; i++){
                children[i]=null;
            }
        }        
    }
    
    public static Node root = new Node();
    
    public static void insert(String word){
        Node curr = root;
        for(int i = 0; i<word.length();i++){
            int idx = word.charAt(i)-'a';
            if(curr.children[idx]==null){
                curr.children[idx]=new Node();
            }
            if(i==word.length()-1){
                curr.children[idx].endOfWord = true;
            }
            curr = curr.children[idx];
        }
    }
    
    public static String ans = "";

    public static void longestWord(Node root, StringBuilder curr) {


       for(int i=0; i<26; i++) {
           if(root.children[i] != null && root.children[i].endOfWord == true) {
               curr.append((char)(i+'a'));
               if(curr.length() > ans.length()) {
                   ans = curr.toString();
               }
               longestWord(root.children[i], curr);
               curr.deleteCharAt(curr.length()-1);
           }
       }
   }



    public static void main(String args[]) {
       String words[] = {"a","banana","app","appl","ap","apply","apple"};
       for(String word: words){
        insert(word);
       }
       longestWord(root, new StringBuilder(""));
       System.out.println(ans);
       
    }
}
