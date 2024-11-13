
package logicpuzzle;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class CluesPane extends StackPane{
    int numClues;
    int x = 500;
    int y = 20;
    int yOffset = 70;
    public CluesPane(){
        //default
    }
    public CluesPane(String[] clues){
        this.setTranslateX(x);
        this.setTranslateY(y);
        for(int i = 1; i <= clues.length; i++){
            Clue clue = new Clue(clues[i-1], i ,this);
            //increment position by 50
            clue.setPosition(x, y + (i*yOffset));
            this.getChildren().add(clue);
        }
    }
}
