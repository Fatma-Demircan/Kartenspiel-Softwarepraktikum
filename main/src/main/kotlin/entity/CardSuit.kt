package entity

/**
 * Enum to distinguish between the four possible suits in a french-suited card game:
 * clubs, spades, hearts, or diamonds
 * @param CLUBS cardsuit
 * @param SPADES cardsuit
 * @param HEARTS cardsuit
 * @param DIAMONDS cardsuit
 */
enum class CardSuit {
    CLUBS,
    SPADES,
    HEARTS,
    DIAMONDS,
    ;

    /**
     * provide a single character to represent this suit.
     * Returns one of: ♣/♠/♥/♦
     */
    override fun toString() = when(this) {
        CLUBS -> "♣"
        SPADES -> "♠"
        HEARTS -> "♥"
        DIAMONDS -> "♦"
    }


}