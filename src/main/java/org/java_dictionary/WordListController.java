package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;





public class WordListController {
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @FXML
    private ListView<String> wordList;

    @FXML
    private Label wordExplain;

    @FXML
    private TextField searchbar;

    @FXML
    private void initialize() {
        // Gán ObservableList làm mục của ListView
        dictionaryManagement.insertFromFile();
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