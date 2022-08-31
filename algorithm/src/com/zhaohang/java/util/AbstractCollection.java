package com.zhaohang.java.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class provides a skeletal implementation of the Collection interface,
 * to minimize the effort required to implement this interface.
 * To implement an unmodifiable collection, programmers need only to extend this class
 * and provide implementations for the iterator and size methods.
 * (The iterator returned by the iterator method must implement hasNext and next.)
 * To implement the modifiable collection, the programmer must override this class's add method
 * (which otherwise throws an UnsupportedOperationException),
 * and the iterator returned by the iterator method must additionally implement its remove method.
 * The programmer should generally provide a void (no argument) and Collection constructor,
 * as per the recommendation in the Collection interface specification.
 * The documentation for each non-abstract method in this class describes its implementation in detail.
 * Each of these methods may be overridden if the collection being implemented admits a more efficient implementation.
 * This class is the member of Java Collection Framework.
 * @param <E>
 */
public abstract class AbstractCollection<E> implements Collection<E> {

    /**
     * Sole constructor (For invocation by subclass constructors, typically implicit.)
     */
    protected AbstractCollection() {}

    /**
     * Returns an iterator over the elements in this collection.
     * @return an iterator over the elements in this collection.
     */
    public abstract Iterator<E> iterator();

    public abstract int size();

