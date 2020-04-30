package me.dnorris.pool.data;

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

    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return this.first;
    }

    public B getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
