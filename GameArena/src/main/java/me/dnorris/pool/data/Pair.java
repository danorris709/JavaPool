package me.dnorris.pool.data;

import javax.annotation.Nonnull;

/**
 *
 *  A DTO used for reducing the number of parameters passed to constructors
 *  and keeping related immutable objects together.
 *
 * @param <A> First type
 * @param <B> Second type
 * @author https://github.com/danorris709
 */
public class Pair<A, B> {

    private final A first; // The first object of type A
    private final B second; // The second object of type B

    /**
     *
     * Constructor assigning the first object of type {@link A} and
     * the second object of type {@link B}
     *
     * @param first First object - cannot be null
     * @param second Second object - cannot be null
     */
    public Pair(@Nonnull A first, @Nonnull B second) {
        this.first = first;
        this.second = second;
    }

    /**
     *
     * Get the first object of type {@link A}
     *
     * @return Non-null first object
     */
    @Nonnull
    public A getFirst() {
        return this.first;
    }

    /**
     *
     * Get the first object of type {@link B}
     *
     * @return Non-null second object
     */
    @Nonnull
    public B getSecond() {
        return this.second;
    }

    /**
     *
     * String representation of the {@link Pair}
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
