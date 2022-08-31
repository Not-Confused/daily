package com.zhaohang.java.util;

import com.zhaohang.java.lang.Iterable;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * The root interface in the Collection hierarchy.
 * A collection represents a group of objects, known as its elements.
 * Some collections allow duplicate elements and other do not.
 * Some are ordered and others unordered.
 * The JDK does not provide any direct implementation of this interface:
 * it provides implementations of more specific subinterfaces like List or Set.
 * This interface is typically used to pass collections around and manipulate them where maximum generality is desired.
 *
 * Bags or multiset (unordered collections that may contain duplicate elements) should implement this interface directly.
 *
 * All general-propose collection implementation classes (which typically implement Collection indirectly through one of its subinterfaces) should provide two "standard" constructor.
 * a void (no arguments) constructor, which creates an empty collection,
 * and a constructor with single argument of type Collection, which creates a new collection with the same element as its argument.
 * In effect, the latter constructor allows user to copy any collection,
 * producing an equivalent collection of the desired implementation type.
 * There is no way to enforce this convention (as interface cannot contain constructors)
 * but all of the general-purpose Collection implementations in the Java platform libraries comply.
 *
 * The "destructive" methods contained in this interface, that is, the methods that modify the collection on which they operate,
 * are specified to throw UnsupportedOperationException if the invocation would have no effect on the collection.
 * For example, invoking the addAll(Collection) method on an unmodifiable collection may, but is not required to,
 * throw the exception if the collection to be added is empty.
 *
 * Some Collection implementations have restriction on the elements that they may contain.
 * For example, some implementations prohibit null elements, and some have restriction on the types of their elements.
 * Attempting to add an ineligible element throws an unchecked exception, typically NullPointerException or ClassCastExcepition.
 * Attempting to query the presence of an ineligible element may throw an exception, or it may simply return false;
 * some implementations will exhibit the former behavior and some will exhibit latter.
 * More generally, attempting an operation on an ineligible element whose completion would not result in the insertion of an ineligible element into the collection may throw an exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this interface.
 *
 * It is up to each collection to determine its own synchronized policy.
 * In the absence of a stronger guarantee by the implementation,
 * undefined behavior may result from the invocation of any method on a collection that is mutated by another thread;
 * this includes direct invocations, passing the collection to a method that might perform invocations,
 * and use an existing iterator to examine the collection.
 *
 * Many methods in Collections Framework interfaces are defined in the term of the equals method.
 * For example, the specification for the contains(Object o) method says:
 * "returns true if and only if this collection contains at least one element such that (o == null ? o == null : o.equals(e))."
 * This specification should not be construed to imply that invoking Collection.contains with a non-null argument o will cause o.equals(e) to be invoked for any element e.
 * Implementations are free to implement optimizations whereby the equals invocation is avoided,
 * for example, by first comparing the hash codes of the two elements.
 * (The Object.hashCode() specification guarantees that two objects with unequal hash code cannot be equal.)
 * More generally, implementations of the various Collections Framework interfaces are free to take advantage of the specified behavior of underlying Object methods wherever the implementor deems it appropriate.
 *
 * Some Collection operations which perform recursive traversal of the collection may fail
 * with an exception for self-referential instances where the collection directly or indirectly contains itself.
 * This includes the clone(), equals(), hashcode() and toString() methods,
 * Implementations may optionally handle the self-referential scenario,
 * however most current implementations do not do so.
 *
 * This interface is a member of the Java Collections Framework.
 *
 * Implementation Requirements:
 * The default method implementations(inherited or otherwise) do not apply any synchronized protocal.
 * If a Collection implementation has a specific synchronized protocol,
 * then it must overview default implementations to apply that protocol.
 * See also: Set, List, Map, SortedSet, SortedMap, HashSet, TreeSet, ArrayList,
 * LinkedList, Vector, Collections, Arrays, AbstractCollection
 *
 * @param <E>
 */
public interface Collection<E> extends Iterable<E> {

    /**
     * Returns the number of elements in this collection.
     * If this collection contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return the number of elements in this collection
     */
    int size();

    /**
     * Returns true if this collection contains no elements.
     * @return true if this collection contains no elements.
     */
    boolean isEmpty();

    /**
     * Return true if this collection contains specified element.
     * More formally, returns true if and only if this collection contains at least one element e such that(o==null ? e == null : o.equals(e)).
     * @param o element whose presence in this collection is to be tested
     * @return true if this collection contains specified element.
     * @throws ClassCastException - if the type of specified element is incompatible with this collection(optional)
     * NullPointerException - if the specified element is null and this collection does not permit null elements(optional)
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over elements in this collection.
     * There are no guarantees concerning the order in which the element are returned
     * (unless this collection is an instance of some class that provides a guarantee).
     * @return an Iterator over the element in this collection.
     */
    Iterator<E> iterator();

