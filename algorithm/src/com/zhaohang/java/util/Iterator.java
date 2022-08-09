package com.zhaohang.java.util;

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
 * the type of elements returned by this iterator.
 *           the type
 * the type of elements which were returned by this iterator.
 */
public interface Iterator<E> {

    /**
     * Returns true if the iteration has more elements.
     * (In other words, returns true if next would return an element rather than throwing an exception.)
     * @return: true if the iteration has more elements.
     */
    boolean hasNext();
}
