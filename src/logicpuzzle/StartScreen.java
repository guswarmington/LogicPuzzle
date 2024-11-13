/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicpuzzle;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static logicpuzzle.LevelLoader.Difficulty.*;

/**
 *
 * @author gusin
 */
public class StartScreen extends Group{
    LogicPuzzle root;
    Pane display;
    public StartScreen(Pane display, LogicPuzzle root){
        this.display = display;
        this.root = root;
        drawScreen();
        
    }
    private void drawScreen(){
        Text titleText = new Text("Logic Puzzle");
            titleText.setTranslateX(550);
            titleText.setTranslateY(150);
            titleText.setFont(new Font(50));
        Text authorText = new Text("By Gus Warmington, Alex Giberson, and Yunjin");
            authorText.setTranslateX(530);
            authorText.setTranslateY(200);

        ComboBox dimensions = new ComboBox();
            dimensions.getItems().addAll("3x4", "3x5", "4x4", "4x5","4x6","4x7");
            dimensions.setValue("3x4");
            dimensions.setTranslateX(650);
            dimensions.setTranslateY(300);
        ComboBox difficulty = new ComboBox();
            difficulty.getItems().addAll(EASY, MEDIUM, HARD);
            difficulty.setValue(EASY);
            difficulty.setTranslateX(650);
            difficulty.setTranslateY(350);
        Button startButton = new Button("Start");
            startButton.setTranslateX(650);
            startButton.setTranslateY(400);
        startButton.setOnAction((ActionEvent event)->{
            int numCategories = (dimensions.getValue() == "3x4" || 
                    dimensions.getValue() == "3x5") ? 3 : 4;
            if(dimensionValue(dimensions) != 0){
                
            LevelLoader loader = new LevelLoader(convertDimension(dimensions), (LevelLoader.Difficulty) difficulty.getValue());
            DataSet data = loader.load();
            
            root.createGame(numCategories,dimensionValue(dimensions),data);
            
            }
        });
        
        display.getChildren().addAll(titleText,authorText, 
                dimensions, difficulty, startButton);
    }      
    private int dimensionValue(ComboBox dimension){
        if(dimension.getValue() == "3x4" || dimension.getValue() == "4x4"){
            return 4;
        }
        if(dimension.getValue() == "3x5" || dimension.getValue() == "4x5"){
            return 5;
        }
        if(dimension.getValue() == "4x6"){
            return 6;
        }
        if(dimension.getValue() == "4x7"){
            return 7;
        }
        return 0;//error
    }
    private int[] convertDimension(ComboBox dimension){
        if(dimension.getValue() == "3x4"){ 
        return new int[]{3,4};
        }
        if(dimension.getValue() == "3x5"){ 
        return new int[]{3,5};
        }
        if(dimension.getValue() == "4x4"){ 
        return new int[]{4,4};
        }
        if(dimension.getValue() == "4x5"){ 
        return new int[]{4,5};
        }
        if(dimension.getValue() == "4x6"){ 
        return new int[]{4,6};
        }
        if(dimension.getValue() == "4x7"){ 
        return new int[]{4,7};
        }
        return new int[]{0,0};
    }
            
    }