    /**
     * Returns true if this collection contains no elements.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this collection contains the specified element.
     * More formally, returns true if and only if this collection contains at least one element e such that (o == null ? e == null : o.equals(e))
     * This implementation iterates over the elements in this collection,
     * checking each element in turn for equality for the specified element.
     * @param o element whose presence in this collection is to be tested
     * @return
     */
    public boolean contains(Object o) {
        Iterator<E> iterator = iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (o.equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements are returned by its iterator,
     * this method must return the elements in the same order.
     * The returned array will be "safe" in that no references to it are maintained by this collection.
     * (In other words, this method must return a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     * This method acts as bridge between array-based and collection-based APIs.
     * This implementation returns an array containing all the elements returned this collection's iterator,
     * in the same order, stored in consecutive elements of the array, starting with index 0.
     * The length of the returned array is equal to the number of elements by the iterator,
     * even if the size of the collection changes during iteration,
     * as might happen if the collection permits concurrent modification during iteration.
     * The size method is called only as an optimization hint;
     * the correct result is returned even if the iterator returns a different number of elements.
     * This is equivalent to:
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *  list.add(e);
     *  return list.toArray();
     * @return
     */
    public Object[] toArray() {
        // Estimate size of array; be prepared to see more or fewer element.
        Object[] r = new Object[size()];
        Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) {
                return Arrays.copyOf(r, i); // fewer elements than expected
            }
            r[i] = it.next();
        }
//        return it.hasNext() ?
        return null;
    }

    /**
     * Returns an array containing all the elements in this collection ;
     * the runtime type of the returned array is that of the specific array.
     * If the collection fits in the specific array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the specified array and the size of this collection.
     *
     * @param a the array into which the elements of this collection are to be store, if it is big enough;
     *          otherwise, a new array of the same runtime type is allocated for this purpose.
     * @param <T>
     * @return
     */
    public <T> T[] toArray(T[] a) {
        // Estimate size of array; be prepared to see more or fewer elements
        int size = size();
        T[] r = a.length >= size ? a : (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        Iterator<E> it = iterator();

        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) {  // fewer element than expected
                if (a == r) {
                    r[i] = null;  // null-terminate
                } else if (a.length < i) {  // fully filled
                    return Arrays.copyOf(r, i);
                } else {
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
                return a;
            }
            r[i] = (T)it.next();
        }
        // more elements than expected
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in OutOfMemoryError:
     * Requested array size exceeds VM limit.
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int i = r.length;
        while (it.hasNext()) {
            int cap = r.length;
            if (i == cap) {
                int newCap = cap + (cap >> 1) + 1;
                // overflow-conscious code
                if (newCap - MAX_ARRAY_SIZE > 0) {
                    newCap = hugeCapacity(cap + 1);
                }
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T)it.next();
        }
        // trim if overallocated
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {// overflow
            throw new OutOfMemoryError("Required array size too large");
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    /**
     * Ensures that this collection contains the specified element(optional operation).
     * Return true if this collection changed as a result of the call.
     * (Returns false if this collection does not permit duplicates and already contains the specific element.)
     * Collection that supports this operation may place limitation on what elements be added to this collection.
     * In particular, some collections will refuse to add null elements,
     * and others will impose restrictions on the type of elements that may be added.
     * Collection classes should clearly specify in their documentation any restrictions on what elements may be added.
     * If a collection refuses to add a particular element for any reason other than that it is already contains the element,
     * it must throw a exception (rather than returning false).
     * This preserves the invariant that a collection always contains the specified element after this call returns.
     * This implementation always throw an UnsupportedOperationException.
     * @param e element whose presence in this collection is to be ensured.
     * @return
     */
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes a single instance of the specified element from this collection,
     * if it is present(optional operation).
     * More formally, removes an element e such that (o == null) ? e = null : o.equals(e)),
     * if this collection contains one or more such elements.
     * Return true if this collection contains the specified element
     * (or equivalently, if this collection changed as a result of the call.
     * This implementation iterates over the collection looking for the specified element.
     * 分词短语作状语表伴随, 分词动作伴随着被修饰词和短语的发生。
     * 现在分词短语作状语, 与主语之间是主动关系。
     * If it finds the element, it removes the element from the collection using the iterator's remove method.
     * Note that this implementation throws an UnsupportedOperationException
     * if the iterator returned by this collection's iterator method does not implement remove method
     * and this collection contains the specified object.
     * @param o element to be removed from this collection, if present.
     * @return
     */
    public boolean remove(Object o) {
        Iterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o == it.next()) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if this collection contains all the elements in the specified collection.
     * This implementation iterates over the specified collection,
     * checking each element returned by the iterator in turn to see if it's contained in this collection.
     * Return true if all elements are so contained, otherwise false.
     * @param c
     * @return
     */
    public boolean containAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * This implementation iterates over the specified collection,
     * and adds each object by the iterator to this collection, in turn.
     * Note that this implementation throw an UnsupportedOperationException unless add is overridden
     * (assuming the specified collection is non-empty)
     * @param c collection containing element to be added to this collection.
     * @return
     */
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        Iterator<? extends E> it = c.iterator();
        while (it.hasNext()) {
            if (!add(it.next())) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all of this collection's elements that are also contained in the specified collection(optional operation)
     * 介词短语作定语起限定作用
     * After this call returns, this collection will contain no element in common with the specified.
     * This implementation iterates over this collection,
     * 过去分词作定语, 与修饰词之间是被动关系, 即修饰词时动作的接受者
     * checking each element returned by the iterator in turn to see if it's contained in the specified collection.
     * If it's so contained, it's remove from this collection with the iterator's remove method.
     * Note that this collection will throw an UnsupportedOperationException
     * if the iterator returned by the iterator method does not implement remove method
     * 介词短语作定语起限定作用
     * and this collection contains one or more elements in common with the specified collection.
     * @param c collection containing element to be removed from this collection.
     * @return
     */
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains the elements in this collection that ara contained in the specified collection(optional operation).
     * In other words, removes from this collection all the elements that are not contained in the specified collection.
     * This implementation iterates over this collection, checking each element returned by the iterator in turn
     * to see if it's contained in the specified collection.
     * If it's not so contained, it's removed from this collection with the iterator's remove method.
     * Note that this implementation will throw an UnsupportedOperationException if
     * the iterator method does not implement remove method and this collection contains one or more elements not present in the specified collection.
     * @param c collection containing the elements to be retained in this collection.
     * @return
     */
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all the element from this collection(optional operation).
     * The collection will be empty after this method returns.
     * This implementation iterates over this collection,
     * removing the element using the Iterator.remove operation.
     * Most implementation will probably choose to overview this method for efficiency.
     * Note that this implementation will throw an UnsupportedOperationException
     * if the iterator returned by this collection's iterator method does not implement the remove method and this collection is non-empty.
     */
    public void clear() {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /**
     * Returns a string representation of this collection.
     * The string representation consists of a list of the collection's elements in the order
     * they are return by the iterator, enclosed in square brackets ("[]").
     * Adjacent element are separated by the character ","(comma and space).
     * Elements are converted to strings as by String.valueOf(Object).
     * @return The string representation of this collection.
     */
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext()) {
                return sb.append("]").toString();
            }
            sb.append(",").append(" ");
        }
    }








}
