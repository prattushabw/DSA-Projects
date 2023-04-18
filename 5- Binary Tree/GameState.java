/**
 * This class represents the state of the game and determines if the game is not over or if student won or lost
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

public enum GameState {

        GAME_OVER_WIN("YOU WIN"), GAME_OVER_LOSE("YOU LOSE"), GAME_NOT_OVER("");

        private String state;
        GameState(String state) {
            this.state = state;
        }

        public String getGameState() {
            return state;
        }
    }


