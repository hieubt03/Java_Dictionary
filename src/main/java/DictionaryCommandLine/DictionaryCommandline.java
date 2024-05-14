package DictionaryCommandLine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;
    private List<String> questions = new ArrayList<>();
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

//    public void dictionayBasic() {
//        dictionaryManagement.insertFromCommandline();
//        showAllWords();
//    }
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
                    dictionaryGame();
                    break;
                case 8:
                    dictionaryManagement.insertFromFile();
                    break;
                case 9:
                    dictionaryManagement.dictionaryExportToFile("dictionaries.txt");
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

    public void dictionaryGame() {
        String filePath = "Game.txt";
        int numberOfQuestions = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                int countInLine = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '@') {
                        countInLine++;
                    }
                }

                numberOfQuestions += countInLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Random random = new Random();
            int randomQuestionNumber = random.nextInt(numberOfQuestions) + 1;
            int questionLine = (randomQuestionNumber - 1) * 9 + 2;
            int currentLine = 1;
            while (currentLine < questionLine && reader.readLine() != null) {
                currentLine++;
            }

            for (int i = 0; i < 6; i++) {
                line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                } else {
                    break;
                }
            }
            String correctAnswer = reader.readLine();
            //code tiếp ở đây
            Scanner scanner = new Scanner(System.in);
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(correctAnswer.trim())) {
                System.out.println("Chính xác! Câu trả lời đúng.");
            } else {
                System.out.println("Sai! Câu trả lời không đúng.");
            }

        } catch (IOException e) {
            e.printStackTrace();
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
