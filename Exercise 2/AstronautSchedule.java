import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private int priority;

    public Task(String description, LocalTime startTime, LocalTime endTime, int priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task(description=" + description + ", startTime=" + startTime + ", endTime=" + endTime + ", priority=" + priority + ")";
    }
}

class ScheduleManager {
    private List<Task> tasks;

    public ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public boolean addTask(Task newTask) {
        for (Task task : tasks) {
            if (tasksOverlap(task, newTask)) {
                System.out.println("Error: Task times overlap with an existing task.");
                return false;
            }
        }
        tasks.add(newTask);
        System.out.println("Task added successfully!");
        return true;
    }

    public boolean removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task removed successfully!");
            return true;
        } else {
            System.out.println("Error: Invalid task index!");
            return false;
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            tasks.sort(Comparator.comparing(Task::getStartTime));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ": " + tasks.get(i));
            }
        }
    }

    private boolean tasksOverlap(Task t1, Task t2) {
        return !t1.getEndTime().isBefore(t2.getStartTime()) && !t2.getEndTime().isBefore(t1.getStartTime());
    }
}

public class AstronautSchedule {
    private static Scanner scanner = new Scanner(System.in);
    private static ScheduleManager manager = new ScheduleManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter start time (HH:MM): ");
        String startTimeInput = scanner.nextLine();
        System.out.print("Enter end time (HH:MM): ");
        String endTimeInput = scanner.nextLine();
        System.out.print("Enter priority (1-5): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            LocalTime startTime = LocalTime.parse(startTimeInput);
            LocalTime endTime = LocalTime.parse(endTimeInput);

            if (endTime.isBefore(startTime)) {
                System.out.println("Error: End time cannot be before start time.");
                return;
            }

            Task newTask = new Task(description, startTime, endTime, priority);
            manager.addTask(newTask);
        } catch (Exception e) {
            System.out.println("Error: Invalid time format. Please use HH:MM.");
        }
    }

    private static void removeTask() {
        System.out.print("Enter task index to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        manager.removeTask(index);
    }

    private static void viewTasks() {
        manager.viewTasks();
    }
}

