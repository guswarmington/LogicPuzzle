/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicpuzzle;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Gus Warmington
 * 
 * Four tabs on sidebar:
 * Clues - click on clue to cross out or fade out, click again to undo that
 *      3x4 - 4 or 5 clues
 *      3x5 - 6 clues
 *      4x4 - 7 clues
 *      4x5 - 8 to 9 clues
 *      4x7 - 13 - 15 clues 
 * 
 * Story - Backstory and Goal
 * Notes - Editable (blank) text box
 * Answers - this is where the dimensions come in
 *      Table with one category on y axis and the other 3 (or 2) on the x axis
 */
public class SideBar extends Group{
    private String notes = "";
    private String[] clues;
    private String story = "Remember, as with all grid-based logic puzzles, "
            + "\nno option in any category will ever"
            + "\nbe used more than once.";
    private CluesPane cluesPane;
    private Tab tabClues;
    private Tab tabStory;
    private Tab tabNotes;
    private Tab tabAnswers;
    public SideBar(){
        //default
        drawBorder(0);
    }
    public SideBar(DataSet data){
        
        this.clues = data.getClues();
        cluesPane = new CluesPane(clues);
        
        tabClues = new Tab("Clues", 970);
        tabStory = new Tab("Story", 1070);
        tabNotes = new Tab("Notes", 1170);
        tabAnswers = new Tab("Answers", 1270);

        tabClues.setOnMouseClicked((MouseEvent event) -> {
                    
                        //System.out.println("clues");
                        tabClues.setSelected(true);
                        tabStory.setSelected(false);
                        tabNotes.setSelected(false);
                        tabAnswers.setSelected(false);
                        
                        cluesTab();
                    
                    });

        tabStory.setOnMouseClicked((MouseEvent event) -> {
                    
                        //System.out.println("story");
                        tabClues.setSelected(false);
                        tabStory.setSelected(true);
                        tabNotes.setSelected(false);
                        tabAnswers.setSelected(false);
                        
                        storyTab();
                    
                    });

        tabNotes.setOnMouseClicked((MouseEvent event) -> {
                    
                        //System.out.println("Notes: " + notes);
                        tabClues.setSelected(false);
                        tabStory.setSelected(false);
                        tabNotes.setSelected(true);
                        tabAnswers.setSelected(false);
                        
                        notesTab(notes);
                    
                    });
        tabAnswers.setOnMouseClicked((MouseEvent event) -> {
                    
                        //System.out.println("answers");
                        tabAnswers.setSelected(true);
                        tabClues.setSelected(false);
                        tabStory.setSelected(false);
                        tabNotes.setSelected(false);
                        
                        answersTab();
                    
                    });
        
        cluesTab();
    }
    public void reset(){
        this.getChildren().clear();
        drawBorder(0);
    }
    private void cluesTab(){
        
        drawBorder(0);
        this.getChildren().add(cluesPane);
        //breaks program with no data attached
    }
    
    private void drawBorder(int tabNumber){
        this.getChildren().clear();
        Rectangle border = new Rectangle(400, 800);
        border.setStrokeWidth(3.0);
        border.setStroke(Color.BLACK);
        border.setFill(Color.WHITE);
        border.setTranslateX(970);
        border.setTranslateY(40);
        
        this.getChildren().add(border);
        
        drawTabs(tabNumber);
    }
    private void storyTab(){
        drawBorder(1);
        
        Text storyText = new Text(story);
        storyText.setTranslateX(1000);
        storyText.setTranslateY(60);
        this.getChildren().add(storyText);
    }
    private void notesTab(String notesText){
        drawBorder(2);
        
        TextArea notesEntry = new TextArea(notesText);
        notesEntry.maxWidth(350);
        notesEntry.setTranslateX(1000);
        notesEntry.setTranslateY(60);
        notes = notesEntry.getText();
        this.getChildren().add(notesEntry);
    }
    private void answersTab(){
        drawBorder(3);
        
        Text answersText = new Text("This grid will auto-populate with all \n"
                + "the true relationships you've created on the top 4 rows \n"
                + "on the grid. Once this table is fully populated you will \n"
                + "be able to submit your solution.");
        answersText.setTranslateX(1000);
        answersText.setTranslateY(60);
        
        this.getChildren().add(answersText);
    }
    private void drawTabs(int tabNumber){
            if(!this.getChildren().contains(tabClues)){
                this.getChildren().add(tabClues);
            }
            if(!this.getChildren().contains(tabStory)){
                this.getChildren().add(tabStory);
            }
            if(!this.getChildren().contains(tabNotes)){
                this.getChildren().add(tabNotes);
            }
            if(!this.getChildren().contains(tabAnswers)){
                this.getChildren().add(tabAnswers);
            }      
            
            setSelectedTab(tabNumber);
        
    }
    private void setSelectedTab(int tabNumber){
            switch (tabNumber) {
            case 0:
                tabClues.setSelected(true);
                tabStory.setSelected(false);
                tabNotes.setSelected(false);
                tabAnswers.setSelected(false);
                break;
            case 1:
                tabClues.setSelected(false);
                tabStory.setSelected(true);
                tabNotes.setSelected(false);
                tabAnswers.setSelected(false);
                break;
            case 2:
                tabClues.setSelected(false);
                tabStory.setSelected(false);
                tabNotes.setSelected(true);
                tabAnswers.setSelected(false);
                break;
            case 3:
                tabClues.setSelected(false);
                tabStory.setSelected(false);
                tabNotes.setSelected(false);
                tabAnswers.setSelected(true);
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
