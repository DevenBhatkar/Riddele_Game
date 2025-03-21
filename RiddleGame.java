import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class RiddleGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static double score = 0;
    private static int gamesPlayed = 0;
    private static int correctAnswers = 0;
    private static final List<Riddle> riddles = new ArrayList<>();
    private static final Map<String, List<Riddle>> riddleCategories = new HashMap<>();
    private static final String[] DIFFICULTY_LEVELS = {"Easy", "Medium", "Hard"};
    private static String currentDifficulty = "Medium";

    static class Riddle {
        private final String question;
        private final String answer;
        private final String hint;
        private final String category;
        private final String difficulty;

        public Riddle(String question, String answer, String hint, String category, String difficulty) {
            this.question = question;
            this.answer = answer;
            this.hint = hint;
            this.category = category;
            this.difficulty = difficulty;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        public String getHint() {
            return hint;
        }
        
        public String getCategory() {
            return category;
        }
        
        public String getDifficulty() {
            return difficulty;
        }
    }

    public static void main(String[] args) {
        initializeRiddles();
        displayWelcomeScreen();
        
        boolean exitGame = false;
        while (!exitGame) {
            displayMainMenu();
            int choice = getValidNumberInput(1, 4);
            
            switch (choice) {
                case 1: // Play Game
                    playGame();
                    break;
                case 2: // Change Difficulty
                    changeDifficulty();
                    break;
                case 3: // View Statistics
                    displayStatistics();
                    break;
                case 4: // Exit
                    exitGame = true;
                    break;
            }
        }
        
        System.out.println("\nThank you for playing the Riddle Game!");
        System.out.println("Final Statistics:");
        displayStatistics();
        scanner.close();
    }
    
    private static void displayWelcomeScreen() {
        clearScreen();
        System.out.println("*************************************************");
        System.out.println("*                RIDDLE GAME                   *");
        System.out.println("*                                               *");
        System.out.println("*  Test your wit with challenging riddles!     *");
        System.out.println("*  Earn points, get hints, and have fun!       *");
        System.out.println("*************************************************");
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private static void displayMainMenu() {
        clearScreen();
        System.out.println("===== MAIN MENU =====");
        System.out.println("Current Difficulty: " + currentDifficulty);
        System.out.println("1. Play Game");
        System.out.println("2. Change Difficulty");
        System.out.println("3. View Statistics");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }
    
    private static void changeDifficulty() {
        clearScreen();
        System.out.println("===== DIFFICULTY SETTINGS =====");
        System.out.println("Current Difficulty: " + currentDifficulty);
        System.out.println("\nAvailable Difficulty Levels:");
        for (int i = 0; i < DIFFICULTY_LEVELS.length; i++) {
            System.out.println((i + 1) + ". " + DIFFICULTY_LEVELS[i]);
        }
        
        System.out.print("\nSelect difficulty (1-" + DIFFICULTY_LEVELS.length + "): ");
        int choice = getValidNumberInput(1, DIFFICULTY_LEVELS.length);
        currentDifficulty = DIFFICULTY_LEVELS[choice - 1];
        
        System.out.println("\nDifficulty changed to: " + currentDifficulty);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void displayStatistics() {
        clearScreen();
        System.out.println("===== GAME STATISTICS =====");
        System.out.println("Total Games Played: " + gamesPlayed);
        System.out.println("Total Score: " + score);
        System.out.println("Correct Answers: " + correctAnswers);
        
        if (gamesPlayed > 0) {
            double averageScore = score / gamesPlayed;
            System.out.printf("Average Score per Game: %.2f\n", averageScore);
            
            double successRate = (double) correctAnswers / gamesPlayed * 100;
            System.out.printf("Success Rate: %.2f%%\n", successRate);
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void initializeRiddles() {
        // Initialize category lists
        riddleCategories.put("General", new ArrayList<>());
        riddleCategories.put("Word Play", new ArrayList<>());
        riddleCategories.put("Math", new ArrayList<>());
        riddleCategories.put("Logic", new ArrayList<>());
        
        // General Riddles
        addRiddle("I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I?", 
                "echo", "I repeat what you say.", "General", "Medium");
        
        addRiddle("The more you take, the more you leave behind. What am I?", 
                "footsteps", "Think about walking.", "General", "Easy");
        
        addRiddle("What has keys but no locks, space but no room, and you can enter but not go in?", 
                "keyboard", "You use it to type.", "General", "Medium");
        
        addRiddle("What gets wet while drying?", 
                "towel", "You use it after a shower.", "General", "Easy");
        
        addRiddle("I'm tall when I'm young, and I'm short when I'm old. What am I?", 
                "candle", "I provide light.", "General", "Medium");
        
        // Word Play Riddles
        addRiddle("What has a head and a tail but no body?", 
                "coin", "It's money.", "Word Play", "Easy");
        
        addRiddle("What can travel around the world while staying in a corner?", 
                "stamp", "You put it on an envelope.", "Word Play", "Medium");
        
        addRiddle("Forward I am heavy, but backward I am not. What am I?", 
                "ton", "Read me backwards.", "Word Play", "Hard");
        
        // Math Riddles
        addRiddle("If you have me, you want to share me. If you share me, you don't have me. What am I?", 
                "secret", "It's something private.", "Logic", "Medium");
        
        addRiddle("A man is looking at a photograph of someone. His friend asks who it is. The man replies, 'Brothers and sisters, I have none. But that man's father is my father's son.' Who is in the photograph?", 
                "his son", "Think about family relationships.", "Logic", "Hard");
        
        addRiddle("What has many keys but can't open a single lock?", 
                "piano", "It's a musical instrument.", "General", "Medium");
        
        addRiddle("What has one eye but can't see?", 
                "needle", "You use it for sewing.", "General", "Easy");
        
        addRiddle("What breaks yet never falls, and what falls yet never breaks?", 
                "day and night", "Think about time.", "Logic", "Hard");
        
        addRiddle("I have cities, but no houses. I have mountains, but no trees. I have water, but no fish. What am I?", 
                "map", "You use it for navigation.", "General", "Medium");
        
        addRiddle("What is full of holes but still holds water?", 
                "sponge", "You use it for cleaning.", "General", "Easy");
    }
    
    private static void addRiddle(String question, String answer, String hint, String category, String difficulty) {
        Riddle riddle = new Riddle(question, answer, hint, category, difficulty);
        riddles.add(riddle);
        
        // Add to appropriate category
        if (riddleCategories.containsKey(category)) {
            riddleCategories.get(category).add(riddle);
        } else {
            List<Riddle> newCategory = new ArrayList<>();
            newCategory.add(riddle);
            riddleCategories.put(category, newCategory);
        }
    }
    
    private static void playGame() {
        clearScreen();
        System.out.println("===== GAME STARTED =====");
        System.out.println("Difficulty: " + currentDifficulty);
        
        // Create a list of riddles matching the current difficulty
        List<Riddle> gameRiddles = new ArrayList<>();
        for (Riddle riddle : riddles) {
            if (riddle.getDifficulty().equals(currentDifficulty)) {
                gameRiddles.add(riddle);
            }
        }
        
        if (gameRiddles.isEmpty()) {
            System.out.println("No riddles available for the current difficulty level.");
            System.out.println("Press Enter to return to the main menu...");
            scanner.nextLine();
            return;
        }
        
        double roundScore = 0;
        int roundCorrect = 0;
        int riddlesAnswered = 0;
        
        boolean playAgain = true;
        while (playAgain && !gameRiddles.isEmpty()) {
            riddlesAnswered++;
            double points = playRound(gameRiddles);
            roundScore += points;
            if (points > 0) {
                roundCorrect++;
            }
            
            System.out.print("\nDo you want to solve another riddle? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }
        
        // Update global statistics
        score += roundScore;
        gamesPlayed++;
        correctAnswers += roundCorrect;
        
        System.out.println("\n===== ROUND SUMMARY =====");
        System.out.println("Riddles Attempted: " + riddlesAnswered);
        System.out.println("Correct Answers: " + roundCorrect);
        System.out.println("Round Score: " + roundScore);
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static double playRound(List<Riddle> gameRiddles) {
        // Select a random riddle
        int riddleIndex = random.nextInt(gameRiddles.size());
        Riddle currentRiddle = gameRiddles.get(riddleIndex);
        
        // Remove the riddle so it doesn't repeat in this game session
        gameRiddles.remove(riddleIndex);
        
        System.out.println("\n---------------------------------");
        System.out.println("Category: " + currentRiddle.getCategory());
        System.out.println("Difficulty: " + currentRiddle.getDifficulty());
        System.out.println("\nRiddle: " + currentRiddle.getQuestion());
        
        // Start timing the answer
        long startTime = System.currentTimeMillis();
        
        System.out.print("\nYour answer: ");
        String userAnswer = scanner.nextLine().trim().toLowerCase();
        
        // Calculate time taken to answer
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        
        double pointsEarned = 0;
        
        if (userAnswer.equals(currentRiddle.getAnswer().toLowerCase())) {
            System.out.println("\nCorrect! +1 point");
            
            // Bonus points for quick answers (under 30 seconds)
            if (timeElapsed < 30000) {
                double timeBonus = 0.5 * (1 - (timeElapsed / 30000.0));
                System.out.printf("Quick answer bonus: +%.2f points!\n", timeBonus);
                pointsEarned = 1 + timeBonus;
            } else {
                pointsEarned = 1;
            }
            
            // Difficulty bonus
            if (currentRiddle.getDifficulty().equals("Hard")) {
                System.out.println("Hard difficulty bonus: +0.5 points!");
                pointsEarned += 0.5;
            }
        } else {
            System.out.println("\nIncorrect!");
            System.out.println("Hint: " + currentRiddle.getHint());
            System.out.print("\nTry again: ");
            userAnswer = scanner.nextLine().trim().toLowerCase();
            
            if (userAnswer.equals(currentRiddle.getAnswer().toLowerCase())) {
                System.out.println("\nCorrect on second try! +0.5 points");
                pointsEarned = 0.5;
                
                // Reduced difficulty bonus for second attempt
                if (currentRiddle.getDifficulty().equals("Hard")) {
                    System.out.println("Hard difficulty bonus: +0.25 points!");
                    pointsEarned += 0.25;
                }
            } else {
                System.out.println("\nStill incorrect. The answer was: " + currentRiddle.getAnswer());
                pointsEarned = 0;
            }
        }
        
        System.out.printf("Points earned: %.2f\n", pointsEarned);
        System.out.println("Time taken: " + formatTime(timeElapsed));
        
        return pointsEarned;
    }
    
    private static String formatTime(long milliseconds) {
        return String.format("%d seconds", TimeUnit.MILLISECONDS.toSeconds(milliseconds));
    }
    
    private static int getValidNumberInput(int min, int max) {
        int choice = -1;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
                
                if (choice >= min && choice <= max) {
                    validInput = true;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        
        return choice;
    }
    
    private static void clearScreen() {
        // This is a simple way to "clear" the console
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
