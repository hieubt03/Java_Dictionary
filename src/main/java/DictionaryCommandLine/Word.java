package DictionaryCommandLine;

public class Word {
    private String word_target;
    private String word_explain;
    private String word_pronounce;

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_pronounce() {
        return word_pronounce;
    }

    public void setWord_pronounce(String word_pronounce) {
        this.word_pronounce = word_pronounce;
    }

    public Word() {
        this.word_target = null;
        this.word_explain = null;
        this.word_pronounce = null;
    }

    public Word(String word_target,
                String word_explain, String word_pronounce) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.word_pronounce = word_pronounce;
    }
}
