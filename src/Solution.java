import java.util.Scanner;

import digitToWord.Translate;

public class Solution {
    public static void main(String[] args){
        Translate translate = new Translate();
        translate.readDict();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a phone number, input q to stop:");
        String number = scanner.nextLine();
        // repeat to return the translations
        while(!number.equals("q")){
            System.out.println(translate.transToWords(number));
            number = scanner.nextLine();
        }
        scanner.close();
    }
}
