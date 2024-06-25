package entity

class CardDeck(var cards: MutableList<Card>){
    /**
     * @param cards List of cards
     * @return List of drawn cards we return
     */

    fun draw(amount: Int):List<Card> {
       // require (amount in 1..cards.size) { "can't draw $amount cards from $cards" }
        if(amount !in 1..cards.size) return emptyList()
        return List(amount) { cards.removeFirst() }

    }
}