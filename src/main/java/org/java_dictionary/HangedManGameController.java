package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HangedManGameController {
    private DictionaryManagement dic = new DictionaryManagement();
    @FXML
    private List<TextField> textFieldList;
    @FXML
    TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, tf13;
    public void initialize(){
        dic.insertFromFile();
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
        for (int i = startIndex; i < textFieldList.size(); i++) {
            textFieldList.get(i).setVisible(false);
        }
    }
}
