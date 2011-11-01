package com.ziphyre.listprocessor;

/**
 *
 */
public class Foo {
    private int in;
    private int out;

    {
        in = 0;
        out = 0;
    }

    Foo(int in) {
        this.in = in;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }
}