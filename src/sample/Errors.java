package sample;

public class Errors {


    public boolean onlyLetter(String word) {

        boolean check = true;
        if (!word.isEmpty()) {
            if (word.charAt(0) > 64 && word.charAt(0) < 91) {
                for (int i = 1; i < word.length(); i++) {
                    if (!(word.charAt(i) == 32 || (word.charAt(i) > 96 && word.charAt(i) < 124))) {
                        check = false;
                    }
                }
            } else {
                return false;
            }
            return check;
        }
        else{
            return false;
        }
    }
}
