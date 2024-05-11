package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.*;

public class HangedManGameController {
    private DictionaryManagement dic;
    int numOfGuess = 6;
    int counting = 0;
    String wordTarget;
    @FXML
    private AnchorPane gamePane;
    @FXML
    Label message;
    @FXML
    TextField input;
    @FXML
    ImageView img;
    @FXML
    Button playButton;
    @FXML
    Button checkButton;
    @FXML
    private List<TextField> textFieldList = new ArrayList<>();
    Image image0 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/0.png")));
    Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/1.png")));
    Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/2.png")));
    Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/3.png")));
    Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/4.png")));
    Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/5.png")));
    Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/6.png")));
    @FXML
    TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, tf13;
    public void initData(DictionaryManagement dic) {
        this.dic = dic;
    }
    public void initialize(){
        textFieldList.add(tf1);
        textFieldList.add(tf2);
        textFieldList.add(tf3);
        textFieldList.add(tf4);
        textFieldList.add(tf5);
        textFieldList.add(tf6);
        textFieldList.add(tf7);
        textFieldList.add(tf8);
        textFieldList.add(tf9);
        textFieldList.add(tf10);
        textFieldList.add(tf11);
        textFieldList.add(tf12);
        textFieldList.add(tf13);
    }

    public void setInvisibleFromIndex(int startIndex) {
        for (int i = 0; i < startIndex ; i++) {
            setLetter(i,"");
            textFieldList.get(i).setVisible(true);
        }
        for (int i = startIndex; i < textFieldList.size(); i++) {
            textFieldList.get(i).setVisible(false);
        }
    }
    @FXML
    public void checkInput() {
        System.out.println(wordTarget);
        String str = input.getText();
        if (wordTarget.contains(str)) {
            for (int i = 0; i < wordTarget.length(); i++) {
                char c = wordTarget.charAt(i);
                if (String.valueOf(c).equals(str)) {
                    setLetter(i, Character.toString(c));
                    counting ++;
                }
            }
        } else {
            setImage();
        }
        if (counting == wordTarget.length()) {
            message.setText("YOU WON!!!");
        }
    }
    public void setLetter(int index, String str) {
        switch (index) {
            case 0:
                tf1.setText(str);
                break;
            case 1:
                tf2.setText(str);
                break;
            case 2:
                tf3.setText(str);
                break;
            case 3:
                tf4.setText(str);
                break;
            case 4:
                tf5.setText(str);
                break;
            case 5:
                tf6.setText(str);
                break;
            case 6:
                tf7.setText(str);
                break;
            case 7:
                tf8.setText(str);
                break;
            case 8:
                tf9.setText(str);
                break;
            case 9:
                tf10.setText(str);
                break;
            case 10:
                tf11.setText(str);
                break;
            case 11:
                tf12.setText(str);
                break;
            case 12:
                tf13.setText(str);
                break;
        }
    }

    public void setImage() {
        numOfGuess --;
        switch (numOfGuess) {
            case 5:
                img.setImage(image1);
                break;
            case 4:
                img.setImage(image2);
                break;
            case 3:
                img.setImage(image3);
                break;
            case 2:
                img.setImage(image4);
                break;
            case 1:
                img.setImage(image5);
                break;
            case 0:
                img.setImage(image6);
                message.setText("YOU LOSE!!!");
                checkButton.setVisible(false);
                break;
        }
    }
    @FXML
    public void newGame() {
        playButton.setText("REPLAY");
        numOfGuess = 6;
        counting = 0;
        img.setImage(image0);
        checkButton.setVisible(true);
        int random = new Random().nextInt(dic.getDictionary().getWordsList().size());
        wordTarget = dic.getDictionary().getWordsList().get(random).getWord_target().toLowerCase();
        message.setText(wordTarget.length() + " LETTERS" );
        setInvisibleFromIndex(wordTarget.length());
    }


}