    /**
     * Returns an array containing all of elements in this collection.
     * If this collection makes any guarantees as to what order its elements are returned by this iterator,
     * this method must return the elements in same order.
     * The returned array will be "safe" in that no reference to it are maintained by this collection.
     * (In other words, this method must allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     * This method acts as bridge between array-based and collection-based APIs.
     * @return: an array containing all of the elements in this collection
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this collection;
     * the runtime type of the returned array is that of the specified array.
     * If the collection fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the specified array and the size of this collection.
     * to prep (与某些名词、形容词连用, 表示关联) 对于; 关于
     * You use to with certain nouns and adjectives to show that a following noun is related to them.
     *
     * If this collection fits in the specified collection with room to spare
     * (i.e.,the array has more element than this collection),
     * the element in this array immediately following the end of the collection is set to null.
     * (This is useful in determining the length of this collection only if the caller knows that this collection does not contain any null elements.)
     *
     * If this collection makes the guarantee as to what order its element are returned by its iterator,
     * this method must return the elements in the same order.
     *
     * Like the toArray() method, this method acts as bridge between array-based and collection-based APIs.
     * Further, this method allows precise controller over the runtime type of output array,
     * and may, under certain circumstances, be used to save allocations costs.
     *
     * Suppose x is a collection known to contain only strings.
     * The following code can be used to dump the collection into a newly allocated array of String:
     *      String[] y = x.toArray(new String[0]);
     * Note that toArray(new Object[0]) is identical in function to toArray().
     *
     * @param a the array into which the elements of this collection are to be store, if it is big enough;
     *          otherwise, a new array of the same runtime type is allocated for this purpose.
     * @param <T> the runtime type of the array to contain the collection.
     * @return an array containing all of the elements in this collection.
     * @throws ArrayStoreException if the runtime type of the specified array is not a supertype of the runtime type of every element in this collection.
     *         NullPointerException if the specified array is null.
     */
    <T> T[] toArray(T[] a);

    /**
     * Ensures that this collection contains the specified element(optional operation).
     * Returns true if this collection changed as a result of the call.
     * (Returns false if this collection does not permit duplicates and already contain the specified element.)
     *
     * Collections that support this operation may place limitations on what elements may be to add to this collection.
     * In particular, some collections will refuse to add null elements,
     * and others will impose restrictions on the type of element that may be added.
     * Collection classes should clearly specify in their document any restrictions on what elements may be add.
     *
     * If a collection refuse to add a particular element for any reason other than it already contains the element,
     * it must throw an exception(rather than returning false).
     * This preserves the invariant that a collection always contains specified element after this call returns.
     * @param e element whose presence in this collection is to be ensured.
     * @return true if this collection changed as a result of the call.
     * @throws UnsupportedOperationException if the add operation is not supported by this collection.
     * @throws ClassCastException if the class of the specified element prevents it from being added to this collection.
     * @throws IllegalArgumentException If some properties of the specified element prevent it from being added to this collection.
     * @throws IllegalStateException If the element cannot be added at this time due to insertion restrictions.
     */
    boolean add(E e);

    /**
     * Removes a single instance of the specified element from this collection, if it is present(optional operation).
     * More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if this collection contains one or more such elements.
     * Returns true if this collection contains the specified element(or equivalently, if this collection changed as a result of the call).
     * @param o element to be removed from this collection, if present.
     * @return true if an element was removed as a result of the call.
     * @throws ClassCastException if the type of the specified element is incompatible with this collection(optional)
     * @throws NullPointerException if the specified element is null and this collection does not permit null elements(optional).
     * @throws UnsupportedOperationException If the remove operation is not supported by this collection.
     */
    boolean remove(Object o);

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     * @param c collection to be checked for containment in this collection
     * @return true if this collection contains all of the elements in this collection.
     * @throws ClassCastException If the types of one or more element in the specified collection are incompatible with this collection(optional)
     * @throws NullPointerException if the specified collection contains one or more null elements and this collection does not permit null elements(optional), or if the specified collection is null.
     */
    boolean containAll(Collection<?> c);

