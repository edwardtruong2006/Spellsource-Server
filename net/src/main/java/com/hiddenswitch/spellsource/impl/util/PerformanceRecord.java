package com.hiddenswitch.spellsource.impl.util;

import jskills.*;
import jskills.trueskill.TwoPlayerTrueSkillCalculator;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class PerformanceRecord implements Serializable {
	public String queueId;
	public int wins;
	public int losses;
	public int totalGames;
	public Date lastGame;
	public RatingRecord rating;

	public static PerformanceRecord create(String queueId) {
		PerformanceRecord record = new PerformanceRecord();
		GameInfo gameInfo = GameInfo.getDefaultGameInfo();
		record.queueId = queueId;
		record.rating = RatingRecord.create(gameInfo.getDefaultRating());
		return record;
	}

	/**
	 * Draws the two players if {@code draw} is {@code true}. Otherwise, {@code player1} is interpreted as the winner
	 * and {@code player2} is interpreted as the loser.
	 *
	 * @param player1 The first player, or the winner if {@code draw} is {@code false}
	 * @param player2 The second player, or the loser if {@code draw} is {@code false}
	 * @param draw    {@code true} if the performance records should be updated as though there were a draw.
	 */
	public static void update(PerformanceRecord player1, PerformanceRecord player2, boolean draw) {
		player1.lastGame = new Date();
		player2.lastGame = (Date) player1.lastGame.clone();

		player1.wins++;
		player2.losses++;

		player1.totalGames++;
		player2.totalGames++;

		// Player 1 is always the winner
		Player<Integer> rp1 = new PlayerInfo<>(1);
		Player<Integer> rp2 = new PlayerInfo<>(2);
		GameInfo gameInfo = GameInfo.getDefaultGameInfo();

		Team team1 = new TeamInfo().addPlayer(rp1, player1.rating.toRating());
		Team team2 = new TeamInfo().addPlayer(rp2, player2.rating.toRating());
		Collection<Team> teams = TeamInfo.concat(team1, team2);

		TwoPlayerTrueSkillCalculator calculator = new TwoPlayerTrueSkillCalculator();
		Map<Player, Rating> newRatings = calculator.calculateNewRatings(gameInfo, teams, 1, draw ? 1 : 2);
		player1.rating = RatingRecord.create(newRatings.get(rp1));
		player2.rating = RatingRecord.create(newRatings.get(rp2));

	}

	/**
	 * Updates the performance records of the provided winner and loser.
	 *
	 * @param winner
	 * @param loser
	 */
	public static void update(PerformanceRecord winner, PerformanceRecord loser) {
		update(winner, loser, false);
	}
}
