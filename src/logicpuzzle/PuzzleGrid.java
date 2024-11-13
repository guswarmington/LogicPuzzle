package logicpuzzle;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;

/*
Three different grids, each height * width, all with blank insides. 
Each grid has a copy that is "correct".
When an O is inputted, the rest of the row and colum will fill with Xs


 */
public class PuzzleGrid extends StackPane{
    public int numCategories; //number of grids, 3 or 6
    public int width; //how many tiles per grid, widthxwidth
    public int boxWidth; //How wide, in pixels is each tile/box
    Box[][] boxTable; //For submission and checking against solution

    int startX = 250; //where on the screen will the first box be placed
    int startY = 250;
    boolean popUpCheck = false;
    
    public PuzzleGrid(){
        //default
    }
    public PuzzleGrid(int numCategories, int width, Pane root, int gridNumber, LogicPuzzle puzzle){
        popUpCheck = false;
        this.numCategories = numCategories;
        this.width = width; //dimensions of grid
        this.boxWidth = 50; //default is 50
        startX = 350;
        startY = 350;
        if(width > 4){
            this.boxWidth = 40;
            startX = 310;
            startY = 310;
            //for presentation purposes
        }
        if(numCategories == 4){
        this.boxWidth = 40;
        startX = 200;
        startY = 200;//Needs to be smaller to fit 6 grids
        if(width>5){
            this.boxWidth = 35;
            startX = 250;
            startY = 250;
        }
        }
        
        switch(gridNumber){
            //Each grid starts at different x and y
            /*
            Tile Grid setup:
            0 1 3           It is numbered this way so that 0 1 & 2 can exist independently
            2 4
            5
            */

            case 1 :
                startX += (boxWidth*width);
                break;
            case 2 :

                startY += (boxWidth*width);
                break;
            case 3 :
                startX += (2*boxWidth*width);
                break;
            case 4 :
                startX += (boxWidth*width);
                startY += (boxWidth*width);
                break;
            case 5 :
                startY += (2*boxWidth*width);
                break;
            default:
                //default should never be called, but just in case
                break;
        }
        //simple array of boxes within each grid
        //allows for further manipulation after object creation
        boxTable = new Box[width][width];
        
        //Creating grid of tiles
        for(int i = 0; i < width; i++){
            //Left side grids get category and title cards
            if(gridNumber ==0 || gridNumber == 1 || gridNumber == 3){
                //
            CategoryCard field = new CategoryCard("test " + i, this, -90);
                field.setTranslateX(startX + ((i)*boxWidth)); //one card per column
                field.setTranslateY(startY);
                root.getChildren().add(field); //add to pane
            
            if(i == 0){ //Create border to surround categoryCard, only occurs once per grid
                Rectangle titleBorder = new Rectangle((4*boxWidth), (width*boxWidth));
                    titleBorder.setStrokeType(StrokeType.CENTERED);
                    titleBorder.setFill(null); //transparent
                    titleBorder.setTranslateX(startX + ((i)*boxWidth)); 
                    titleBorder.setTranslateY(startY);
                    titleBorder.getTransforms().add(new Rotate(-90));
                    titleBorder.setStrokeWidth(3);
                    titleBorder.setStroke(Color.BLACK);
                    root.getChildren().add(titleBorder);
                //Title card creation, has category name 
                TitleCard titleCardH = new TitleCard("title" + gridNumber, this, 0); //horizontal title
                    titleCardH.setTranslateX(startX);
                    titleCardH.setTranslateY(startY - (boxWidth*4) - (boxWidth/2));
                    root.getChildren().add(titleCardH); //add to pane
                
            }
            }
            for(int j = 0; j < width; j++){
                if((gridNumber == 0 || gridNumber == 2 || gridNumber == 5) && i == 0){
                    //Top side grids get category cards and titles, oriented above
                    CategoryCard field = new CategoryCard("test " + j, this, 0);
                        field.setTranslateX(startX-(boxWidth*4));
                        field.setTranslateY(startY+(j*boxWidth));
                        root.getChildren().add(field);
                        
                    if(j == 0){//border to surround all category cards
                        Rectangle titleBorder = new Rectangle((4*boxWidth), (4*boxWidth));
                            titleBorder.setStrokeType(StrokeType.CENTERED);
                            titleBorder.setFill(null);
                            titleBorder.setTranslateX(startX-(boxWidth*4));
                            titleBorder.setTranslateY(startY);
                            titleBorder.getTransforms().add(new Rotate(0));
                            titleBorder.setStrokeWidth(3);
                            titleBorder.setStroke(Color.BLACK);
                            root.getChildren().add(titleBorder);
                        
                        TitleCard titleCardV = new TitleCard("title" + gridNumber, this, -90); //horizontal title
                            titleCardV.setTranslateX(startX - (boxWidth*4) - (boxWidth/2));
                            titleCardV.setTranslateY(startY + (boxWidth * width));
                            root.getChildren().add(titleCardV);
                    }
            
                    }
                Box box = new Box(i, j, gridNumber, boxWidth);
                    box.setTranslateX(startX + (i*box.getBoxWidth()));
                    box.setTranslateY(startY + (j*box.getBoxWidth()));
                    box.setOnMouseClicked((MouseEvent event) -> {
                        if(event.getButton() == MouseButton.PRIMARY){//Left click
                            if(this.checkForConflicts(box.getColumn(),box.getRow())){
                            box.toggleBox(); //change box symbol
                            this.gridXRows();//check each row for o's and fill in row and column with x's
                            }
                            else{
                                //Pop up to alert user of the system rules
                                if(!puzzle.getPopUpCheck()){
                                PopUpCard warningCard = new PopUpCard("There is already an O\nin"
                                        + " this row or column", true, puzzle);
                                warningCard.setTranslateX(root.getPrefWidth() + box.getWidth());
                                warningCard.setTranslateY((2*root.getPrefHeight()/3) +  box.getHeight());
                                root.getChildren().add(warningCard);
                                puzzle.setPopUpCheck(true);
                            }
                            }
                        }
                        else{//Right click clears box
                                box.clear();
                            }
                        });//end lambda
                    
                boxTable[j][i] = box; //add new box to array
                
                root.getChildren().add(box); //add box to pane
            }
            
        }
        //Create grid border, every grid gets this no matter gridNumber
        Rectangle border = new Rectangle((width*boxWidth), (width*boxWidth));
        border.setStrokeType(StrokeType.CENTERED);
        border.setFill(null);
        border.setTranslateX(startX);
        border.setTranslateY(startY);
        border.setStrokeWidth(3);
        border.setStroke(Color.BLACK);
        root.getChildren().add(border);
        
    }
    
