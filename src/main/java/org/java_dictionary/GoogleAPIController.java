package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;

import java.io.IOException;
import java.lang.annotation.Target;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import com.google.cloud.translate.*;

public class GoogleAPIController {
    private DictionaryManagement dic;
    @FXML
    private Button TranslateButton;
    @FXML
    private TextField SourceText;
    @FXML
    private TextField TranslatedText;
    @FXML
    private Button ChangeTargetLangButton;
    @FXML
    private Label TargetLangText;
    private static final String API_KEY = "AIzaSyCARQj8ecJRRuQ5DPng2ULE89ej1DhOp9g";

    private String TargetLang = "vi";

    void initData(DictionaryManagement dic) {
        this.dic = dic;
    }

    @FXML
    void OnTranslateButtonClicked(MouseEvent event) {
        String text = SourceText.getText();

        Translate translate = TranslateOptions.newBuilder().setApiKey(API_KEY).build().getService();

        Translation translation = translate.translate(text,Translate.TranslateOption.targetLanguage(TargetLang));
        TranslatedText.setText(translation.getTranslatedText());

    }

    @FXML
    void OnChangeTargetLang(MouseEvent event) {
        if (TargetLang == "vi"){
            TargetLang = "en";
            TargetLangText.setText("Việt sang Anh");
        } else {
            TargetLang = "vi";
            TargetLangText.setText("Anh sang Việt");
        }
    }







}
