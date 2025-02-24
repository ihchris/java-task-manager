import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    private static final String FILE_NAME = "tasks.txt";
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewTasks();
                    break;
                case 2:
                    System.out.print("Enter a new task: ");
                    String newTask = scanner.nextLine();
                    addTask(newTask);
                    break;
                case 3:
                    System.out.print("Enter the task number to delete: ");
                    int taskNumber = scanner.nextInt();
                    deleteTask(taskNumber);
                    break;
                case 4:
                    saveTasksToFile();
                    System.out.println("Exiting Task Manager. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\nYour Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask(String task) {
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    private static void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number.");
        } else {
            String removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Task deleted: " + removedTask);
        }
    }

    private static void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            System.out.println("No existing tasks found. Starting with an empty list.");
        }
    }

    private static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }
}