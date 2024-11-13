/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicpuzzle;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Gus Warmington
 */
public class Tab extends Group{
    private int x;
    private int y;
    boolean faded = false;
    boolean selected = false;
    private String text;
    Color textColor = (!faded) ? Color.BLACK : Color.GREY;
    Color tabColor = (!faded) ? Color.WHITE : Color.LIGHTGREY;
    double strokeWidth = (selected) ? 3.0 : 2.0;
    public Tab(String text, int x){
        this.text = text;
        this.x = x;
        this.y= 10;
        
        this.drawTab(text);

    }
    public Tab(){
        //default
    }
    public void setSelected(boolean bool){
        selected = bool;
        if(bool){
            
        }
        textColor = (bool) ? Color.BLACK : Color.GREY;
        tabColor = (bool) ? Color.WHITE : Color.LIGHTGREY;
        strokeWidth = (bool) ? 3.0 : 2.0;
        this.getChildren().clear();
        drawTab(text);
    }
    public boolean getSelected(){
        return selected;
    }
    public void drawTab(String txt){
        
        Rectangle tabBorder = new Rectangle(100,30);
            tabBorder.setStrokeWidth(strokeWidth);
            tabBorder.setTranslateX(x);
            tabBorder.setTranslateY(y);
            tabBorder.setFill(tabColor);
            tabBorder.setStroke(Color.BLACK);
        
        Text tabText = new Text(txt);
            tabText.setTextAlignment(TextAlignment.CENTER);
            tabText.setTranslateX(x+15);
            tabText.setTranslateY(y+20);
            
            tabText.setFill(textColor);
            
        getChildren().add(tabBorder);
        getChildren().add(tabText);
    }
}