    /**
     * Adds all of the elements in the specified collection to this collection(optional operation).
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the specified collection is this collection, and this collection is nonempty.)
     * @param c collection containing element to be added to this collection.
     * @return true if the collection changed as a result of the call.
     * @throws UnsupportedOperationException if the addAll operation is not supported by this collection.
     * @throws ClassCastException If the class of the element of the specified collection prevents it from being adding to this collection.
     * @throws NullPointerException if the specified collection contains null element and this collection does not permit null elements,
     *                              or if the specified collection is null.
     * @throws IllegalArgumentException If some property of an element of the specified collection prevents it from being added to this collection.
     * @throws IllegalStateException If not all the elements can be added at this time due to insertion restrictions.
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * Remove all of this collection's elements that are also contained in the specified collection(optional operation).
     * After this call returns, this collection will contain no element with the specified element.
     * @param c collection containing element to be removed from this collection.
     * @return true if this collection changed as a result of the call.
     * @throws UnsupportedOperationException if the removeAll method is not supported by this collection.
     * @throws ClassCastException If the type of one or more element in this collection are incompatible with the specified collection(optional)
     * @throws NullPointerException If the specified collection is null
     */
    boolean removeAll(Collection<?> c);

    /**
     * Removes all of the elements of this collection that satisfy the given predicate.
     * Errors or runtime exceptions during iteration or by the predicate are relayed to the caller.
     * @param filter a predicate which returns true for element to be removed.
     * @return true if any elements were removed.
     * @throws NullPointerException if the specified filter is null.
     * @throws UnsupportedOperationException if elements not be removed from this collection.
     * Implementations may throw this exception if a matching element cannot be removed or if, in general, removal is not supported.
     * @implSpec
     * The default implementation traverses all elements of the collection using its iterator.
     * Each matching element is removed using Iterator.remove(). If the collection's iterator does not support removal then an UnsupportedOperationException will be thrown on the first matching element.
     */
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Retains only the elements in this collection that are obtained in the specified collection(optional operation).
     * In other words, removes from this collection all of its elements that are not obtained in the specified collection.
     * 现在分词作定语表示正在进行的含义, 单个词放在修饰词前, 短语放在修饰词后。
     * 单个现在分词作前置定语, 相当于形容词, 有的现在分词直接就是形容词, 现在分词短语作定语, 相当于定语从句。
     * 不定式短语作定语, 置于被修饰的名词或代词后, 表示将来的动作, 如与被修饰词只有动宾关系, 而无逻辑上的主谓关系, 则需用被动语态
     * @param c collection containing the elements to be retained in this collection.
     * @return true if this collection changed as a result of the call.
     * @throws UnsupportedOperationException if the retainAll operation is not supported by this collection.
     * @throws ClassCastException if the types of one or more elements in this collection are incompatible with the specified collection(optional)
     * @throws NullPointerException if this collection contains one or more null elements and the specified collection does not permit null element(optional), or if the specified collection is null.
     */
    boolean retainAll(Collection<?> c);

    /**
     * Removes all of the elements from this collection(optional operation).
     * The collection will be empty after this method returns.
     * @throws UnsupportedOperationException if the clear operation is not supported by this collection.
     */
    void clear();

    // Comparison and hashing

    /**
     * Compares the specified collection with this collection for equality.
     * While the Collection interface adds no stipulation to the general contract for the Object.equals,
     * programmers who implement the Collection interface "directly" must exercise case if they choose to overview the Object.equals.
     * It is no necessary to do so, and the simplest course of action is to rely on Objects implementation,
     * but the programmer may wish to a "value comparison" in the place of the default "reference comparison".
     * (The List or Set interfaces mandate the value comparison)
     * The general contract for the Object.equals method states that equals must be symmetric
     * (in the words, a.equals(b) if and only if b.equals(a)).
     * The contracts of List.equals and Set.equals state that lists are only equal to other lists, and sets to other sets.
     * Thus, a customer equals method for a collection class that implements neither List nor Set interface must return false
     * when this collection is compared to any List or Set.
     * (By the same logic, it is not possible to write a class that implements both List and Set interfaces.)
     * 不定式作定语, 如与被修饰词只有动宾关系, 而无主谓关系, 则需用被动语态
     * @param o object to be compared for equality with this collection.
     * @return true if the specified object is equals to this collection.
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.
     * While the Collection interface adds no stipulation to the general contract for this collection,
     * programmers should take note that any class that overrides the Object.equals method must also override the Object.hash method
     * in order to satisty the general contract for the Object.hashCode method.
     * In particular, c1.equlas(c2) implies that c1.hashCode(c2).
     * @return the hash code value for this collection
     */
    int hashCode();






}
