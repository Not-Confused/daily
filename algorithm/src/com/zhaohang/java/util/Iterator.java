package com.zhaohang.java.util;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * An iterator over a collection.
 * Iterator takes the places of Enumeration in the Java Collections Framework.
 * Iterators differ from enumerations in two ways:
 * <ul>
 *     <li> Iterators allow the callers to remove elements from
 *     underlying collection during the iteration with well-defined semantics.
 *     <li> Method names have been improved.
 * </ul>
 * This interface is the member of the Java Collection Framework.
 * @param <E> the type of elements returned by this iterator.
 * 过去分词充当定语, 常置于所修饰的名词之后, 相当于定语从句, 但较定语从句简洁, 常用于书面语
 * the type of element returned by this iterator.
 */
public interface Iterator<E> {

    /**
     * Returns true if the iteration has more elements.
     * (In other words, returns true if next would return an element rather than throwing an exception.)
     * @return: true if the iteration has more elements.
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     * @return: the next element in the iteration.
     * Throws: NoSuchElementException - if the iteration has no more elements.
     */
    E next();

    /**
     * Removes from the underlying collection the last element returned this iterator
     * (optional operation). This method can be called only once per call to next.
     * The behavior of an iterator is unspecified if the underlying collection is modified
     * while the iteration is in progress in any way other than by calling this method.
     * Throws:
     * UnsupportedOperationException —— the remove operation is not supported by this iterator.
     * IllegalStateException —— if the next method has not yet been called,
     * or the remove method has already been called after the last call to the next method.
     * Implementation Requirements：The default implementation throws an instance of
     * UnsupportedOperationException and performs no other action.
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * Performs the given action for each remaining element until all element have been processed
     * or the action throws an exception.
     * Action are performed in the order of iteration, if that order is specified.
     * Exceptions thrown by the action are relayed to caller.
     * 不定式作后置定语：置于被修饰的名词或代词之后
     * 1、表示将来的动作, 可转换为定语从句
     * @param action The action to be performed for each element.
     * throws：NullPointerException — if the specified action is null.
     * Implementation Requirements：
     *               The default implementation behaves as if：
     */
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext()) {
            action.accept((next()));
        }
    }
}
