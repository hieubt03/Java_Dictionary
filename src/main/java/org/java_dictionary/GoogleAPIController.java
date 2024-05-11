package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;
//import com.google.cloud.translate.v3.LocationName;
//import com.google.cloud.translate.v3.TranslateTextRequest;
//import com.google.cloud.translate.v3.TranslateTextResponse;
//import com.google.cloud.translate.v3.Translation;
//import com.google.cloud.translate.v3.TranslationServiceClient;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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




        TranslatedText.setText(text);

    }
 /*
    public static void translateText() throws IOException {
        String projectId = "charged-gravity-422909-f7";
        String targetLanguage = "es";
        String text = "Hello world!";
        translateText(projectId, targetLanguage, text);
    }

    public static void translateText(String projectId, String targetLanguage, String text)
            throws IOException {

        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            LocationName parent = LocationName.of(projectId, "global");

            TranslateTextRequest request =
                    TranslateTextRequest.newBuilder()
                            .setParent(parent.toString())
                            .setMimeType("text/plain")
                            .setTargetLanguageCode(targetLanguage)
                            .addContents(text)
                            .build();

            TranslateTextResponse response = client.translateText(request);

            // Display the translation for each input text provided
            for (Translation translation : response.getTranslationsList()) {
                System.out.printf("Translated text: %s\n", translation.getTranslatedText());
            }
        }

    }
*/


}
