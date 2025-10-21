import java.util.*;
import java.io.*;

public class TodoManager {
    private static class Task implements Serializable {
        String title;
        boolean done;
        Task(String title) { this.title = title; this.done = false; }
        public String toString() { return (done ? "[x] " : "[ ] ") + title; }
    }

    private List<Task> tasks = new ArrayList<>();
    private final String filename = "tasks.dat";

    @SuppressWarnings("unchecked")
    private void loadTasks() {
        File file = new File(filename);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            tasks = (List<Task>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        }
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

    private void showMenu() {
        System.out.println("\n==== To-Do List Manager ====");
        System.out.println("1. View tasks");
        System.out.println("2. Add task");
        System.out.println("3. Mark task done");
        System.out.println("4. Delete task");
        System.out.println("5. Exit");
        System.out.print("Choose option: ");
    }

    private void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private void addTask(Scanner sc) {
        System.out.print("Enter task title: ");
        String title = sc.nextLine().trim();
        if (!title.isEmpty()) tasks.add(new Task(title));
        System.out.println("Task added!");
    }

    private void markDone(Scanner sc) {
        viewTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter task number to mark done: ");
        int num = sc.nextInt(); sc.nextLine();
        if (num > 0 && num <= tasks.size()) {
            tasks.get(num - 1).done = true;
            System.out.println("Task marked as done!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void deleteTask(Scanner sc) {
        viewTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter task number to delete: ");
        int num = sc.nextInt(); sc.nextLine();
        if (num > 0 && num <= tasks.size()) {
            tasks.remove(num - 1);
            System.out.println("Task deleted!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void run() {
        loadTasks();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            showMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> viewTasks();
                case "2" -> addTask(sc);
                case "3" -> markDone(sc);
                case "4" -> deleteTask(sc);
                case "5" -> { running = false; saveTasks(); System.out.println("Goodbye!"); }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        new TodoManager().run();
    }
}
