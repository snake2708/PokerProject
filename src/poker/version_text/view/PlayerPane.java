package poker.version_text.view;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import poker.version_text.model.Card;
import poker.version_text.model.HandType;
import poker.version_text.model.Player;

public class PlayerPane extends VBox {
	private Label lblName = new Label();
	private HBox hboxCards = new HBox();
	private Label lblEvaluation = new Label("--");
	private Label lblWinLose = new Label("--");
	// Link to player object
	private Player player;

	public PlayerPane() {
		super(); // Always call super-constructor first !!
		this.getStyleClass().add("player"); // CSS style class

		// Add child nodes
		this.getChildren().addAll(lblName, hboxCards, lblEvaluation, lblWinLose);

		VBox.setVgrow(hboxCards, Priority.ALWAYS); // On resize, expand the card area

		// Add CardLabels for the cards
		for (int i = 0; i < 5; i++) {
			Label lblCard = new CardLabel();
			HBox.setHgrow(lblCard, Priority.ALWAYS); // On resize, expand the cards
			hboxCards.getChildren().add(lblCard);
		}
	}

	public void setPlayer(Player player) {
		this.player = player;
		updatePlayerDisplay(); // Immediately display the player information
	}

	public void updatePlayerDisplay() {
		lblName.setText(player.getPlayerName());
		lblName.setOpacity(1);
		lblName.setTextFill(Color.BLACK);

		for (int i = 0; i < Player.HAND_SIZE; i++) {
			Card card = null;
			if (player.getCards().size() > i)
				card = player.getCards().get(i);
			CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
			cl.setCard(card);
			HandType evaluation = player.evaluateHand();
			if (evaluation != null)
				lblEvaluation.setText(evaluation.toString());
			else
				lblEvaluation.setText("--");

		}

	}

	// method to display the winner or loser of the round
	public void updateWinners(ArrayList<Player> winnerList) {
		if (winnerList != null) {
			if (winnerList.contains(this.player)) {
				lblWinLose.setText("Winner");
			} else {
				lblWinLose.setText("Loser");
			}
		}
	}

}
