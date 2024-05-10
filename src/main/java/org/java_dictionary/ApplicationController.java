package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    private DictionaryManagement dic = new DictionaryManagement();
    @FXML
    private Button searchButton;
    @FXML
    private Button googleButton;
    @FXML
    private Button addButton;
    @FXML
    private Button gameButton;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane gamePane;
    @FXML
    private AnchorPane addPane;
    @FXML
    private AnchorPane googleapiPane;
    private void setMainBorderPane(AnchorPane anchorPane) {
        mainBorderPane.setCenter(anchorPane);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dic.insertTxt();
        try {
            loadSearcher();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadSearcher() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
        Parent searchParent = loader.load();
        SearchController searchController = loader.<SearchController>getController();
        searchController.initData(dic);
        searchPane = (AnchorPane) searchParent;
        setMainBorderPane(searchPane);
    }

    public void loadGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hangedmangame.fxml"));
        Parent gameParent = loader.load();
        HangedManGameController hangedManGameController = loader.<HangedManGameController>getController();
        hangedManGameController.initData(dic);
        gamePane = (AnchorPane) gameParent;
        setMainBorderPane(gamePane);
    }

    public void loadAddWord() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent addParent = loader.load();
        AddController addController = loader.<AddController>getController();
        addController.initData(dic);
        addPane = (AnchorPane) addParent;
        setMainBorderPane(addPane);
    }

    public void loadGoogleApi() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("googleapi.fxml"));
        Parent googleapiParent = loader.load();
        GoogleAPIController googleAPIController = loader.<GoogleAPIController>getController();
        googleAPIController.initData(dic);
        googleapiPane = (AnchorPane) googleapiParent;
        setMainBorderPane(googleapiPane);
    }

}
