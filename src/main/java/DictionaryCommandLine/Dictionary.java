package DictionaryCommandLine;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<Word> wordsList = new ArrayList<Word>();
    public List<Word> getWordsList() {
        return wordsList;
    }

    public String showWordAt(int i) {
        return String.format("| %-20s | %-20s",
                wordsList.get(i).getWord_target(),
                wordsList.get(i).getWord_explain());
    }
}
