package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private static final String API_KEY = "AIzaSyCARQj8ecJRRuQ5DPng2ULE89ej1DhOp9g";

    void initData(DictionaryManagement dic) {
        this.dic = dic;
    }

    @FXML
    void OnTranslateButtonClicked(MouseEvent event) {
        String text = SourceText.getText();

        Translate translate = TranslateOptions.newBuilder().setApiKey(API_KEY).build().getService();

        Translation translation = translate.translate(text,Translate.TranslateOption.targetLanguage("vi"));
        TranslatedText.setText(translation.getTranslatedText());

    }







}
