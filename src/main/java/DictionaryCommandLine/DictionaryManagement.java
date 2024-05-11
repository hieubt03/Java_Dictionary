package DictionaryCommandLine;

import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary;
    public DictionaryManagement() {
        this.dictionary = new Dictionary();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        System.out.print("Enter number of word: ");
        int numberOfWord = scanner.nextInt();
        scanner.nextLine();
        String word_target;
        String word_explain;
        for (int i = 0; i < numberOfWord; i++) {
            System.out.print("Enter word: ");
            word_target = scanner.nextLine();
            System.out.print("Translate to: ");
            word_explain = scanner.nextLine();
            Word word = new Word(word_target, word_explain, "");
            dictionary.getWordsList().add(word);
            String string = word_target + "\t" + word_explain;
            try {
                fileWriter = new FileWriter("dictionaries.txt", true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(string);
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();

            } catch (Exception e) {
                System.out.println("File Error");
            }
        }
    }
    public boolean wordExist(String wordTarget) {
        boolean isExist = false;
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWord_target().equals(wordTarget)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }
    public void insertFromFile() {
        File file = new File("dictionaries.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String string;
        String eng;
        String viet;
        int numberOfTab;
        while (scanner.hasNext()) {
            string = scanner.nextLine();
            numberOfTab = string.indexOf('\t');
            eng = string.substring(0,numberOfTab);
            viet = string.substring(numberOfTab + 1);
            if (!wordExist(eng)) {
                Word word = new Word(eng, viet, "");
                dictionary.getWordsList().add(word);
            }
        }
    }

    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter word to translate");
        String wordTarget = scanner.nextLine();
        boolean checked = false;
        for (int i = 0; i< dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWord_target().equals(wordTarget)) {
                System.out.println(dictionary.showWordAt(i));
                checked = true;
            }
        }
        if (!checked) {
            System.out.println("Can't translate");
        }
    }

    public void removeWordFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        boolean checked = false;
        System.out.println("Enter english word: ");
        String wordTarget = scanner.nextLine();
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWord_target().equals(wordTarget)) {
                dictionary.getWordsList().remove(i);
                checked = true;
            }
        }
        if (!checked) {
            System.out.println("Word not found!");
        }
    }

    public void changeWordFromCommandline() {
        System.out.println("Enter word to change: ");
        Scanner scanner = new Scanner(System.in);
        String wordTarget = scanner.nextLine();
        boolean checked = false;
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWord_target().equals(wordTarget)) {
                int selection;
                checked = true;
                do {
                    System.out.println("1: Change word");
                    System.out.println("2: Change mean");
                    selection = scanner.nextInt();
                } while (selection != 1 && selection != 2);
                scanner.nextLine();
                if (selection == 1) {
                    System.out.println("Enter new word: ");
                    String newWord = scanner.nextLine();
                    dictionary.getWordsList().get(i).setWord_target(newWord);
                } else if (selection == 2) {
                    System.out.println("Enter new mean");
                    String newMean = scanner.nextLine();
                    dictionary.getWordsList().get(i).setWord_explain(newMean);
                }
            }
        }
        if (!checked) {
            System.out.println("Word not found!");
        }
    }
    public void dictionaryExportToFile(String pathname) throws IOException {
        File file = new File(pathname);
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for(int i = 0; i < dictionary.getWordsList().size(); i++) {
            String wordLabel = "\n" + "@" + dictionary.getWordsList().get(i).getWord_target() + " " + dictionary.getWordsList().get(i).getWord_pronounce() + "\n";
            outputStreamWriter.write(wordLabel);
            String wordExplain = dictionary.getWordsList().get(i).getWord_explain() + "\n";
            outputStreamWriter.write(wordExplain);
        }
        outputStreamWriter.flush();
    }

    //UI version
    public void insertTxt() {
        String line;
        boolean check = false;
        String target = "";
        String explain = "";
        String pronounce = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader("TuDien.txt"));

            while((line = reader.readLine()) != null){
                if(line.length() == 0 && target.length() != 0) {
                    dictionary.getWordsList().add(new Word(target, explain, pronounce));
                    target = "";
                    explain = "";
                    pronounce = "";
                    check = false;
                }
                if(check && line.length() != 0) {
                    explain = explain.concat(line + "\n");
                }
                if(line.length() > 1 && line.charAt(0) == '@') {
                    target = getWord(line);
                    pronounce = getPronounce(line);
                    check = true;
                }
            }
            if (target.length() != 0) {
                dictionary.getWordsList().add(new Word(target, explain, pronounce));
            }
        }catch (Exception e){
            System.out.println("Can't read file " + e);
        }
    }

    private String getWord(String line) {
        if(line.indexOf('/') == -1) {
            return line;
        }
        return line.substring(1, line.indexOf('/') - 1);
    }

    private String getPronounce(String line) {
        if(line.indexOf('/') == -1) {
            return "";
        }
        return line.substring(line.indexOf('/'));
    }

    public boolean removeCurrentWord(String wordRemove) {
        if(wordRemove == null) return false;
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWord_target().equals(wordRemove)) {
                dictionary.getWordsList().remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean changeWordExplain (String newExplain, String target) {
        for(int i = 0; i < dictionary.getWordsList().size(); i++) {
            if( dictionary.getWordsList().get(i).getWord_target().equals(target)) {
                dictionary.getWordsList().get(i).setWord_explain(newExplain);
                return true;
            }
        }
        return false;
    }
}
