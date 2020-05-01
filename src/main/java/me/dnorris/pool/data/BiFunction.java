package me.dnorris.pool.data;

/**
 * Interface used for representation a function that takes two parameters but
 * doesn't return anything.
 *
 * @param <A> The type of the first parameter of the function
 * @param <B> The type of the second paramter of the function
 * @author https://github.com/danorris709
 */
public interface BiFunction<A, B> {

    /**
     *
     * Similar naming to {@link Runnable}. As can be represented by a lambda and thus there's no
     * name that represents the function better.
     *
     * @param a The first object of type {@link A}
     * @param b The second object of type {@link B}
     */
    void run(A a, B b);

}
