package me.dnorris.pool.data;

/**
 *
 * Interface used for representation a function that takes one parameter but
 * doesn't return anything.
 *
 * @param <T> The type of the parameter for the function
 * @author https://github.com/danorris709
 */
public interface Method<T> {

    void run(T t);

}
