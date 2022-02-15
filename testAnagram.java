import java.util.*;

public class testAnagram {
    public static void generateAllAnagram(String ans, String asli, char ch, Set<String> li) {
        if (ans.length() == 0) {
            li.add(asli);
        }
        for (int i = 0; i < ans.length(); i++) {
            String temp = ans.substring(0, i) + ans.substring(i + 1);
            generateAllAnagram(temp, ans.charAt(i) + asli, ch, li);
        }
    }

    public static void main(String[] args) {
        String s = "ana";
        Set<String> li = new HashSet<String>();
        generateAllAnagram(s, "", s.charAt(0), li);
        System.out.println(li);
    }
}
