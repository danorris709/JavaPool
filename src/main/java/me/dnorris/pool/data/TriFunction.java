package me.dnorris.pool.data;

public interface TriFunction<A, B, C> {

    void run(A a, B b, C c);

}
