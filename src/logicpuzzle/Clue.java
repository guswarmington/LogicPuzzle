/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicpuzzle;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author gusin
 */
public class Clue extends Group{
    private boolean active;
    private Text text;
    public Clue(){
        
    }
    public Clue(String clue, int number, CluesPane pane){
        active = true;
        int width = (int) pane.getWidth();
        int height = (int) pane.getHeight();
         text = new Text(number + ". " +clue);
        this.setPosition(1200,100+(100*number));
        text.setTranslateX(340);
        text.setTranslateY(100*number);
        this.getChildren().add(text);
        
        this.setOnMouseClicked((MouseEvent event)->{
            toggleFade();
        });
    }
    public void toggleFade(){
        if(active){
            text.setFill(Color.LIGHTGREY);
            active = false;
        }
        else{
            text.setFill(Color.BLACK);
            active = true;
        }
        
        
    }
    public void setPosition(int x, int y){
        this.setTranslateX((double)x);
        this.setTranslateY((double)y);
    }
    public boolean getState(){
        return active;
        
    }
    
}
