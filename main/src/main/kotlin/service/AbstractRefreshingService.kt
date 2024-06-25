package service

import view.Refreshable
/**
 * Abstract service class
 */

abstract class AbstractRefreshingService {

    /**
     * Abstract service class that handles multiples [Refreshable] which are notified
     * of changes to refresh via the [onAllRefreshables] method.
     *
     */
    private val refreshables = mutableListOf<Refreshable>()

    /**
     * adds a [Refreshable] to the list that gets called
     * whenever [onAllRefreshables] is used.
     */
    fun addRefreshable(newRefreshable : Refreshable) {
        refreshables += newRefreshable
    }

    /**
     * function for onAllRefreshables
     */
    fun onAllRefreshables(method: Refreshable.() -> Unit) =
        refreshables.forEach { it.method() }


}