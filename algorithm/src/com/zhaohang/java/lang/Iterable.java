package com.zhaohang.java.lang;

import com.zhaohang.java.util.Iterator;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Implementing this interface allows an object to be the target of the
 * "for-each loop" statement.
 * Type parameters: <T> - the type of elements returned by the iterator.
 */
public interface Iterable<T> {

    /**
     * Returns an iterator over elements of type T
     * @return: an Iterator
     */
    Iterator<T> iterator();

    /**
     * Performs the given action for each element of the Iterable
     * until all elements have been processed or the action throws an exception.
     * Unless otherwise specified by the implementing class, actions are performed in the order of iteration
     * (if an iteration order is specified). Exception thrown by the action are relayed to the caller.
     * @param action The action to be performed for each element.
     * throws: NullPointerException if the specified action is null.
     * Implementation requirements：
     *               The default implements behaves as if:
     *
     */
//    default void forEach(Consumer<? super T> action) {
//        Objects.requireNonNull(action);
//        for (T t : this) {
//            action.accept(t);
//        }
//    }
}
