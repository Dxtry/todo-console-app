package org.example;

public class Task {

    /*The id and title fields are fixed when the task is created and should not be changed.*/

    private final int id;
    private final String title;
    private boolean is_done;

    /*A simple constructor for creating task objects*/

    public Task(int id, String title, boolean is_done){
        this.id = id;
        this.title = title;
        this.is_done = is_done;
    }

    /*Getters and setter*/
    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public boolean isDone(){
        return is_done;
    }

    public void setDone(boolean is_done){
        this.is_done = is_done;
    }

    /*Overriding the toString method so that the object is
    output with a [ ] field where the task completion mark will be*/

    @Override
    public String toString() {
        return "[" + (is_done ? "✓" : " ") + "] " + title;
    }
}
