/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicpuzzle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author gusin
 */
public class PopUpCard extends StackPane{
    public PopUpCard(){
        //default
    }
    int width;
    int height;
    public PopUpCard(String message, boolean OKbutton, LogicPuzzle puzzle){
        
        width = 300;
        height = 100;
        Font textFont = new Font(20); //font size
        this.setHeight(height);
        this.setWidth(width);
        Rectangle border = new Rectangle(width,height);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(2.0);
        border.setFill(null);
        border.setTranslateX(this.getTranslateX() + (width*.15));
        this.getChildren().add(border);
        
        Text textField = new Text(message);
        textField.setTextAlignment(TextAlignment.LEFT);
        textField.setFont(textFont);
        this.getChildren().add(textField);
        if(OKbutton){
            Button button = new Button("OK");
        
            button.setOnAction((ActionEvent event)->{
                puzzle.setPopUpCheck(false);//
                deleteSelf();
            });
            button.setTranslateX(this.getTranslateX()+(width*.5));
            this.getChildren().add(button);
            
        }
        
    }
    public void deleteSelf(){
        this.getChildren().clear();
    }

}
