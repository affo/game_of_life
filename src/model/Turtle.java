package model;

import java.util.Collection;

public interface Turtle {

    public Turtle penDown();

    public Turtle penUp();

    public Turtle right();

    public Turtle left();

    public Turtle up();

    public Turtle down();

    public Collection<?> getDraft();

    public Turtle times(Integer times);
}
