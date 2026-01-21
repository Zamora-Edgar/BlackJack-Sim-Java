import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// --- CLASSES ---

class Card {
    String suit, rank;
    int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String toString() { return rank + " of " + suit; }
}

class Deck {
    ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(suit, ranks[i], values[i]));
            }
        }
    }

    public void shuffle() { Collections.shuffle(cards); }
    
    public Card draw() { return cards.remove(0); }
}

// --- MAIN GAME LOGIC ---

public class Blackjack {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- BLACKJACK & STATISTICS SIMULATOR ---");
        System.out.println("1. Play Interactive Mode");
        System.out.println("2. Run Simulation (10,000 Hands)");
        System.out.print("Select mode: ");
        
        String choice = scanner.next();
        
        if (choice.equals("1")) {
            playInteractive();
        } else {
            runSimulation(10000);
        }
    }

    // MODE 1: HUMAN PLAYER
    public static void playInteractive() {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        int playerSum = 0;
        int dealerSum = 0;
        
        // Initial Deal
        Card p1 = deck.draw();
        Card p2 = deck.draw();
        Card d1 = deck.draw();
        Card d2 = deck.draw();

        playerSum += p1.value + p2.value;
        dealerSum += d1.value + d2.value;

        System.out.println("\nYour hand: " + p1 + ", " + p2 + " (Total: " + playerSum + ")");
        System.out.println("Dealer shows: " + d1);

        // Player Turn
        while (playerSum < 21) {
            System.out.print("Hit (h) or Stand (s)? ");
            String move = scanner.next();
            if (move.equalsIgnoreCase("s")) break;

            Card newCard = deck.draw();
            playerSum += newCard.value;
            System.out.println("You drew: " + newCard + " (Total: " + playerSum + ")");
        }

        if (playerSum > 21) {
            System.out.println("Bust! You lose.");
            return;
        }

        // Dealer Turn (AI: Hit until 17)
        System.out.println("Dealer's hidden card: " + d2);
        while (dealerSum < 17) {
            Card newCard = deck.draw();
            dealerSum += newCard.value;
            System.out.println("Dealer drew: " + newCard + " (Total: " + dealerSum + ")");
        }

        // Result
        if (dealerSum > 21 || playerSum > dealerSum) {
            System.out.println("YOU WIN!");
        } else if (playerSum == dealerSum) {
            System.out.println("PUSH (Tie).");
        } else {
            System.out.println("DEALER WINS.");
        }
    }

    // MODE 2: SIMULATION (DEALER VS DEALER AI)
    public static void runSimulation(int games) {
        int wins = 0;
        int losses = 0;
        int ties = 0;

        System.out.println("\nRunning " + games + " simulated games...");

        for (int i = 0; i < games; i++) {
            Deck deck = new Deck();
            deck.shuffle();

            int pSum = deck.draw().value + deck.draw().value;
            int dSum = deck.draw().value + deck.draw().value;

            // Simple AI Strategy: Always hit if under 16
            while (pSum < 16) {
                pSum += deck.draw().value;
            }

            // If player didn't bust, dealer plays
            if (pSum <= 21) {
                while (dSum < 17) {
                    dSum += deck.draw().value;
                }
            }

            // Track Stats
            if (pSum > 21) losses++;
            else if (dSum > 21) wins++;
            else if (pSum > dSum) wins++;
            else if (pSum < dSum) losses++;
            else ties++;
        }

        System.out.println("--- SIMULATION RESULTS ---");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Ties: " + ties);
        double winRate = (double) wins / games * 100;
        System.out.println("Win Rate (Simple Strategy): " + String.format("%.2f", winRate) + "%");
    }
}
