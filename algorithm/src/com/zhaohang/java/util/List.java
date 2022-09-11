package com.zhaohang.java.util;

import java.util.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * An ordered collection(as known as a sequence).
 * 连接代词和连接副词引导的宾语从句实际上是从特殊疑问句变化而来, 宾语从句要用陈述句语序。
 * 用于这种结构的动词常常是：see、say、answer等。
 * 英语中的连接代词有：who、whom、whose、which、what。在句中担任主语、宾语、定语或者表语。
 * e.g：Can you tell me whom you are waiting for?
 * 英语中的连接副词有：when、where、why、how。在句中担任状语成分。
 * The user of this interface has precise controller over where in the list each element is inserted,
 * and search for elements in the list.
 * Unlike sets, lists typically allow duplicate elements.
 * More formally, lists typically allow pairs of elements e1 and e2 such that e1.equals(e2),
 * and they typically allow multiple null elements if they allow null elements at all.
 * It is not inconceivable that someone might wish to implement a list that prohibits duplicates,
 * by throwing runtime exception when the user attempts to insert them,
 * but we except this usage to be rare.
 * The list interface place additional stipulations, beyond those specified in the Collection interface,
 * on the contract of the iterator, add, remove, and hashCode methods.
 * Declarations for other inherited methods are also included here for convenience.
 *
 * The list interface provides four methods for positional(indexed) access to list elements.
 * Lists (like Java Array) are zero based.
 * Note that these operations may execute in time proportional to the index value for some implementations
 * (the LinkedList class, for example).
 * Thus, iterating over the elements in a list is typically preferable to indexing through it
 * if the caller does not know the implementation.
 * The list interface provides a specified iterator, called a ListIterator,
 * that allows element insertion and replacement, and bidirectional access in addition to the normal operations that the Iterator interface provides.
 * A method is provided to obtain a list iterator that starts at a specified position in the list..
 *
 * The List interface provides two methods to search for object.
 * From a performance standpoint, these methods should be used with caution.
 * In many implementations they will perform costly linear searches.
 *
 * The List interface provides two method to efficiently insert or remove multiple elements
 * at the arbitrary point in the list.
 * 不定式作定语, 表未发生、但将来可能发生的动作
 * Note: While it is permissible for list to contain themselves as elements,
 * extreme caution is advised: the equals and hashCode methods are no longer well defined on such a list.
 *
 * Some List implementations have restriction on the elements that they may contain.
 * For example, some implementations prohibit null element, and some have restriction on the type of the elements.
 * Attempting to add an ineligible element throws an unchecked exception, typically NullPointException or ClassCastException.
 * Attempting to query the presence of an ineligible element may throw an exception,
 * or it may simply return false;
 * some implementations exhibit former and some will exhibit latter.
 * More formally, attempting an operation on an ineligible element whose completion would not result in the insertion of an ineligible element into the list
 * may throw an exception or it may succeed, at the point of the implementation.
 * Such exception are marked as "optional" in the specification for this interface.
 * This interface is a member of the Java Collection Framework.
 * @param <E>
 */
public interface List<E> extends Collection<E> {

    /**
     * Returns the number of the elements in this list.
     * If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return returns the number of elements in this list.
     */
    int size();

    /**
     * Returns true if this list contains no elements.
     * @return Returns if this list contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns true if this list contains the specified element.
     * More formally, returns true if and only if this list contains at least one element e such that (o == null ? e == null : o.equals(e)).
     * @param o element whose presence in this collection is to be tested
     * @return true if this list contains the specified element.
     * @throws ClassCastException if the type of the specified element is incompatible with this list(optional).
     * @throws NullPointerException if the specified element is null and this list does not permit null elements(optional)
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return
     */
    Iterator<E> iterator();

    /**
     * Returns an array containing all the elements in this list in proper sequence (from first to last element).
     * The returned array will be "safe" in that no references to it are retained by this list.
     * (In other words, this method must allocate a new array even if this list is backed by an array).
     * Thus, the caller is free to modify the returned array.
     * This method acts as bridge between array-based and collection-based APIs.
     * @return an array containing all the elements in this list in proper sequence.
     */
    Object[] toArray();

    /**
     * Returns an array containing all the elements in this list in proper sequence(from first to last element);
     * the runtime type of the returned array is that of the specified array.
     * If the list fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the specified array and the size of this list.
     *
     * If the list fits in the specified array with room to spare(i.e., the array has more elements than the list),
     * the element in the array immediately following the end of the list is set to null.
     * This is useful in determining the length of the list if the caller only know that the list does not contain any null elements.)
     *
     * Like the toArray() method, this method acts as bridge between array-based and collection-based APIs.
     * Further, this method allows precise controller over the runtime type of the output array,
     * and may, under certain circumstances, be used to save allocation costs.
     *
     * Suppose x is a list known to contain only strings.
     * The following code can be used to dump the list into a newly allocated array of String:
     *      String[] y = x.toArray(new String[0]);
     * Note that toArray(new Object[0]) is identical in function to toArray().
     *
     * @param a the array into which the elements of this collection are to be store, if it is big enough;
     *          otherwise, a new array of the same runtime type is allocated for this purpose.
     * @param <T> the runtime type of the returned array.
     * @return an array containing all the elements in this list in proper sequence.
     * @throws ArrayStoreException if the runtime type of the specified array is not a supertype of the runtime type of every element in this list.
     * @throws NullPointerException if the specified array is null.
     */
    <T> T[] toArray(T[] a);

