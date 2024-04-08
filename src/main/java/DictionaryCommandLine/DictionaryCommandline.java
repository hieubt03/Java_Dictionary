package DictionaryCommandLine;

import java.util.Scanner;
import java.io.IOException;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;
    public DictionaryCommandline() {
        this.dictionaryManagement = new DictionaryManagement();
    }

    public DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    public void showAllWords() {
        System.out.println("NO\t " + "| ENGLISH\t\t\t\t" + "| VIETNAMESE");
        for (int i = 0; i < dictionaryManagement.getDictionary().getWordsList().size(); i++) {
            int j = i + 1;
            System.out.println(String.format("%-4s ", j) +
                    dictionaryManagement.getDictionary().showWordAt(i));
        }
    }

    public void dictionayBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }
    public void dictionaryAdvanced() throws IOException {

        Scanner scanner = new Scanner(System.in);
        int selection;
        while (true) {
            System.out.println("0: Exit");
            System.out.println("1: Add");
            System.out.println("2: Remove");
            System.out.println("3: Update");
            System.out.println("4: Display");
            System.out.println("5: Lookup");
            System.out.println("6: Search");
            System.out.println("7: Game");
            System.out.println("8: Import from file");
            System.out.println("9: Export file");
            System.out.println("Your choice: ");

            selection = scanner.nextInt();

            switch (selection) {
                case 0:
                    break;
                case 1:
                    dictionaryManagement.insertFromCommandline();
                    break;
                case 2:
                    dictionaryManagement.removeWordFromCommandline();
                    break;
                case 3:
                    dictionaryManagement.changeWordFromCommandline();
                    break;
                case 4:
                    dictionaryManagement.insertFromFile();
                    showAllWords();
                    break;
                case 5:
                    dictionaryManagement.dictionaryLookup();
                    break;
                case 6:
                    dictionarySearcher();
                    break;
                case 7:
                    System.out.println("Enter Game");
                    break;
                case 8:
                    dictionaryManagement.insertFromFile();
                    break;
                case 9:
                    dictionaryManagement.dictionaryExportToFile();
                    break;
            }
        }
    }

    public void dictionarySearcher() {
        System.out.println("Enter word:");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        for (int i = 0; i < dictionaryManagement.getDictionary().getWordsList().size(); i++) {
            String wordMatch = dictionaryManagement.getDictionary().getWordsList().get(i).getWord_target();
            if (wordMatch.indexOf(word) == 0) {
                System.out.println(wordMatch + ",");
            }
        }
    }
    public static void main(String[] args) {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.dictionaryManagement.insertFromFile();
        try {
            dictionaryCommandline.dictionaryAdvanced();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
