package digitToWord;
import java.util.ArrayList;
import java.util.List;

public class DigitTree {
    TreeNode root; // root of the tree, an empty node
    public DigitTree(List<String> digitWords) {
        // TODO Auto-generated constructor stub
        root = new TreeNode("root");
        TreeNode current = root;
        for (String word:digitWords) {
            current = root;
            for(int i = 0; i < word.length(); i++){
                TreeNode node = new TreeNode(word.charAt(i));
                current = current.addChild(node); // add char to the tree
            }
            current.end = true; // end of the word
        }
    }
    public class TreeNode{
        char value;
        boolean end; // is the end of a word or not
        List<TreeNode> children;
        TreeNode parent;
        public TreeNode(char v) {
            // TODO Auto-generated constructor stub
            value = v;
            end = false;
            children = new ArrayList<>();
        }
        public TreeNode(String root){
            value = '0';
            end = false;
            children = new ArrayList<>();
        }
        public TreeNode addChild(TreeNode child){
            // if char already exist in the tree, return the node, otherwise add the child
            if(!children.contains(child)){
                this.children.add(child);
                child.parent = this;
                return child;
            }
            else{
                for(TreeNode n:children){
                    if(n.equals(child))
                        return n;
                }
            }
            return null;
        }
        public TreeNode getChild(char value){
            // return the child if value exists, otherwise return null
            for(TreeNode child:children){
                if(child.value == value)
                    return child;
            }
            return null;
        }
        @Override
        public boolean equals(Object obj){
            if(obj instanceof TreeNode){
                TreeNode n = (TreeNode) obj;
                if(n.value == value)
                    return true;
            }
            return false;
        }
    }
}