    /**
     * Appends the specified element to the end of this list(optional operation).
     * Lists that support this operation may place limitations on what elements be added to this list.
     * In particular, some lists will refuse to add null elements,
     * and others will impose restrictions on the type of elements that may be added.
     * List classes should clear specify in their document any restrictions on what elements may be added.
     * @param e element whose presence in this collection is to be ensured.
     * @return true (as specified by Collection.add)
     * @throws UnsupportedOperationException if the add operation is supported by this list.
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list.
     * @throws NullPointerException if the specified element is null and this list does not permit null elements.
     * @throws IllegalArgumentException if some properties of the element prevent it from being added to list.
     */
    boolean add(E e);

    /**
     * Removes the first occurrence of the specified element from this list, if it is present (optional operation).
     * If this list does not contain the specified element, it is unchanged.
     * More formally, removes the element with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
     * Return true if this list contains the specified element (or equivalent, if this list is changed as a result of the call).
     * @param o element to be removed from this collection, if present.
     * @return true if this list contains the specified element.
     * @throws ClassCastException if the type of the specified element is incompatible with this list (optional)
     * @throws NullPointerException if the specified element is null and this list does not permit null elements (optional).
     * @throws UnsupportedOperationException if the remove operation is not supported by this list.
     */
    boolean remove(Object o);

    /**
     * Returns true if this list contains all the elements of the specified collection.
     *
     * @param c collection to be checked for containment in this list.
     * @return true if this list contains all the elements of the specified collection.
     * @throws ClassCastException if the types of one or more elements in this list are incompatible with this list (optional).
     * @throws NullPointerException if the specified collection contains one or more null element and this list does not permit null element (optional), or if the specified collection is null.
     */
    boolean containAll(Collection<E> c);

    /**
     * Appends all the element of the specified collection to the end of this list,
     * in the order that they are returned by the specified collection's iterator (optional operation).
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     * (Note that this will occur if the specified collection is this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list.
     * @return true if this list is changed as a result of the call.
     * @throws UnsupportedOperationException if the addAll operation is not supported by this list.
     * @throws ClassCastException if the types of one or more elements of the specified collection prevent it from be added to this list.
     * @throws NullPointerException if the specified collection contains one or more null elements and this list does not permit null elements, or the specified collection is null.
     * @throws IllegalArgumentException If some properties of the elements of the specified collection prevent it from being added to this list.
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * Inserts all the element in the specified collection into this list at the specified position (optional operation).
     * Shifts the element currently at that position (if any) and any subsequent elements to right (increase their indices).
     * The new elements will appear in this list in the order that they are returned by the specified collection's iterator.
     * The behavior of this operation is undefined if the specified collection is modified while this operation is in progress.
     * (Note that this will occur if the specified collection is this list, and it's nonempty.)
     * 关系副词引导的定语从句, 关系副词在从句中做状语 at which == where
     * @param index index at which to index the first element from the specified collection.
     * @param c collection containing elements to be added to this list.
     * @return true if this list is changed as a result of the call.
     * @throws UnsupportedOperationException if the addAll operation is not supported by this list.
     * @throws ClassCastException if the types of the elements of the specified collection prevent it from being added to this list.
     * @throws NullPointerException if the specified collection contains one or more null elements and this list does not permit null element,
     *                              or if the specified collection is null.
     * @throws IllegalArgumentException if some properties of an element of the specified collection prevent it from being added to this list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    boolean addAll(int index, Collection<? extends E> c);

    /**
     * Removes from this list all its elements that are contained in the specified collection (optional operation).
     * @param c collection containing the elements to be removed from this list.
     * @return true if this list is changed as a result of the call.
     * @throws UnsupportedOperationException if the removeAll operation is not supported by this list.
     * @throws ClassCastException if the types of an element of this list are incompatible with the specified collection.
     * @throws NullPointerException if this list contains null element and the specified collection does not permit null elements(optional), or if the specified collection is null.
     */
    boolean removeAll(Collection<?> c);

    /**
     * Retains only the element in this list that are contained in the specified collection (optional operation).
     * In other words, removes from this list all its elements that are not contained in the specified collection.
     * @param c collection containing the elements to be stored in this list.
     * @return true if this list is changed as a result of the call.
     * @throws UnsupportedOperationException if the retainAll operation is not supported by this list.
     * @throws ClassCastException if the class of an element of this list is incompatible with the specified collection (optional).
     * @throws NullPointerException if the specified collection is null.
     */
    boolean retainAll(Collection<?> c);

