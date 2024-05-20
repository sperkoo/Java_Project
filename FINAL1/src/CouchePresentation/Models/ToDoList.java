package CouchePresentation.Models;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private String name;
    private List<Task> tasks;

    public ToDoList(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }

    // Other methods as needed
}
