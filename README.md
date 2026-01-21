# ♠️ Blackjack & Statistics Simulator

A dual-language project (Java & JavaScript) implementing a fully functional Blackjack engine. This project features an interactive playable mode and a high-speed "Monte Carlo" simulation mode to analyze win/loss probabilities over 10,000 hands.


## 📋 Features

### 1. Interactive Gameplay
* **Full Game Logic:** Implements standard Blackjack rules including Hitting, Standing, and Dealer logic (Dealer draws to 16, stands on 17).
* **Dealer AI:** Automated dealer turns based on casino standard probability rules.
* **State Management:** Tracks player and dealer hands, detecting busts, ties (pushes), and blackjacks.

### 2. Statistical Simulation Engine
* **Monte Carlo Method:** Can simulate **10,000+ automated games** in under a second.
* **Strategy Analysis:** Tests a "Safe Strategy" (Hit if < 16) against the Dealer to calculate a statistically accurate win rate.
* **Data Reporting:** Outputs raw data (Wins, Losses, Ties) and calculated percentages.

---

## 🛠️ Technologies Used

| Component | Technology | Description |
| :--- | :--- | :--- |
| **Core Logic** | **Java** | Object-Oriented design (Classes for `Deck`, `Card`, `Hand`) and simulation loops. |
| **Web UI** | **JavaScript** | DOM manipulation for the interactive browser version. |
| **Frontend** | **HTML5 / CSS3** | Responsive dark-mode UI to match the developer portfolio. |

---

## 💻 How to Run (Java Version)

To run the command-line analysis tool locally:

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/yourusername/java-blackjack-sim.git](https://github.com/yourusername/java-blackjack-sim.git)
