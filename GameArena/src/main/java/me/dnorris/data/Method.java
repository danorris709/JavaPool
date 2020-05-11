package me.dnorris.data;

/**
 *
 * Interface used for representation a function that takes one parameter but
 * doesn't return anything.
 *
 * @param <T> The type of the parameter for the function
 * @author https://github.com/danorris709
 */
public interface Method<T> {

    /**
     *
     * Similar naming to {@link Runnable}. As can be represented by a lambda and thus there's no
     * name that represents the function better.
     *
     * @param t The only parameter of type {@link T}
     */
    void run(T t);

}
