package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SearchController  {
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    @FXML
    private AnchorPane searchPane;

    @FXML
    private ListView<String> wordList;

    @FXML
    private Label wordExplain;

    @FXML
    private TextField searchbar;

    public void initData(DictionaryManagement dic) {
        this.dictionaryManagement = dic;
        for (int i = 0; i < dictionaryManagement.getDictionary().getWordsList().size(); i++) {
            wordList.getItems().add(dictionaryManagement.getDictionary().getWordsList().get(i).getWord_target());
        }
    }

    @FXML
    void OnWordListClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {

            String selectedItem = wordList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                for (int i = 0; i < dictionaryManagement.getDictionary().getWordsList().size(); i++) {
                    if (dictionaryManagement.getDictionary().getWordsList().get(i).getWord_target().equals(selectedItem)) {
                        wordExplain.setText(dictionaryManagement.getDictionary().getWordsList().get(i).getWord_explain());

                    }
                }
            }
        }
    }

    @FXML
    void OnSearchbarTyped(KeyEvent event) {
        wordList.getItems().clear();
        String searchWord = searchbar.getText();
        for (int i = 0; i < dictionaryManagement.getDictionary().getWordsList().size(); i++) {
            if (dictionaryManagement.getDictionary().getWordsList().get(i).getWord_target().contains(searchWord.toLowerCase())){
                wordList.getItems().add(dictionaryManagement.getDictionary().getWordsList().get(i).getWord_target());
            }
        }
    }


}