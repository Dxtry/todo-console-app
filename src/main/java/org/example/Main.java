package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    //Creating a repository object and a scanner for input
    private static final TaskRepository taskrepository = new TaskRepository();
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        //Main loop - the user selects an item that performs the desired action
        while(true){
            System.out.println("Выберите действие: ");
            System.out.println("1. Показать все задачи");
            System.out.println("2. Добавить задачу");
            System.out.println("3. Завершить задачу");
            System.out.println("4. Удалить задачу");
            System.out.println("5. Выйти");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    getAllTasks();
                    break;
                case "2":
                    addTasks();
                    break;
                case "3":
                    is_doneTask();
                    break;
                case "4":
                    deleteTask();
                    break;
                case "5":
                    System.out.println("До встречи!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    //Show all Tasks
    private static void getAllTasks() {
        List<Task> tasks = taskrepository.getAllTasks();
        if(tasks.isEmpty()){
            System.out.println("Задач пока нет");
        } else {
            for (Task task : tasks){
                System.out.println(task.getId() + ": " + task);
            }
        }
    }

    //Add Task
    private static void addTasks(){
        String title = readNonEmptyString("Введите нахвание задачи: ");
        taskrepository.addTask(title);
        System.out.println("Задача добавлена");
    }

    //Mark task as done
    private static void is_doneTask(){
        int id = readIntFromUser("Введите ID задачи, которую хотите пометить выполненной");
        taskrepository.is_doneTask(id);
        System.out.println("Задача отмечена как выполненная");
    }

    //delete Task
    private static void deleteTask(){
        int id = readIntFromUser("Введите ID задачи");
        taskrepository.deleteTask(id);
        System.out.println("Задача успешно удалена из списка");
    }

    //Error handling if a non-numeric value is entered instead of an ID
    private static int readIntFromUser(String prompt) {
        while(true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try{
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("Ошибка: введите корректное число!");
            }
        }
    }

    //Error handling if the task name field is empty
    private static String readNonEmptyString(String prompt){
        while(true){
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if(!input.isEmpty()){
                return input;
            } else {
                System.out.println("Ошибка: строка не может быть пустой");
            }
        }
    }
}
