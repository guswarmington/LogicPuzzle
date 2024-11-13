
package logicpuzzle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
Tile Grid setup:
0 1 3
2 4
5
 */
public class LogicPuzzle extends Application {
    Pane display;
    int screenWidth;
    int screenHeight;
    boolean popUpCheck;
    String results;
    DataSet data;
    public LogicPuzzle(){
        popUpCheck = false;
        screenWidth = 1400;
        screenHeight = 900;
        display = new Pane();
    }
    @Override
    public void start(Stage primaryStage) {
        StartScreen startScreen = new StartScreen(display, this);
        display.getChildren().add(startScreen);
        
        //createGame(int numCategories, int width)
        //width is the dimension of tiles on grid
        //numCategories will determine between 3 and 6 grids
        //3 - > 3
        //4 - > 6
        
        //LevelLoader level = new LevelLoader();
        //createGame(4,6);
        Scene scene = new Scene(display, screenWidth, screenHeight);
        
        //set to maximized so full display is seen
        //primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void createGame(int numCategories, int tileWidth, DataSet data){
        display.getChildren().clear();
        
        SideBar sidebar = new SideBar(data);
        
        display.setPrefSize(900, 1400);
        
        display.getChildren().add(sidebar);
        //Two options, 3 or 6 grids total
        //On website, 3x4 and 3x5 have 3 grids total
        // 4x4, 4x5 4x6 and 4x7 have 6 grids
        //Seems as though it is the difference between having 3 or 4 categories
        //3 categories -> 3 total grids
        //4 categories -> 6 total grids
        
        Button submit = new Button("Submit & Check");
        submit.setLayoutX(1120);
        submit.setLayoutY(870);
        display.getChildren().add(submit);
        results = "";
            //Each grid is numbered 0 through 2
        PuzzleGrid grid0 = new PuzzleGrid(numCategories, tileWidth,display,0,this);
        PuzzleGrid grid1 = new PuzzleGrid(numCategories, tileWidth,display,1,this);
        PuzzleGrid grid2 = new PuzzleGrid(numCategories, tileWidth,display,2,this);
        display.getChildren().add(grid0);
        display.getChildren().add(grid1);
        display.getChildren().add(grid2);
        submit.setOnAction((ActionEvent event) ->{
            results += grid0.toString() + "\n" + grid1.toString() + "\n" + grid2.toString();
            System.out.println(results);
        });

        if(numCategories == 4){
        PuzzleGrid grid3 = new PuzzleGrid(numCategories, tileWidth,display,3,this);
        PuzzleGrid grid4 = new PuzzleGrid(numCategories, tileWidth,display,4,this);
        PuzzleGrid grid5 = new PuzzleGrid(numCategories, tileWidth,display,5,this);
        display.getChildren().add(grid3);
        display.getChildren().add(grid4);
        display.getChildren().add(grid5);
                        submit.setOnAction((ActionEvent event) ->{
            results += grid0.toString() + "\n" + grid1.toString() + "\n" + grid2.toString()
                    + "\n" + grid3.toString() + "\n" + grid4.toString() + "\n" + grid5.toString() ;
                            System.out.println(results);
        });
                        
        }
        //Create submit button that will take button data and turn it into a String, to be compared with
        //the correct solution
        
    }
    public boolean getPopUpCheck(){
        
        return popUpCheck;
    }
    public void setPopUpCheck(boolean check){
        popUpCheck = check;
    }
    public DataSet getData(){
        return data;
    }
}
