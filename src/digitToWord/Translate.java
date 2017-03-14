package digitToWord;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import digitToWord.DigitTree.TreeNode;

public class Translate {
    // map from digits to words, e.g key:3278 value:FAST
    public Map<String, String> dict = new HashMap<String, String>();
    
    // list of all the words expressed as digits
    public List<String> digits = new ArrayList<>();
    
    // map from letter to digit, ABC-2, DEF-3...
    private char[] letterToDigit = {'2','2','2','3','3','3','4','4','4','5','5','5','6','6','6','7','7','7','7','8','8','8','9','9','9','9'};
    
    DigitTree digitTree;
    
    public void readDict(){
        // read dictionary from external file
        File file = new File("dict");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            StringBuilder number = new StringBuilder();
            while((line = br.readLine()) != null){
                for(int i = 0; i<line.length(); i++)
                    number.append(letterToDigit[line.charAt(i)-'A']);
                dict.put(number.toString(), line);
                digits.add(number.toString());
                number.setLength(0);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // build digitTree
        digitTree = new DigitTree(digits);
    }
    
    public ArrayList<ArrayList<String>> transToWords(String number){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        TreeNode current = digitTree.root;
        List<Integer> indexes = new ArrayList<>();
        int lastIndex = 0;
        for(int i = 0; i < number.length(); i++){
            if((current = current.getChild(number.charAt(i))) != null){
                if(current.end && (i-lastIndex) >= 2){
                    indexes.add(i);
                    lastIndex = i;
                    current = digitTree.root;
                }
            }
            else {
                break;
            }
        }
        if(current != null && (current == digitTree.root || current.children.isEmpty())){
            if(indexes.size() == 1){
                result.add(new ArrayList<>());
                result.get(0).add(dict.get(number));
            }
            else{
                result.add(new ArrayList<>());
                lastIndex = 0;
                for(int index:indexes){
                    result.get(0).add(dict.get(number.substring(lastIndex, index + 1)));
                    lastIndex = index + 1;
                }
                if(dict.containsKey(number)){
                    result.add(new ArrayList<>());
                    result.get(1).add(dict.get(number));    
                }
            }
        }
        return result;
    }
}
