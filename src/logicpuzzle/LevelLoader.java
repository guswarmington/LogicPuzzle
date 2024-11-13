package logicpuzzle;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;
import org.json.*;
/**
 * LevelLoader - Loads puzzle json from disk and parses it into a usable DataSet instance
 * 
 * @author Alex Giberson
 */
public class LevelLoader {
	private int[] dimensions;
	private Difficulty difficulty;
	
	/**
	 * @author Alex Giberson
	 * Defines the three difficulties a puzzle can be
	 */
	public static enum Difficulty {
		EASY,
		MEDIUM,
		HARD
	}
	
	/**
	 * Constructor 
	 * @param dimensions number-of-topics x number-of-elements
	 * @param difficulty enum element defining the puzzle difficulty
	 */
	public LevelLoader(int[] dimensions, Difficulty difficulty)
	{
		this.dimensions = dimensions;
		this.difficulty = difficulty;
	}
	
	/**
	 * Default constructor sets all params to null
	 */
	public LevelLoader()
	{
		this(null, null);
	}
	
	/**
	 * Load the puzzle defined by the dimensions and difficulty provided in this instance's constructor
	 * @return a DataSet object containing all of the puzzle's data
	 */
	public DataSet load()
	{
		// Get path from current instance data
		Path path = Paths.get(getFilePath());
		DataSet dataSet = new DataSet();
		
		// 2D array to hold puzzle data
		
			// Load entire json file into the object
                try(Scanner scanner = new Scanner( new File(getFilePath()), "UTF-8" )) {
                            String text = scanner.useDelimiter("\\A").next();
                            
			JSONObject jsonObject = new JSONObject(text);
			
			String[][] topicData = loadTopicData(jsonObject);
			String[] clueData = loadClueData(jsonObject);
			
			dataSet = new DataSet(dimensions, topicData, clueData);
		} 
		catch (IOException e){
			System.out.println("Could not read file");
                        return dataSet;
		}
		
		System.out.println("Level successfully loaded...");
		return dataSet;
	}
	
	/**
	 * Builds a file path defined by the dimensions and difficulty provided in this instance's constructor
	 * @return a Path instance containing the file path
	 */
	private String getFilePath()
	{
		// Build string containing file name to load file
		String fileName = "";
                fileName = "src\\logicpuzzle\\PuzzleData\\";
		
		// Files are formatted N_M_Difficulty.json
		for(int i = 0; i < dimensions.length; i++)
		{
			fileName += dimensions[i] + "_";
		}
		
		// Ordinal works because there will only ever be three difficulties
		fileName += difficulty.ordinal();
		fileName += ".json";
		
		// Return filename as Path instance
                System.out.println("\nAttempting to load " + fileName);
                return fileName;
	}
	
	/**
	 * Loads the puzzle's topics and elements into a 2D String array
	 * @param _jsonObject the JSON object representing the entire file
	 * @return 2D string array containing the topics and elements of the puzzle
	 */
	private String[][] loadTopicData(JSONObject _jsonObject)
	{
		// 2D Array representing the data for each topic initialized to the dimensions of the puzzle
		String[][] returnData = new String[dimensions[0]][dimensions[1] + 1];
		
		// Load just the topic JSON object from the whole file's JSON object
		JSONObject topicData = _jsonObject.getJSONObject("Topics");
		
		// Get the iterator containing all of the keys, or actual topics in the puzzle
		Iterator<String> topics = topicData.keys();
		
		// Step through each key in the iterator and include the topic as the first element of each entry
		int t = 0;
		while(topics.hasNext())
		{
			String key = topics.next();
			returnData[t][0] = key;
			
			JSONArray topicArray = topicData.getJSONArray(key);
			for(int i = 0; i < topicArray.length(); i++)
			{
				returnData[t][i + 1] = topicArray.getString(i);
			}
			
			t++;
		}	
		
		// Return the data
		return returnData;
	}
	
	/**
	 * Loads the clue data from the file
	 * @param _jsonObject the JSON object representing the entire file
	 * @return String array containing the puzzle clues
	 */
	private String[] loadClueData(JSONObject _jsonObject)
	{
		// Load just the clue JSON array from the whole file's JSON object
		JSONArray clueData = _jsonObject.getJSONArray("Clues");
		
		// Initialize string array the same size as the clue array
		String[] returnData = new String[clueData.length()];
		
		// Copy the clue json data into a string array
		for(int i = 0; i < clueData.length(); i++)
		{
			returnData[i] = clueData.getString(i);
		}
		
		return returnData;
	}
}
