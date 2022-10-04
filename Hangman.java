import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +                 // no misses
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +                 // one miss
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +                 // two misses
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +                // three misses
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +               // four misses
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +               // five misses
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +               // six misses, player loses
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};



    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); 
        
        // Assigns random word to String variable 
        String word = randomWord(); 

        // Creates a new array for placeholders using random word length
        char[] placeholders = new char[word.length()]; 
        for (int i = 0; i < placeholders.length; i++) { 
            placeholders[i] = '_'; 
        }

        // Initiates misses variable 
        int misses = 0; 

        // Creates a missed guesses array with a length of 6 (since 6 is tha max guesses)
        char[] missedGuesses = new char[6]; 

        // Creates a while loop while misses is less than 6 to ask user for guesses
        while (misses < 6) { 
            System.out.println(gallows[misses]);    // prints gallows at each position based on guesses

            System.out.print("Word: ");
            printPlaceholders(placeholders);   
            System.out.print("\n");

            System.out.print("Misses:   ");
            printMissedGuesses(missedGuesses);
            System.out.print("\n");

            System.out.print("Guess:  ");
            char guess = scan.nextLine().charAt(0);     // recieve char guess 
            System.out.print("\n");

            // updates placeholders based on guess, if guess misses, increases counter and records miss
            if (checkGuess(word, guess)) { 
                updatePlaceholders(word, placeholders, guess); 
            } else { 
                missedGuesses[misses] = guess; 
                misses++; 
            }
        

            // if player guesses word right, system prints word and breaks loop
            if (Arrays.equals(placeholders, word.toCharArray())) { 
                System.out.print(gallows[misses]);
                System.out.print("\nWord:   ");
                printPlaceholders(placeholders);
                System.out.println("\nNICE JOB!");
                break;
            }
        }
        
        // if player misses 6 times, game is over and displays word 
        if (misses == 6) { 
            System.out.print(gallows[6]);
            System.out.println("\nRIP!");
            System.out.println("\nThe word was: '" + word + "'");
        }

        scan.close();
    }
        
    


    /**
     * Function name - randomWord
     * Description: 
     *  - Populates random word using length of "words" array
     *  - Creates random index using randomDouble * length of words array
     *  - Returns String at the random index 
     * @return words (at index)
     */
    public static String randomWord() { 
        int numWords = words.length; 
        double randomDouble = Math.random(); 
        int randomIndex = (int)(randomDouble * numWords); 
        return words[randomIndex]; 
    }
    


    /**
     * Function name - checkGuess
     * Description: 
     *  - checks if guess and word match 
     *  - returns true if guess matches word 
     * @param word (String)
     * @param guess (char)
     * @return true or false
     */
    public static boolean checkGuess(String word, char guess) {
        
        for (int i = 0; i < word.length(); i++) { 
            if (word.charAt(i) == guess) { 
                return true; 
            }
        }
        return false; 
    }



    /**
     * Function name - updatePlaceholders
     * Description: 
     *  - indexs through word to update placeholder 
     *  - replaces placeholder with correctly guessed character
     * @param word (String)
     * @param placeholders (char[])
     * @param guess (char)
     */
    public static void updatePlaceholders(String word, char[] placeholders, char guess) {
        
        for (int j = 0; j < word.length(); j++) { 
            if (word.charAt(j) == guess) { 
                placeholders[j] = guess; 
            }
        }
    }



    /** 
     * Function name - printPlaceholders
     * Description: 
     *  - Takes length of char array placeholders and prints it 
     * @param char[] (placeholders)
     */
    public static void printPlaceholders(char[] placeholders) { 
        for (int i = 0; i < placeholders.length; i++) {
            System.out.print(" " + placeholders[i]);
        }
        System.out.print("\n");
    }



    /**
     * Function name - printMissedGuesses 
     * Description: 
     *  - Takes char string containing misses and prints each character that misses
     * @param char[][] (misses)
     */
    public static void printMissedGuesses(char[] misses) { 
        for (int i = 0; i < misses.length; i++) { 
            System.out.print(misses[i]);
        }
        System.out.print("\n");
    }


}





