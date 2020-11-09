package com.hyorin.myshop.provider;

import java.util.Objects;

public class Test {

    public String name ;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return age == test.age &&
                Objects.equals(name, test.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) {

        Object o = new Object();
        System.out.println(o);
        System.out.println(o.hashCode());

    }
}
