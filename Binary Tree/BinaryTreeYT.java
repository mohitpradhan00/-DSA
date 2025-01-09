import java.util.*;

public class BinaryTreeYT {
    static class  Node {
        int data;
        Node left;
        Node right;
    
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        } 
    }

    static class BinaryTree {
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            
            return newNode;
        }
    }
    
    // Preorder Traversal
    public static void preorder(Node root){   // complexity = O(n)
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }
    
    // Inorder Traversal
    public static void inorder(Node root){   // complexity = O(n)
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
   
    // PostOrder Traversal
    public static void postorder(Node root){   // complexity = O(n)
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // Level-Order Traversal
    public static void levelorder(Node root){   // complexity = O(n)
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node currNode = q.remove();
            if(currNode == null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }else{
                System.out.print(currNode.data+ " ");
                if(currNode.left != null){
                    q.add(currNode.left);
                }
                if(currNode.right != null){
                    q.add(currNode.right);
                }
                
            }
        }
    }
    
    // Count Nodes
    public static int countOfNodes(Node root){
        if(root == null) return 0;
        int leftNodes = countOfNodes(root.left);
        int rightNodes = countOfNodes(root.right);

        return leftNodes+rightNodes+1;
    }

    // Sum Nodes
    public static int sumOfNodes(Node root){
        if(root == null) return 0;
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);

        return leftSum + rightSum + root.data;

    }

    //Height of Tree
    public static int heightOfTree(Node root){
        if(root == null) return 0;
        int leftHeight = heightOfTree(root.left);
        int rightHeight = heightOfTree(root.right);

        return Math.max(leftHeight,rightHeight)+1;
    }

    //Diameter of tree -- Number of Nodes in the Longest path between any 2 nodes
    //---------------- APPROACH 1 -----------------------------------------
    // complexity - O(n2)
    public static int diameterOfTree1(Node root){
        if(root == null) return 0;
        int diameter1 = diameterOfTree1(root.left);
        int diameter2 = diameterOfTree1(root.right);
        int diameter3 = heightOfTree(root.left)+heightOfTree(root.right)+1;

        return Math.max(diameter3, Math.max(diameter2, diameter1)) ;
    }

    //---------------- APPROACH 2 -----------------------------------------
    static class TreeInfo {
        int height;
        int diameter;
   
        TreeInfo(int height,int diameter){
           this.height = height;
           this.diameter = diameter;
        }
    }
    // complexity - O(n)
    public static TreeInfo diameterOfTree2(Node root){
        if(root == null) return new TreeInfo(0,0);
        TreeInfo left = diameterOfTree2(root.left);
        TreeInfo right = diameterOfTree2(root.right);
        
        int myHeight = Math.max(left.height , right.height) + 1;

        int diam1 = left.diameter;
        int diam2 = right.diameter;
        int diam3 = left.height + right.height +1;

        int mydiam = Math.max(Math.max(diam2, diam1),diam3);

        TreeInfo myInfo = new TreeInfo(myHeight, mydiam);
        return myInfo;
        
    }

    
    public static void main(String[] args) {
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        System.out.print("root" + ":" + " ");
        System.out.println(root.data);
        System.out.println("Preorder Traversal");
        preorder(root);
        System.out.println("");
        System.out.println("Inorder Traversal");
        inorder(root);
        System.out.println("");
        System.out.println("Postorder Traversal");
        postorder(root);
        System.out.println("");
        System.out.println("Levelorder Traversal");
        levelorder(root);
        System.out.println("Count of Nodes: "+ countOfNodes(root));
        System.out.println("Sum of Nodes: "+ sumOfNodes(root));
        System.out.println("Sum of Nodes: "+ heightOfTree(root));
        System.out.println("Diameter of Nodes: "+ diameterOfTree1(root));
        System.out.println("Diameter of Nodes: "+ diameterOfTree2(root).diameter);
    }
}
