// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        boolean validLink = true;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(nextOpenBracket == -1 || nextCloseBracket == -1 || openParen == -1 || closeParen == -1){
                return toReturn;
            }
            if((nextOpenBracket != 0  && markdown.charAt(nextOpenBracket - 1) == '!')
                || (openParen != nextCloseBracket + 1)){
                validLink = false;
            }
            if(markdown.substring(openParen, closeParen).contains(" ")){
                validLink = false;
            }
            String returnString = markdown.substring(openParen + 1, closeParen);
            if(markdown.charAt(openParen + 1) == '\n'){
                returnString = markdown.substring(openParen + 2, closeParen);
            }
            if(markdown.charAt(closeParen - 1) == '\n'){
                returnString = returnString.substring(0, returnString.length() - 1);
            }
            if(validLink) {
                toReturn.add(returnString);
            }
            currentIndex = closeParen + 1;
            //System.out.println(currentIndex);
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}