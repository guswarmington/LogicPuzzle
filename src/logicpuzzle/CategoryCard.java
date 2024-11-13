package logicpuzzle;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
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
public class CategoryCard extends Group{
        int height;
        int width;
    public CategoryCard(){
            
        }
    public CategoryCard(String text, PuzzleGrid root, int rotation){
        //initialize constants
        double borderWidth = 2;
        Color borderColor = Color.BLACK;
        height = root.boxWidth;  //height and weight are scalable
        width = root.boxWidth * 4;
        Font textFont = new Font(15); //font size
        
        Rectangle card = new Rectangle(width, height);
            card.setStrokeWidth(borderWidth);
            card.setStroke(borderColor);//black border
            card.setFill(null); //blank/transparent rectangle
            card.getTransforms().add(new Rotate(rotation)); //rotates text
        
        Text textField = new Text(text);
            textField.setFont(textFont);
            textField.setTextAlignment(TextAlignment.CENTER);
            textField.getTransforms().add(new Rotate(rotation)); //rotates text
            
            //position of text changes based on rotation
            //rotation screws up positioning a little bit, especially to scale
            if(rotation == -90){
            textField.setTranslateX(textField.getTranslateX() + (root.boxWidth/2)); //adjusting pos of text
            textField.setTranslateY(textField.getTranslateY() - (root.boxWidth/2));
            }
            else{
            textField.setTranslateX(textField.getTranslateX() + (root.boxWidth*(2.5))); //adjusting pos of text
            textField.setTranslateY(textField.getTranslateY() + (root.boxWidth/2));    
            }
        this.getChildren().add(card);
        this.getChildren().add(textField);
        
        }
        
    }

