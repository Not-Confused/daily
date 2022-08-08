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

//    default void forEach(Consumer<? super T> action) {
//        Objects.requireNonNull(action);
//        for (T t : this) {
//            action.accept(t);
//        }
//    }
}
