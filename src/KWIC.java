/*
 * CS3213 Home work 1: KWIC
 * Group members:
 * 	Qing Cheng (A0091747W)
 * 	Yang Zhixing (A0091726B)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class KWIC {
	
	public static ArrayList<String> getInputLines(){
		String currentLine = "";
		System.out.println("Enter a line of text (input blank line to finish input): ");
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		ArrayList<String> stringList = new ArrayList<String>();
		try{
			currentLine = in.readLine();
			while(!currentLine.equals("")){
				stringList.add(currentLine);
				currentLine = in.readLine();
			}
			return stringList;
		} catch(IOException e){
			return stringList;
		}
	}
	
	public static ArrayList<String> getInputIgnoredList(){
		String currentLine = "";
		System.out.println("Enter ignored words (input space between words, press enter to finish): ");
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		ArrayList<String> wordsList = new ArrayList<String>();
		try{
			currentLine = in.readLine();
			String[] wordsArray = currentLine.split(" ");
			Collections.addAll(wordsList, wordsArray);
			return wordsList;
		} catch(IOException e){
			return wordsList;
		}
	}
	
	public static ArrayList<String> circularShift(ArrayList<String> toBeShifted, ArrayList<String> toBeIgnored){
		
		ArrayList<String> afterShifting = new ArrayList<String>();
		for(String currentLine: toBeShifted){
			String[] words = currentLine.split(" ");
			
			int numOfWords = words.length;
			ArrayList<String> wordsList = new ArrayList<String>();
			for(String w: words){
				if(!toBeIgnored.contains(w.toLowerCase())){
					w = w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase();
				} else {
					w = w.toLowerCase();
				}
				wordsList.add(w);
			}
			
			
			for(int i = 0; i < numOfWords; i++){
				String word = wordsList.get(0);
				
				if(!toBeIgnored.contains(word.toLowerCase())){
					afterShifting.add(convertToString(wordsList));
				}
				wordsList.remove(0);
				wordsList.add(word);
			}
		}
		return afterShifting;
	}
	
	public static String convertToString(ArrayList<String> toBeConverted){
		String result = "";
		for(String word: toBeConverted){
			result += word;
			result += " ";
		}
		return result;
	}
	
	private static ArrayList<String> alphabetizedList(ArrayList<String> inputList){
		
		Collections.sort(inputList);
		return inputList;
	}
	
	private static void outputResult(ArrayList<String> input){
		
		for(String newLine: input){
			System.out.println(newLine);
		}	
	}
	
	public static void main (String[] arg){
		
		// By Zhixing
		ArrayList<String> ignoredList = getInputIgnoredList();
		
		// By Qing Cheng
		ArrayList<String> inputStringList = getInputLines();
		
		// By Qing Cheng
		ArrayList<String> circularShiftedList = circularShift(inputStringList, ignoredList);
		
		// By Zhixing
		ArrayList<String> alphabetizedList = alphabetizedList(circularShiftedList);
		
		// By Zhixing
		outputResult(alphabetizedList);
		
		// After writing the above functions individually, 
		// two team members integrated the code together, debugged and tested together.
		
	}
}
