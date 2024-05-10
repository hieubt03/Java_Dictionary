package org.java_dictionary;

import DictionaryCommandLine.DictionaryManagement;
import DictionaryCommandLine.Word;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class AddController {
    private DictionaryManagement dic;

    @FXML
    TextField engword;
    @FXML
    TextField engpronounce;
    @FXML
    TextArea vieexplain;
    void initData(DictionaryManagement dic) {
        this.dic = dic;
    }
    @FXML
    public void addWord() throws IOException {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        String eng = engword.getText();
        String vie = vieexplain.getText();
        String pronounce = engpronounce.getText();
        Alert alertFalse = new Alert(Alert.AlertType.WARNING);
        alertFalse.setTitle("Không thành công");
        alertFalse.setHeaderText("Từ đã có hoặc từ không hợp lệ");
        boolean isExist = false;
        for (int i = 0; i < dic.getDictionary().getWordsList().size(); i++) {
            if (dic.getDictionary().getWordsList().get(i).getWord_target().equals(eng)) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            alertFalse.show();
            return;
        } else if (eng != null && vie != null && eng.length() * vie.length() != 0) {
            if (pronounce == null) {
                pronounce = "";
            }
            pronounce = "/" + pronounce + "/";
            String str = "\n@" + eng + " " + pronounce + "\n";
            try {
                fileWriter = new FileWriter("TuDien.txt", true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(str);
                bufferedWriter.write(vie);
                bufferedWriter.write("\n");
                bufferedWriter.close();
                fileWriter.close();
            } catch (Exception ex) {

            }
            dic.getDictionary().getWordsList().add(new Word(eng,vie,pronounce));
            Alert alertTrue = new Alert(Alert.AlertType.INFORMATION);
            alertTrue.setTitle("Thông tin");
            alertTrue.setHeaderText("Thêm thành công");
            alertTrue.show();
            return;
        }
        alertFalse.show();
    }
}