    public void gridXRows(){ //Sets row and column of an O to Xs
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                 
                if(boxTable[j][i].getToggleNumber() == 2){ //if box is a O
                    for(int row = 0; row < width; row++){
                        if(row != i){
                        boxTable[j][row].setToggleNumber(1);//set boxes in this row to an x
                        }
                    }
                    for(int col = 0; col < width; col++){
                        if(col != j){
                        boxTable[col][i].setToggleNumber(1);//set boxes in this column to an x
                        }
                    }
                    
                }
                
            }
        }
        updateGrid();
    }
    public void updateGrid(){ //Simple call for all boxes in grid to call update method
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                boxTable[j][i].update();
            }
        }
    }
    public void clear(){
        //revert grid back to normal, blank state
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                boxTable[j][i].setToggleNumber(0);
                boxTable[j][i].update();
            }
        }
        
    }

    private boolean checkForConflicts(int column, int row) {
        //no conflicts = true;
        
        //Check the current row/column for existing O, return false if found.
        for(int i = 0; i< width; i++){
            if(i != row){
            if(boxTable[column][i].getToggleNumber() == 2){
                //Conflict found
                return false;
            }
            }
        
        }
        for(int j = 0; j< width; j++){
            if(j != column){
            if(boxTable[j][row].getToggleNumber() == 2){
                //Conflict found
                return false;
            }
            }
        }
        return true;
    }
    public void setPopUpCheck(){
        popUpCheck = false;
    }
    
    @Override
    public String toString(){
        String results = "";
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                results += boxTable[j][i].getToggleNumber();
                
            }
            results += " ";
        }
        return results;
        
    }

}
