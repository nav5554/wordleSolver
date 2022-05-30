import java.util.*;
import java.io.*;

/**
 * Write a description of class WordleGuesser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class wordle
{
    public static void wordle(int pos1, String letter1, int pos2, String letter2, int pos3, String letter3, int pos4, String letter4, int pos5, String letter5, String[] notInclChar, String[] inclChar, String[] npos1, String[] npos2, String[] npos3, String[] npos4, String[] npos5, boolean showAllWords, boolean rank) throws IOException
    {
        Scanner scan = new Scanner(new File("wordleAllWords.txt")).useDelimiter("/s");
        Scanner scan2 = new Scanner(new File("wordleAllAnswers.txt")).useDelimiter("/s");
        
       


        ArrayList<String> words3 = new ArrayList<String>();
        ArrayList<String> possibleWords = new ArrayList<String>();
        String str;
        String str2;

        //Transfer Words from .txt file to ArrayList
        while (scan.hasNext())
        {
            str = scan.next();
            words3.add(str);
        }
        while (scan2.hasNext())
        {
            str2 = scan2.next();
            possibleWords.add(str2);

        }
        
        String rankString = " ";

       
        
        //Parse .txt file into separate words
        String[] words = words3.get(0).split("\\s+");
        String[] possibleWords2 = possibleWords.get(0).split("\\s+");
        

        

        

        possibleWords.clear();
        for(int i = 0; i < possibleWords2.length; i++)
        {
            possibleWords.add(possibleWords2[i]);
        }
        

        int count = 0;
        int expectedCount = 0;

        

        

        //Checks if words contains character from array provided, if it does, remove it from array list
        if(inclChar.length != 0)
        {
            for(int i = 0; i < words.length; i ++)
            {
                for(int j = 0; j < inclChar.length; j ++)
                {
                    if( words[i] != null && !words[i].contains(inclChar[j]))
                    {
                        words[i] = null;
                    }

                }

            }

        }

        ArrayList<String> newWords = new ArrayList<String>();
        for(int i = 0; i < words.length; i++)
        {
            if(words[i] != null)
            {
                for(int j = 1; j < 6; j++)
                {
                    if(letter1 != null)
                    {
                        expectedCount++;
                        if(words[i].substring(pos1, pos1 + 1).equals(letter1))
                        {
                            count++;   
                        }

                    }
                    if(letter2 != null)
                    {
                        expectedCount++;
                        if(words[i].substring(pos2, pos2 + 1).equals(letter2))
                        {
                            count++;     
                        }

                    }
                    if(letter3 != null)
                    {
                        expectedCount++;
                        if(words[i].substring(pos3, pos3 + 1).equals(letter3))
                        {
                            count++;     
                        }

                    }
                    if(letter4 != null)
                    {
                        expectedCount++;
                        if(words[i].substring(pos4, pos4 + 1).equals(letter4))
                        {
                            count++;       
                        }

                    }
                    if(letter5 != null)
                    {
                        expectedCount++;
                        if(words[i].substring(pos5, pos5 + 1).equals(letter5))
                        {
                            count++;     
                        }

                    }

                }
                if(count == expectedCount)
                {
                    newWords.add(words[i]);

                }
                count = 0;
                expectedCount = 0;

            }

        }
        
        String[] newWordsArray = new String[newWords.size()];
        for(int i = 0; i < newWords.size(); i++)
        {
            newWordsArray[i] = newWords.get(i);
        }
        
        
        
        
        for(int i = 0; i < newWordsArray.length; i++)
        {
            for(int j = 0; j < notInclChar.length; j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].contains(notInclChar[j]))
                {
                    newWordsArray[i] = null;
                }

            }

        }
        
         for(int i = 0; i < newWords.size(); i++)
        {
            for(int j = 0; j < npos1.length; j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(0, 1).equals(npos1[j]))
                {
                    newWordsArray[i] = null;
                }
            }
            for(int j = 0; j < npos2.length; j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(1, 2).equals(npos2[j]))
                {
                    newWordsArray[i] = null;
                }
            }
            for(int j = 0; j < npos3.length; j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(2, 3).equals(npos3[j]))
                {
                    newWordsArray[i] = null;
                }
            }
            for(int j = 0; j < npos4.length; j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(3, 4).equals(npos4[j]))
                {
                    newWordsArray[i] = null;
                }
            }
            for(int j = 0; j < npos5.length; j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(4, 5).equals(npos5[j]))
                {
                    newWordsArray[i] = null;
                }
            }
            
            
        }

        
        
       
        ArrayList<String> finalLayer = new ArrayList<String>();
        
        
        for(String s: newWordsArray)
        {
            if(s != null && showAllWords == true)
            {
                
                finalLayer.add(s);
               
            }
            else if(s != null && showAllWords == false)
            {
                
                if(possibleWords.contains(s))
                {
                    
                    
                    finalLayer.add(s);
                    
                }

            }
        
        }

        System.out.print(finalLayer);

       

        
   
        

        
        

        
        
        
        
        

        
    }

    public static void main(String[] args) throws IOException
    {   
        

        //Enter all Dark Letters Here"
         String[] notInclCharacters = {};
         //Enter all Yellow Letters Here
         String[] inclCharacters = {};
 
         //Chosse whether you want to see all possible words, or all possible words that can be the answer
         boolean showAllPossibleGuesses = false;

         boolean showRankings = true;
         
         //Enter all yellow letters here in their positions
         String[] npos1 = {};
         String[] npos2 = {};
         String[] npos3 = {};
         String[] npos4 = {};
         String[] npos5 = {};
         
         //Enter green letters into their know position
         wordle(0, null, 1, null, 2, null, 3, null, 4, null, notInclCharacters, inclCharacters, npos1, npos2, npos3, npos4, npos5, showAllPossibleGuesses, showRankings);


         
        
       
        
        
        
    }
}
