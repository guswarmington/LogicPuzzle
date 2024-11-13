/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicpuzzle;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Gus Warmington
 */
public class TitleCard extends Group{
    int height;
    int width;
    public TitleCard(String text, PuzzleGrid root, int rotation){
                height = root.boxWidth / 2;
                width = root.boxWidth * root.width;  
                Font textFont = new Font(13); //font size
        
       Rectangle card = new Rectangle(width, height);
            card.setStrokeWidth(1.5);
            card.setStroke(Color.BLACK);
            card.setFill(Color.BLACK);
            card.getTransforms().add(new Rotate(rotation));
        
       Text textField = new Text(text);
            textField.setTextAlignment(TextAlignment.CENTER);
            textField.setFont(textFont);
            textField.setFill(Color.WHITE);
            textField.getTransforms().add(new Rotate(rotation));
            
            //position of text changes based on rotation
            //rotation screws up positioning a little bit, especially to scale
        if(rotation == -90){
            textField.setTranslateX(textField.getTranslateX() + (root.boxWidth/3));
            textField.setTranslateY(textField.getTranslateY() - (root.boxWidth*(root.width/2)) + (root.boxWidth/2));
            }
        else{
            textField.setTranslateX(textField.getTranslateX() + (root.boxWidth*(root.width/2)));
            textField.setTranslateY(textField.getTranslateY() + (root.boxWidth/3));    
            }
        this.getChildren().add(card);
        this.getChildren().add(textField);
        
        }
        
    }

