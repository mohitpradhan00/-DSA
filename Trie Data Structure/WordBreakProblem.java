//  Given An Input String and a Dictionary of Words, find out if the input String 
//  Can be Broken into a space-seperated sequence of dictionary words.
//  Words[] = {i,like,sam,samsung,mobile,ice}
//  key= "ilikesamsung"
//  output = true

public class WordBreakProblem {
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

    public static boolean wordBreak(String key){
        int len = key.length();
        if(len == 0){
            return true;
        }
        for(int i=1; i<=len ; i++){
            if(search(key.substring(0,i)) && wordBreak(key.substring(i))){
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
       String words[] = {"i","like","sam","samsung","mobile","ice"};
       String key= "ilikesamsung";

       for(String word : words){
        insert(word);
       }

       System.out.println(wordBreak(key));
    }
}
