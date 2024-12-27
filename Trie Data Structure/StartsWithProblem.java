//  Create a Function Boolean StartsWith(String prefix) for a trie.
//  Returns true if there is a previously inserted string word that has the prefix prefix 
//  and false otherwise.
//  words={"apple","app","mango","man","woman"}
// prefix = app   : output = true
// prefix = "moon" : output = false

public class StartsWithProblem {
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

    public static boolean search(String word){
        Node curr = root;
        for(int i = 0; i<word.length();i++){
            int idx = word.charAt(i)-'a';
            if(curr.children[idx]==null){
                return false;
            }
            if(i==word.length()-1 && curr.children[idx].endOfWord==false){
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }
    
    public static boolean startsWith(String prefix){
        Node curr = root;
        for(int i=0; i<prefix.length(); i++) {
           int idx = prefix.charAt(i)-'a';
           if(curr.children[idx] == null) {
               return false;
           }
           curr = curr.children[idx];
       }
       return true;

    }

    public static void main(String args[]) {
       String words[] = {"apple","app","mango","man","woman"};
       String prefix = "app";
       for (String word : words) {
           insert(word);
        }
        System.out.println(startsWith(prefix));
    }
}
