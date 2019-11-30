package br.ufpb.dcx.appalpha.control.util;

public class TextUtil {
    private static TextUtil instance;

    private TextUtil(){}

    public static TextUtil getInstance(){
        if(instance == null){
            instance = new TextUtil();
        }

        return instance;
    }

    public String getUnderscoreOfThis(String word) {
        StringBuilder s = new StringBuilder(word);
        StringBuilder newS = new StringBuilder("");

        for (int i = 0; i < s.length(); i++) {
            newS.append("_");
        }

        return newS.toString();
    }
}
