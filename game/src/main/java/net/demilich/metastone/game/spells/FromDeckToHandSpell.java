package net.demilich.metastone.game.spells;

import com.github.fromage.quasi.fibers.Suspendable;
import net.demilich.metastone.game.GameContext;
import net.demilich.metastone.game.Player;
import net.demilich.metastone.game.cards.Card;
import net.demilich.metastone.game.cards.CardList;
import net.demilich.metastone.game.entities.Entity;
import net.demilich.metastone.game.entities.EntityType;
import net.demilich.metastone.game.spells.desc.SpellArg;
import net.demilich.metastone.game.spells.desc.SpellDesc;
import net.demilich.metastone.game.spells.desc.filter.EntityFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Moves a card from the deck to the hand. Triggers a draw event.
 * <p>
 * If a {@code target} is specified and it's inside the caster's deck, that card is the one that is moved.
 * <p>
 * If a {@link SpellArg#CARD} is specified, it is interpreted as a "replacement card" in case no cards are found that
 * satisfy the {@link SpellArg#CARD_FILTER} or the deck is empty.
 * <p>
 * Otherwise, a {@link net.demilich.metastone.game.spells.desc.filter.CardFilter} is evaluated against a {@link
 * net.demilich.metastone.game.spells.desc.source.DeckSource} from the point of view of the caster.
 * <p>
 * For example, to draw a 10-cost minion from the caster's deck:
 * <pre>
 *   {
 *     "class": "FromDeckToHandSpell",
 *     "value": 1,
 *     "cardFilter": {
 *       "class": "CardFilter",
 *       "cardType": "MINION",
 *       "manaCost": 10
 *     },
 *     "targetPlayer": "SELF"
 *   }
 * </pre>
 * <p>
 * This effect is to be rolled into the {@link DrawCardSpell}.
 */
public class FromDeckToHandSpell extends Spell {

	private static Logger logger = LoggerFactory.getLogger(FromDeckToHandSpell.class);

	@Override
	@Suspendable
	protected void onCast(GameContext context, Player player, SpellDesc desc, Entity source, Entity target) {
		checkArguments(logger, context, source, desc, SpellArg.VALUE, SpellArg.CARD_FILTER, SpellArg.CARD);
		if (target != null && target.getEntityType() == EntityType.CARD) {
			Card card = (Card) target;
			if (player.getDeck().containsCard(card)) {
				context.getLogic().receiveCard(player.getId(), card, source, true);
				if (desc.getSpell() != null) {
					SpellUtils.castChildSpell(context, player, desc.getSpell(), source, target, card);
				}
			}
			return;
		}

		int value = desc.getValue(SpellArg.VALUE, context, player, target, source, 1);
		String replacementCard = (String) desc.get(SpellArg.CARD);

		EntityFilter cardFilter = (EntityFilter) desc.get(SpellArg.CARD_FILTER);
		CardList relevantCards = null;
		if (cardFilter != null) {
			relevantCards = SpellUtils.getCards(player.getDeck(), card -> cardFilter.matches(context, player, card, source));
		} else {
			relevantCards = SpellUtils.getCards(player.getDeck(), null);
		}
		for (int i = 0; i < value; i++) {
			Card card = null;
			if (!relevantCards.isEmpty()) {
				card = context.getLogic().getRandom(relevantCards);
				if (desc.getBool(SpellArg.EXCLUSIVE)) {
					final String cardId = card.getCardId();
					relevantCards.removeIf(c -> c.getCardId().equals(cardId));
				} else relevantCards.remove(card);
			} else if (replacementCard != null) {
				card = context.getCardById(replacementCard);
			}

			if (card != null) {
				context.getLogic().receiveCard(player.getId(), card, source, true);
				if (desc.getSpell() != null) {
					SpellUtils.castChildSpell(context, player, desc.getSpell(), source, target, card);
				}
			}
		}
	}
}
