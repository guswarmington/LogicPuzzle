
package logicpuzzle;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/*
Box class creates a singular box to be placed within the grid.
Functionality: When clicked, toggle between blank, x and o

 */
public class Box extends StackPane{
    //extends stackpane, allowing for many useful functions and for many graphical objects to be grouped
    //into a single clickable object
    int toggleNumber = 0; //0 = blank, 1 = X, 2 = O
    
    int x;
    int y;
    
    double xLineWidth;
    double circleLineWidth;
    Color xColor;
    int xLineOffset; //how far away from corners of square
    
    int squareWidth; //dimensions of square
    double borderThickness; //square's border thickness
    Color squareHoverColor;
    boolean entered;
    
    Color circleStrokeColor;
    Color circleFillColor;
    int circleRadius;
    
    private int column;
    private int row;
    
    
    public Box(){
        //default
    }
    public Box(int row, int column, int gridNumber, int squareWidth){
        super();
        this.row = row;
        this.column = column;
        squareHoverColor = Color.YELLOW;
        this.squareWidth = squareWidth;
        borderThickness = 1.5;
        
        xLineWidth = 2.0;
        xColor = Color.BLACK;
        xLineOffset = (int) (squareWidth/2) - 5; //set to scale with square size
        
        circleRadius =(squareWidth/5)*2;
        circleStrokeColor = Color.BLACK; // border color
        circleFillColor = Color.GREEN; //inside color
        circleLineWidth = 1.8; //width of border
        
        x = (int) this.getTranslateX(); //easier access to coords/location of box
        y = (int) this.getTranslateY();
        
        this.setAlignment(Pos.CENTER); //for drawing inside circle
        
        toggleNumber = 0;
        
        //Initial drawing of box
        Rectangle square = new Rectangle(squareWidth,squareWidth);
        square.setFill(null);
        square.setStroke(Color.BLACK);
        square.setStrokeWidth(borderThickness);
        
        this.getChildren().add(square); //add to stackpane
        
        this.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton() == MouseButton.PRIMARY){
              toggleBox(); //left click toggle between shapes 
            }
            else{
                clear(); //right click reverts to blank
            }
        });
        this.setOnMouseEntered((MouseEvent e) -> {
        square.setFill(squareHoverColor);
        entered = true;
    });
        this.setOnMouseExited((MouseEvent e) -> {
        square.setFill(null);
        entered = false;
    });
    }
    
    public int toggleBox(){
        toggleNumber++; //0 = blank, 1 = X, 2 = 0;
        if(toggleNumber == 3){
            //Loops back to blank square
            toggleNumber = 0;
        }
        update();
        return toggleNumber;
    }
    
    public void clear(){
        //reverts to default blank state
        this.getChildren().clear();
        drawBox();
        toggleNumber = 0;
    }
    
    public void update(){
        //changes inside without incrementing toggle number, useful for setting all Xs in a row when placing circle
        this.getChildren().clear();
        drawBox();
        if(toggleNumber == 1){
            drawXs();
        }
        if(toggleNumber == 2){
            drawCircle();
        }
    }
    public void drawBox(){ //create a square
        Rectangle square = new Rectangle(squareWidth,squareWidth);
        if(entered){
        square.setFill(squareHoverColor);
        }
        else{
        square.setFill(null);
        }
        square.setStroke(Color.BLACK);
        square.setStrokeWidth(borderThickness);
        this.setOnMouseEntered((MouseEvent e) -> {
        square.setFill(squareHoverColor);
        entered = true;
    });
        this.setOnMouseExited((MouseEvent e) -> {
        square.setFill(null);
        entered = false;
    });
        this.getChildren().add(square);
        
    }
    private void drawCircle(){//draw circle within square, 
        Circle circle = new Circle(circleRadius, circleFillColor);
            circle.setStroke(circleStrokeColor);
            circle.setStrokeWidth(circleLineWidth);
            this.getChildren().add(circle);
    }
    private void drawXs(){
        //Draw X, two intersecting lines
        Line xLine1 = new Line(x-xLineOffset,y-xLineOffset,
                                            x+xLineOffset, y+xLineOffset);
            xLine1.setStroke(xColor);
            xLine1.setStrokeWidth(xLineWidth);
            
        Line xLine2 = new Line(x - xLineOffset, y +xLineOffset, 
                                            x +xLineOffset, y-xLineOffset);
            xLine2.setStroke(xColor);
            xLine2.setStrokeWidth(xLineWidth);

        this.getChildren().add(xLine1);
        this.getChildren().add(xLine2);
    }
    public int getBoxWidth(){
        return squareWidth;
    }
    public void setToggleNumber(int number){
        toggleNumber = number;
    }
    public int getToggleNumber(){
        return toggleNumber;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }

}
