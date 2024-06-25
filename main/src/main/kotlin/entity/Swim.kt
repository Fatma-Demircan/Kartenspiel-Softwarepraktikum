package entity

/**
 * @param currentPlayer current Spiler
 * @param players list Players
 * @param players tableCards List of table cards
 * @param cardDeck cardDeck
 * @param passCounter Counter for passing
 * @param knockCounter Counter for knocking
 */

class Swim(var currentPlayer: Player, var players: MutableList<Player>, var tableCards: MutableList<Card>, var cardDeck: CardDeck) {
    var passCounter: Int = 0
    var knockCounter: Int = -1

}