package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class SearchController  {
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private FreeTTSVoice voice = new FreeTTSVoice("kevin16");
    @FXML
    private AnchorPane searchPane;
    @FXML
    private ListView<String> wordList;
    @FXML
    private Label wordExplain;
    @FXML
    private TextField searchbar;
    @FXML
    Button voicebutton;
    @FXML
    Button deleteword;
    @FXML
    Button modifiedword;

    public void initData(DictionaryManagement dic) {
        this.dictionaryManagement = dic;
        noWordSelected();
        for (int i = 0; i < dictionaryManagement.getDictionary().getWordsList().size(); i++) {
            wordList.getItems().add(dictionaryManagement.getDictionary().getWordsList().get(i).getWord_target());
        }
    }
    void noWordSelected() {
        voicebutton.setVisible(false);
        deleteword.setVisible(false);
        modifiedword.setVisible(false);
    }

    void hasWordSelected() {
        voicebutton.setVisible(true);
        deleteword.setVisible(true);
        modifiedword.setVisible(true);
    }

    @FXML
    void OnWordListClicked(MouseEvent event) {
        hasWordSelected();
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
            String selectedItem = wordList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                for (int i = 0; i < dictionaryManagement.getDictionary().getWordsList().size(); i++) {
                    if (dictionaryManagement.getDictionary().getWordsList().get(i).getWord_target().equals(selectedItem)) {
                        wordExplain.setText(
                                dictionaryManagement.getDictionary().getWordsList().get(i).getWord_target() + " - " +
                                dictionaryManagement.getDictionary().getWordsList().get(i).getWord_pronounce() + "\n" +
                                dictionaryManagement.getDictionary().getWordsList().get(i).getWord_explain());
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

    @FXML
    public void OnGGVoiceClicked() {
        String target = wordList.getSelectionModel().getSelectedItem();
        String targetRes = target.replace("-", "");
        voice.speak(targetRes);
    }

    @FXML
    public void deleteWord(ActionEvent e) throws IOException {
        String currentWord = wordList.getSelectionModel().getSelectedItem();
        boolean check = dictionaryManagement.removeCurrentWord(currentWord);
        wordList.getItems().remove(wordList.getSelectionModel().getSelectedItem());
        if (check) {
            showAlert("Delete Success!");
            try {
                dictionaryManagement.dictionaryExportToFile("TuDien.txt");
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }

    @FXML
    public void modifiedWord() throws IOException{
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        dialog.getDialogPane().setContent(scrollPane);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                return textArea.getText();
            }
            return null;
        });

        String modifiedWord = wordList.getSelectionModel().getSelectedItem();
        dialog.setTitle("Edit Word");
        dialog.setHeaderText("Edit Word: " + modifiedWord);

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newExplain -> {
            if (newExplain.length() == 0) {
                showAlert("New explain is not valid");
                return;
            }
            boolean check = dictionaryManagement.changeWordExplain(newExplain, modifiedWord);
            wordExplain.setText(newExplain);
            showAlert("Change success!");
            if (check) {
                try {
                    dictionaryManagement.dictionaryExportToFile("TuDien.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showAlert(String newAlert)  {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notify");
        alert.setContentText(newAlert);
        alert.showAndWait();
    }
}