import java.util.*;

public class Wordle {
  private static String[] WORD_LIST = new String[] { "zesty", "zebra", "zippy", "zonal", "zones", "wages", "wains",
      "wakes", "wales", "walks", "xenon", "xylem", "xylyl", "xebec", "xrays", "fancy", "felon", "fever", "fight",
      "final", "rabbi", "roose", "resin", "revel", "rater", "eager", "early", "earth", "elbow", "elect", "udder",
      "under", "uncle", "union", "unity", "organ", "onion ", "older", "ocean ", "olive ", "pedal ", " pixel", "plain",
      "plane", "penny", "panic", "hydro", "helix", "harpy", "heist", "horde", "jolly", "juror", "joker", "jumpy",
      "judge", "makes", "macaw", "macro", "micro", "magic", "nacho", "ninja", "navvy", "nymph", "niece", "start",
      "shown", "scrap", "stare", "smear", "train", "track", "timed", "thyme", "tours", "knead", "kites", "kilos",
      "knelt", "knoll", "label", "labor", "lacks", "lapel", "lamas", "icing", "igloo", "index", "inter", "irony",
      "gamer", "golem", "greed", "ghoul", "gamma", "dough", "dream", "doubt", "death", "darts", "bacon", "basic",
      "beach", "beard", "beats", "quasi", "quail", "quads", "quack", "queen", "apple", "adult", "asset", "above",
      "angry", "court", "cases", "check", "chick", "chain", "yoink", "yearn", "young", "youth", "yacht", "video",
      "valve", "vapor", "valid", "valet" };

  // Word Acessses When game is run
  private String currentWord;
  private ArrayList<String> guesses;
  private ArrayList<String> displays;

  public Wordle() {
    currentWord = selectWord();
    guesses = new ArrayList<String>();
    displays = new ArrayList<String>();
    playGame();
  }

  private String selectWord() {
    return (WORD_LIST[(int) (Math.random() * WORD_LIST.length)]);
  }

  // Only accept 5 letter guesses and only accept letters
  // Format the guess to be lowercase
  // If a letter is in the correct spot, display that letter
  // If a letter is not in the word, display a hyphen "-"
  // If a letter is in the word, but not in the correct spot, and there are more
  // remaining that are not accounted for, display an asteristick "*"
  // example : word is READY, guess made is ARRAY, we see * * - - -
  // Subtract 1 from your remaining guesses after a valid guess
  private String makeGuess() {
    Scanner input = new Scanner(System.in);
    String resultToDisplay = "";

    ArrayList<Character> charachterList = new ArrayList<Character>();
    ArrayList<Integer> numRemaining = new ArrayList<Integer>();

    // Loop through our word
    // For each charachter, check if it is in the charachterList alreay
    // If not, add a new charachter to the character list and add a "1" to the end
    // of the numRemaining List
    // If it is already in charachterList, increment at the same index in the
    // remaining list
    for (int i = 0; i < currentWord.length(); i++) {
      char charachterToCheck = currentWord.charAt(i);
      int indexInList = -1;

      for (int j = 0; j < charachterList.size(); j++) {
        if (charachterToCheck == charachterList.get(i)) {
          indexInList = j;
        }
      }
      // A duplicate was found
      if (indexInList >= 0) {
        numRemaining.set(indexInList, numRemaining.get(indexInList) + 1);
      }
      // New Letter found
      else {
        charachterList.add(charachterToCheck);
        numRemaining.add(1);
      }
    }
    // End of loop, lists are created

    char[] stringBuilder = new char[5];
    boolean isValid = true;
    String guess = "";
    while (!isValid) {
      System.out.println("Make a 5 letter Guess:");
      guess = input.nextLine().toLowerCase();
      isValid = true;
      for (int i = 0; i < guess.length(); i++) {
        if (!Character.isAlphabetic(guess.charAt(i))) {
          isValid = false;
        }
      }
      if (guess.length() != 5) {
        isValid = false;
      }
    }
    // end of loop, we have a valid guess
    guesses.add(guess);

    for (int i = 0; i < guess.length(); i++) {
      if (guess.charAt(i) == currentWord.charAt(i)) {
        stringBuilder[i] = guess.charAt(i);
        for (int j = 0; j < charachterList.size(); j++) {
          if (guess.charAt(i) == charachterList.get(i)) {
            numRemaining.set(i, numRemaining.get(i) - 1);
          }
        }
      }
    }
    // sets asterisks for things that Dont match, exist somewhere, else, have "num
    // remaing" greater than 0
    // Hyphens instead, if numRemaing is 0
    for (int i = 0; i < guess.length(); i++) {
      boolean exists = false;
      for (int j = 0; j < charachterList.size(); j++) {
        if (guess.charAt(i) == charachterList.get(j)) {
          exists = true;
        }
      }
      if (guess.charAt(i) != currentWord.charAt(i) && exists) {
        for (int j = 0; j < charachterList.size(); j++) {
          if (guess.charAt(i) == charachterList.get(j)) {
            if (numRemaining.get(j) > 0) {
              stringBuilder[i] = 'i';
              numRemaining.set(j, numRemaining.get(j) - 1);
            } else {
              stringBuilder[i] = '-';
            }
          }
        }

        for (int i = 0; i < guess.length(); i++) {
          boolean exists = false;
          for (int j = 0; j < characterList.size(); j++) {
            if (guess.charAt(i) == charachterList.get(j)) {
              exists = true;
            }
          }
          if (!exists) {
            stringBuilder[i] = '-';
          }
        }
        for (int i = 0; i < stringBuilder.length; i++) {
          resultToDisplay = resultToDisplay + stringBuilder[i];
        }
      }
    }
    return resultToDisplay;
  }

  private void playGame() {
    while(guesses.size() < 6){
      displays.add(makeGuess());
      for(int  i = 0; i < guesses.size(); i++){
        System.out.println(guesses.get(i));
      }
      if(guesses.get(guesses.size - 1).equals(currentWord)){
        System.out.println("You Win!!!");
        return;
      }
      
    }
    System.out.println("You Lose!!! The word was"+ currentWord);
  }
}