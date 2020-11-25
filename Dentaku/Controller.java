package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.util.ArrayDeque;
import java.util.Deque;

public class Controller {

    @FXML Label totalLabel;
    Alert dialog;
    String label;
    String num;
    String op;
    String op_keep;
    String buttonText;

    public Controller(){
        dialog = new Alert(Alert.AlertType.ERROR);
        label = "";         //ラベルに表示されてる数値を保持
        num = "";           //計算に使う数値を保持
        op = "";            //計算に使う演算子を保持
        op_keep = "";       //演算子を変更できるように仮で演算子を保持
        buttonText = "";    //押されたボタンの文字を保持
    }

    @FXML
    public void onPushButton(ActionEvent evt){
        buttonText = ((Button)evt.getSource()).getText();
        label = this.totalLabel.getText();

        while (true) {
            //最初のボタン入力チェック
            if (label.equals("0")) {
                //最初に0を押したとき
                if (buttonText.equals("0")) {
                    showDialog("0");
                    break;
                //最初の演算子を押したとき
                } else if (buttonText.matches("[/x+-]")) {
                    showDialog("演算子");
                    break;
                }
                label = "";
            }

            //数字ボタンを押したとき
            if (buttonText.matches("[0-9]+")) {
                //演算子の選択が完了したとき
                if (!op_keep.isEmpty()) {
                    if(num.isEmpty()) {
                        op = op_keep;
                        op_keep = "";
                        this.totalLabel.setText(label + buttonText);
                    }else{
                        op = op_keep;
                        op_keep = "";
                        this.totalLabel.setText(buttonText);
                    }
                //演算子が選択されていないとき
                } else {
                    this.totalLabel.setText(label + buttonText);
                }
            //演算子ボタンが押されたとき
            } else if (buttonText.matches("[/x+-]")) {
                //演算子が決定してないとき
                if (op.isEmpty()) {
                    op_keep = buttonText;
                    num = label;
                    this.totalLabel.setText("");
                //演算子が確定しているので、計算する
                } else {
                    num = cal(num, label, op);
                    this.totalLabel.setText(num);
                    op = "";
                    op_keep = buttonText;

                }
            //クリアボタンを押したとき
            }else if(buttonText.equals("C")){
                this.totalLabel.setText("0");
                label = "";
                num = "";
                op = "";
                op_keep = "";
                buttonText = "";
            //イコールを押したとき
            }else if(buttonText.equals("=")){

            }
            break;
        }

    }

    public String cal(String number1, String number2, String operator){
        int sum = 0;
        int n1 = toInt(number1);
        int n2 = toInt(number2);

        switch(operator){
            case "/":
                sum = n1 / n2;
                break;
            case "x":
                sum = n1 * n2;
                break;
            case "+":
                sum = n1 + n2;
                break;
            case "-":
                sum = n1 - n2;
        }

        return String.valueOf(sum);
    }

    public int toInt(String str){
        return Integer.parseInt(str);
    }

    //エラーダイアログを表示する
    public void showDialog(String word){
        dialog.setHeaderText(null);
        dialog.setContentText("最初に" + word + "ボタンを押さないでください。");
        dialog.showAndWait();
    }
}
