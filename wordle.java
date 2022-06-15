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
    public static void wordle(int pos1, String letter1, int pos2, String letter2, int pos3, String letter3, int pos4, String letter4, int pos5, String letter5, ArrayList<String> notInclChar, ArrayList<String> inclChar, ArrayList<String> npos1, ArrayList<String> npos2, ArrayList<String> npos3, ArrayList<String> npos4, ArrayList<String> npos5, boolean showAllWords, boolean rank) throws IOException
    {
        Scanner scan = new Scanner(new File("wordleAllWords.txt")).useDelimiter("/s");
        Scanner scan2 = new Scanner(new File("wordleAllAnswers.txt")).useDelimiter("/s");
        
        String lineSeparator = "--------------------------------";
       


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
        
        if(inclChar.size() != 0)
        {
            for(int i = 0; i < words.length; i ++)
            {
                for(int j = 0; j < inclChar.size(); j ++)
                {
                    if( words[i] != null && inclChar.get(j) != null && !words[i].contains(inclChar.get(j)))
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
            for(int j = 0; j < notInclChar.size(); j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].contains(notInclChar.get(j)))
                {
                    newWordsArray[i] = null;
                }

            }

        }
        
         for(int i = 0; i < newWords.size(); i++)
        {
            for(int j = 0; j < npos1.size(); j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(0, 1).equals(npos1.get(j)))
                {
                    newWordsArray[i] = null;
                }
            }
            for(int j = 0; j < npos2.size(); j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(1, 2).equals(npos2.get(j)))
                {
                    newWordsArray[i] = null;
                }
            }
            for(int j = 0; j < npos3.size(); j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(2, 3).equals(npos3.get(j)))
                {
                    newWordsArray[i] = null;
                }
            }
            for(int j = 0; j < npos4.size(); j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(3, 4).equals(npos4.get(j)))
                {
                    newWordsArray[i] = null;
                }
            }
            for(int j = 0; j < npos5.size(); j++)
            {
                if(newWordsArray[i] != null && newWordsArray[i].substring(4, 5).equals(npos5.get(j)))
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
        System.out.println(lineSeparator);
        System.out.println("The program has narrowed it down to " + finalLayer.size() + " words");
        if(finalLayer.size() == 0)
        {
            System.out.println("[+]No Words Found, Program Terminated, Try correcting the format");
            System.exit(0);
        }
        if(finalLayer.size() == 1)
        {
           
            System.out.print(finalLayer);
            System.out.print("[+]The Wordle has been Solved, Terminating Program in 3");
            try{
                Thread.sleep(1000);
            }
            catch(java.lang.InterruptedException IOException)
            {
                System.out.print(IOException);
            }
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.print("[+]The Wordle has been Solved, Terminating Program in 2");
            try{
                Thread.sleep(1000);
            }
            catch(java.lang.InterruptedException IOException)
            {
                System.out.print(IOException);
            }
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.print("[+]The Wordle has been Solved, Terminating Program in 1");
            try{
                Thread.sleep(1000);
            }
            catch(java.lang.InterruptedException IOException)
            {
                System.out.print(IOException);
            }
            System.exit(0);
           

            
        }
        System.out.println(finalLayer);
        System.out.println(lineSeparator);

       

        
   
        

        
        

        
        
        
        
        

        
    }

    public static void main(String[] args) throws IOException
    {   
        String lineSeparator = "--------------------------------";
        Scanner lineScanner = new Scanner(System.in);

        String userEnteredWord;
        String characterFormOfWord;
        

        //Enter all Dark Letters Here"
         ArrayList<String> notInclCharacters = new ArrayList<String>();
         //Enter all Yellow Letters Here
         ArrayList<String> inclCharacters = new ArrayList<String>();
 
         //Chosse whether you want to see all possible words, or all possible words that can be the answer
         boolean showAllPossibleGuesses;

         boolean showRankings = true;
         
         //Enter all yellow letters here in their positions
         ArrayList<String> npos1 = new ArrayList<String>();
         ArrayList<String> npos2 = new ArrayList<String>();
         ArrayList<String> npos3 = new ArrayList<String>();
         ArrayList<String> npos4 = new ArrayList<String>();
         ArrayList<String> npos5 = new ArrayList<String>();
         

         

         String[] knownLetters = new String[5];
         
    
         System.out.println("\n\n\n\n\n");
         System.out.print("[+]Would you like the program to give your words from a list of all possible words, this makes the program less effecient, enter y for yes, n for no: ");
         String answer = lineScanner.nextLine();
         if(answer.contains("y"))
         {
            showAllPossibleGuesses = true;
         }
         else
         {
            showAllPossibleGuesses = false;
         }
         System.out.print("\n\n\n\n\n");
         System.out.print("[+]Welcome to wordleSolver, begin by entering any word of your choice into the website, I reccomend [salet]");
         





         while(true){
            System.out.println("\n\n\n\n\n");

            System.out.println("[+]Please type the word you entered into wordle:");
            userEnteredWord = lineScanner.nextLine();
            System.out.println("\n\n\n\n\n\n");
            System.out.println(lineSeparator);
            System.out.print("[+]Please Enter the result in the format that follows\n" + lineSeparator + "\n[+]If the 1st square is green, the 2nd, 3rd, and 4th are grey, and the 5th is yellow you would enter [okkki]\n" + lineSeparator + "\n[o=green, i=yellow, k=grey]:");
            characterFormOfWord = lineScanner.nextLine();



            for(int i = 0; i < 5; i++)
            {
                
                if(characterFormOfWord.substring(i, i + 1).equals("o"))
                {
                    knownLetters[i] = userEnteredWord.substring(i, i+1);
                    
                }
                if(characterFormOfWord.substring(i, i + 1).equals("i"))
                {
                    inclCharacters.add(userEnteredWord.substring(i, i+1));
                    if(i == 0)
                    {
                        npos1.add(userEnteredWord.substring(i, i+1));
                    }
                    if(i == 1)
                    {
                        npos2.add(userEnteredWord.substring(i, i+1));
                    }
                    if(i == 2)
                    {
                        npos3.add(userEnteredWord.substring(i, i+1));
                    }
                    if(i == 3)
                    {
                        npos4.add(userEnteredWord.substring(i, i+1));
                    }
                    if(i == 4)
                    {
                        npos5.add(userEnteredWord.substring(i, i+1));
                    }
                    
                    
                }
                if(characterFormOfWord.substring(i, i + 1).equals("k"))
                {
                    
                        notInclCharacters.add(userEnteredWord.substring(i, i+1));
                    

                    
                }
                
            }

            
                
            for(int i = 0; i < inclCharacters.size(); i++)
            {
                for(int j = 0; j < notInclCharacters.size(); j++)
                {
                    if(inclCharacters.get(i).equals(notInclCharacters.get(j)))
                    {
                        notInclCharacters.remove(j);
                    }
                }
            }

            for(int i = 0; i < knownLetters.length; i++)
            {
                for(int j = 0; j < notInclCharacters.size(); j++)
                {
                    if(knownLetters[i] != null && knownLetters[i].equals(notInclCharacters.get(j)))
                    {
                        notInclCharacters.remove(j);
                    }
                }
            }
            

            
                
                


            wordle(0, knownLetters[0], 1, knownLetters[1], 2, knownLetters[2], 3, knownLetters[3], 4, knownLetters[4], notInclCharacters, inclCharacters, npos1, npos2, npos3, npos4, npos5, showAllPossibleGuesses, showRankings);
            
        }
        
                        
         


         
         
         //Enter green letters into their know position


         
        
       
        
        
        
    }
}
