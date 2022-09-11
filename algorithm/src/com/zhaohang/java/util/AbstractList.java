package com.zhaohang.java.util;

import java.util.*;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 宾语补足语：对主语或宾语进行补充说明
 * This class provides a skeletal implementation of the List interface
 * to minimize the effort required to implement this interface backed by a "random access" data stored (such as an array).
 * For sequential access data (such as linked list), AbstractSequentialList should be used in preference to this class.
 *
 * To implement an unmodifiable list, the programmer needs only to extend this class
 * and provide the implementations for the get(int) and size() methods.
 *
 * To implement a modifiable list, the programmer must additionally override to set(int, E) method
 * (which otherwise throws an UnsupportedOperationException).
 * If this list is variable-size, the programmer must additionally override the add(int, E) and remove(int) methods.
 *
 * The programmer should generally provide a void (no argument) and collection constructor,
 * as per recommendation in Collection interface specification.
 *
 * Unlike the other abstract collection implementation, the programmer does not have to provide an iterator implementation;
 * the iterator and the list iterator are implemented by this class, on top of the "random access" method:
 * get(int), set(int, E), add(int, E) and remove(int).
 *
 * The documentation for each non-abstract method in this class describes its implementations in detail.
 * Each of these methods may be overridden if the collection being implemented admits a more efficient implementation.
 *
 * This class is a member of Java Collection Framework.
 * @param <E>
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    /**
     * The member variable decorated by the transient keyword cannot be serialized.
     * 关系代词引导的定语从句
     * 关系代词所代替的先行词是表示人或物的名词, 或相当于名词的名词词组或代词。
     * 并在从句中充当主语, 宾语, 定语等成分。
     * 关系代词在定语从句中作主语时, 从句谓语动词的人称和数要和先行词保持一致。
     * that作宾语时可省略。
     * The number of times this list has been structurally modified.
     * Structurally modification are those that change the size of the list,
     * or otherwise perturb it in a fashion that iterations in progress may yield incorrect results.
     * This field is used by the iterator and list iterator returned by the iterator and listIterator methods.
     * If the value of this field changes unexpectedly,
     * the iterator (or list iterator) will throw a ConcurrentModifiedException in response to the next, remove, previous, set or add operations.
     * This provides fail-fast behavior, rather than non-deterministic behavior in the face of concurrent modification during iteration.
     *
     * Use of this field by subclasses is optional.
     * If a subclass wishes to provide fail-fast iterators (and list iterators),
     * then it merely has to increment this field in its add(int, E) and remove(int) methods
     * (and any other methods that it overrides that result in structurally modification to this list).
     * A single call to add(int, E) or remove(int) must add no more than one to this field,
     * or the iterators (the list iterators) will throw bogus ConcurrentModificationExceptions.
     * If the implementations does not wish to provide fail-fast iterators, this field may be ignored.
     */
    protected transient int modCount = 0;

    /**
     * Sole constructor. (For invocation by subclass constructors, typically implicit.)
     */
    protected AbstractList() {}

    /**
     * Appends the specified element to the end of this list (optional operation).
     * Lists that support this operation may place limitations on what elements may be added to this list.
     * In particular, some lists will refuse to add null elements, and others will impose restrictions on the types of the elements that may be added.
     * List classes should clearly specify in their documentation any restrictions on what elements may be added.
     * This implementation calls the add(size(), e).
     * @param e
     * @return
     */
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    /**
     * Returns the elements at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    abstract public E get(int index);

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * This implementation always throws an UnsupportedOperationException.
     * @throws UnsupportedOperationException
     */
    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     * Shifts the element currently at that position (if any) and subsequent elements to the right
     * (adds one to their indices).
     * @throws UnsupportedOperationException
     */
    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the element at the specified position in this list (optional operation).
     * Shifts any subsequent elements to left (subtract one from their indices).
     * Returns the element that was removed from this list.
     * This implementation always throws an UnsupportedOperationException.
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        ListIterator<E> li = listIterator();
        if (o == null) {
            while (li.hasNext()) {
                if (li.next() == null) {
                    return li.previousIndex();
                }
            }
        } else {
            while (li.hasNext()) {
                if (o.equals(li.next())) {
                    return li.previousIndex();
                }
            }
        }
        return -1;
    }

    private class Itr implements Iterator<E> {

        /**
         * index of the element to be returned by subsequent call to next.
         */
        int cursor = 0;

        /**
         * Index of element returned by most recent call to next or previous.
         * Reset to -1 if this element is deleted by a call to remove.
         */
        int lastRet = -1;

        /**
         * The modCount value that the iterator believes that the backing list should have.
         * If this expectation is violated, the iterator has detected the concurrent modification.
         */
        int expectedModCount = modCount;

        /**
         * Returns true if the iterator has more elements.
         * (In other words, returns true if the next method returns an element rather than throws an exception).
         * @return true if the iteration has more element.
         */
        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        /**
         * @return the next element in the iteration.
         * @throws NoSuchElementException if the iteration has no more elements.
         */
        @Override
        public E next() {
            /**
             * 由whether/if引导的宾语从句, 实际上是由一般疑问句演化而来, 意思是"是否", 宾语从句要用陈述句语序。一般来说, whether/if是可以互换的。
             * 在及物动词后, 只能用whether, 不能用if。
             */
            // Detects whether there are other threads call methods that modify this list.
            checkForModification();
            try {
                // the current index of the list.
                int i = cursor;
                // Gets element at current index.
                E next = get(i);
                // reset
                lastRet = i;
                // Shifts index to right.
                cursor = i + 1;
                return next;
            } catch (Exception e) {
                checkForModification();
                throw new NoSuchElementException();
            }
        }

        /**
         * Removes from the underlying collection the last element returned by this iterator (optional operation)
         * This method can be called only once per call to next.
         * The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any way other than by calling this method.
         * @throws UnsupportedOperationException if the remove operation is not supported by this iterator.
         * @throws IllegalStateException
         */
        public void remove() {
            // checks whether the next method has been called.
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForModification();
            try {
                AbstractList.this.remove(lastRet);
                if (lastRet < cursor) {
                    cursor--;
                }
                // Reset
                lastRet = -1;
                // Modify modCount to indicate that the remove method is called by iterator.
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * An iterator for lists that allows the programmer to traverse the list in either directory,
     * modify the list during iteration, and obtain the iterator's current position in the list.
     *
     */
    private class ListItr extends Itr implements ListIterator<E> {

        /**
         * Specifies the current position of the iterator in the list.
         * @param index the current position of the iterator.
         */
        ListItr(int index) {
            cursor = index;
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
         */
        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        /**
         * Returns the previous element in this list and moves the cursor position backwards.
         * 不定式和分词作状语：
         * 不定式作状语表目的；分词作状语表伴随。目的和伴随都是和被修饰词和短语的关系。
         * 过去分词作状语, 表伴随, 与主语之间是被动关系。
         * This method may be called repeatedly (不定式作目的状语) to iterate through this list backwards,
         * or (过去分词作状语, 表伴随, 伴随called这个动作, 与主语是被动关系) intermixed with call to next to go back and forth.
         * (Note that alternating calls to next and previous will return the same element repeatedly.)
         * @return the previous element in this list.
         * @throws NoSuchElementException if the iteration has no previous elements.
         */
        @Override
        public E previous() {
            checkForModification();
            try {
                // Moves the cursor forward to one position.
                int i = cursor - 1;
                // Gets the element at the cursor position.
                E previous = get(i);
                // reset
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                checkForModification();
                throw new NoSuchElementException();
            }
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to next.
         * (Returns the list size if the list iterator is the end of the list.)
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * Returns the index of element that would be returned by a subsequent call to previous.
         * (Returns -1 if the list iterator is at the beginning of this list.)
         */
        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * Replaces the last element returned by the previous or next with the specified element (optional operation).
         * This call can be made only if neither remove nor add have been called after the last call to next or previous.
         * @param e the element with which to replace the last element returned by next or previous.
         * @throws UnsupportedOperationException if the set operation is not supported by this list iterator.
         * @throws ClassCastException if the class of the specified element prevents it from being added to this list.
         */
        @Override
        public void set(E e) {
            // Indicates that the next method or the previous method has not been called.
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForModification();
            try {
                // Replaces the element at the cursor position with the specified element.
                AbstractList.this.set(lastRet, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            checkForModification();

            try {
                int i = cursor;
                // Adds the specified element at the cursor position.
                AbstractList.this.add(i, e);
                // Reset
                lastRet = -1;
                // Shifts the cursor to one position backwards.
                cursor = i + 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        ListIterator<E> it = listIterator(size());
        if (o == null) {
            while (it.hasPrevious()) {
                if (it.previous() == null) {
                    return it.nextIndex();
                }
            }
        } else {
            while (it.hasPrevious()) {
                if (o.equals(it.previous())) {
                    return it.nextIndex();
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    /**
     * Returns a list iterator over the elements in this list(in proper sequence),
     * starting at the specified element in the list.
     * The specified index indicates the first element that would be returned by an initial call to next.
     * An initial call to previous would return the element with the specified index minus one.
     *
     * This implementation returns a straightforward implementation of the ListIterator interface
     * that extends the implementation of the Iterator interface returned by the iterator() method.
     * The ListIterator implementation replies on the backing list's get(int), set(int, E), add(int, E) and remove(int) methods.
     *
     * Note that the list iterator returned by this implementation will throw an UnsupportedOperationException
     * in response to its remove, set and add methods unless the list's remove(int), set(int, E) and add(int ,E) methods are overridden.
     *
     * This implementation can be made to throw runtime exception in the face of concurrent modification,
     * as described in the specification for the (protected) modCount field.
     * @param index
     * @return
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        rangeCheckForAdd(index);

        return new ListItr(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * Checks weather the index of element for the add operation is out of range.
     * @param index
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size();
    }
}
