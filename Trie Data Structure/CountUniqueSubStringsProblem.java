// Given a String of length n of lowercase alphabet characters, we need to count total no. of 
// distinct substring of the strings.
// str = "ababa"
// ans = 10

// -------------- APPROACH --------------------------
//  1.) find all suffix of sting.
//  2.) create a trie from suffix.
//  3.) count nodes of trie.   total node of trie = count of unique prefix's


public class CountUniqueSubStringsProblem {
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


    public static int countNode(Node root){
        if(root == null){
            return 0;
        }
        int count = 0;
        for(int i=0; i<26 ; i++){
            if(root.children[i] != null){
                count+= countNode(root.children[i]);
            }
        }
        return count+1;
    }

    public static void main(String args[]) {
       String word = "ababa";
       for(int i=0; i<word.length();i++){
        String suffix = word.substring(i);
        // System.out.println(suffix);
        insert(suffix);
       }
       System.out.println(countNode(root));
    }
}