    /**
     * Replaces each element in this list with the result of applying the operator to that element.
     * Errors or runtime exceptions thrown by the operator are relayed to the caller.
     * @param operator the operator to apply to each element.
     */
    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<E> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }

    /**
     * Sorts this list according to the order induced by the specified Comparator.
     * All elements in this list must be mutually comparable using the specified comparator
     * (that is, c.compare(e, e) must not throw a ClassCastException for any element e1 and e2 in this list.)
     * If the specified comparator is null then all elements in this list must implement the Comparable interface and the element' natural order should be used.
     * This list must be modifiable, but need not be resizable.
     * @param c the comparator used to compare list elements. A null value indicates that the elements' natural ordering should be used.
     * @throws ClassCastException if the list contains elements that are not mutually comparable using the specified comparator.
     * @throws UnsupportedOperationException if the list's List-Iterator does not support set operation.
     * @throws IllegalArgumentException If the comparator is found to violate the Comparator contract.
     *
     * Implementation Requirements: The default implementation obtains an array containing all elements in this list,
     * sorts the array, and iterates over this list resetting each element from the corresponding position in the array.
     * (This avoids the log(n) performances that would result from attempting to sort a linked list in place.)
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> li = this.listIterator();
        for (Object e : a) {
            li.next();
            li.set((E) e);
        }
    }

    /**
     * Removes all the elements from this list (optional operation).
     * The list will be empty after the call returns.
     * @throws UnsupportedOperationException if the clear operation is supported by this list.
     */
    void clear();

    /**
     * Compares the specified collection with this list for equivalent.
     * Returns true if and only if the specified object is also a list, both lists have same size,
     * and all corresponding pairs of elements in the two lists are equal.
     * (Two element e1 and e2 are equal if (e1==null ? e2==null : e1.equals(e2)).)
     * In other words, two lists are defined to be equal if they contain the same element in the same order.
     * This definition ensures that the equals method works properly across different implementations of the list interface.
     * @param o the object to be compared for equivalent with this list.
     * @return true if the specified object is equal to this list.
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value of this list.
     * The hash code of a list is defined to be the result of the following calculation:
     *          int hashcode = 1;
     *          for (E e : list)
     *              hashcode = 31 * hashcode + (e==null ? 0 : e.hashcode());
     * This ensures that list.equals(list2) implies that list1.hashcode() == list2.hashcode()
     * for any two lists, list1 and list2, as required by the general contract of Object.hashcode.
     * @return the hash code value for this list.
     */
    int hashcode();

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size()).
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation)
     * @param index index of the element to be replaced
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws UnsupportedOperationException if the set operation is not supported by this list.
     * @throws ClassCastException if the class of the specified element prevent it from being added to this list.
     * @throws NullPointerException if the specified element is null and this list does not permit null elements.
     * @throws IllegalArgumentException if some properties of the specified element prevent it from being added to this list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    E set(int index, E element);

    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * @param index index in which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws UnsupportedOperationException if the add operation is not supported by this list.
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list.
     * @throws IllegalArgumentException if some properties of the specified element prevent it from being added to this list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size()).
     */
    void add(int index, E element);

    /**
     * Removes the element at the specified position in this list (optional operation).
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from this list.
     * @param index the index of the element to be removed.
     * @return the element that was removed from this list.
     * @throws UnsupportedOperationException if the remove operation is not supported by this list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E remove(int index);

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param o element to search for
     * @return
     */
    int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param o element to search for
     * @return
     */
    int lastIndexOf(Object o);

    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     * @return
     */
    ListIterator<E> listIterator();

    /**
     * Returns a list iterator over the elements in this list (in proper sequence),
     * starting at the specified position in the list.
     * The specified index indicates the first element that would be returned by an initial call to next.
     * An initial call to previous would return the element with the specified index minus one.
     * @param index index of the first element to be returned from the list iterator (by a call to next)
     * @return
     */
    ListIterator<E> listIterator(int index);

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * (If fromIndex and toIndex are equal, the returned list is empty.)
     * The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all the optional list operations supported by this list.
     *
     * This method eliminates the need for explicit range operations
     * (of the sort that commonly exists for arrays).
     * Any operation that expects a list can be used as a range operation by passing a subList view instead of a whole list.
     * For example, the following idiom removes a range of elements from a list:
     *      list.subList(from, to).clear();
     * Similar idioms may be constructed for indexOf and lastIndexOf,
     * and all the algorithms in the Collection class can be applied to a subList.
     *
     * The semantics of the list returned by this method become undefined
     * if the backing list (i.e, this list) is structurally modified in any way other than via the returned list.
     * (structural modification are those that change the size of this list,
     * or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList.
     * @param toIndex high endpoint (exclusive) of the subList.
     * @return a view of the specified range within this list.
     */
    List<E> subList(int fromIndex, int toIndex);
}
