package logicpuzzle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * DataSet represents puzzle data loaded from disk
 */
import java.io.File;
import java.util.ArrayList;

/**
 * @author Gus Warmington
 * @author Alex Giberson
 */
public class DataSet {
	private int[] dimensions;
	private String[][] gridData;
	private String[] clues;
	
	/**
	 * Constructor
	 * @param dimensions dimensions of the puzzle
	 * @param gridData the 2d array of grid data
	 * @param clues the array of clues
	 */
	public DataSet(int[] dimensions, String[][] gridData, String[] clues)
	{
		this.dimensions = dimensions;
		this.gridData = gridData;
		this.clues = clues;
	}
	
	/**
	 * Default constructor initializes all arrays to size 0
	 */
	public DataSet()
	{
		this(new int[0], new String[0][0], new String[0]);
	}
	
	/**
	 * Returns the dimensions of the puzzle
	 * @return the dimensions of the puzzle
	 */
	public int[] getDimensions()
	{
		return dimensions;
	}
	
	/**
	 * Returns the grid data
	 * @return the grid data
	 */
	public String[][] getGridData()
	{
		return gridData;
	}
	
	/**
	 * Returns the puzzle clues
	 * @return the puzzle clues
	 */
	public String[] getClues()
	{
		return clues;
	}
	
	/**
	 * Returns a formatted string containing the puzzle data
	 */
	public String toString()
	{
		// Output Dimensions
		String s = "Dataset with the dimensions (";
		
		for(int i = 0; i < dimensions.length; i++)
		{
			s += dimensions[i];
			
			if(i < dimensions.length - 1)
				s += ", ";
		}
		
		// Output grid data
		s += ") with the following grid data: \n";
		
		for(int i = 0; i < gridData.length; i++)
		{
			s += "\n" + gridData[i][0] + "[";
			
			for(int j = 1; j < gridData[i].length; j++)
			{
				s += gridData[i][j];
				
				if(j < gridData[i].length - 1)
					s += ", ";
			}
			
			s += "] ";
		}
		
		// Output clues
		s += "\n\nand the following clues: ";
		
		for(String c: clues)
		{
			s += "\n" + c;
		}
		
		// Return string
		return s;
	}
}
