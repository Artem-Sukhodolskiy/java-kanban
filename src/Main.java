public class Main {

    public static void main(String[] args)
    {
        TaskManager taskManager = new TaskManager();

        System.out.println("Поехали!");
        // создание задач
        taskManager.createTask("Задача1", "описание1");
        taskManager.createTask("Задача2", "описание2");
        taskManager.createEpic("Эпик1", "описание1");
        taskManager.createSubtask("Подзадача1", "описание1", "Эпик1" );
        taskManager.createSubtask("Подзадача2", "описание2", "Эпик1");
        taskManager.createEpic("Эпик2", "описание2");
        taskManager.createSubtask("Подзадача3", "описание3", "Эпик2" );


        // распечатать задачи/эпики/подзадачи
        taskManager.printAllTasks();
        taskManager.printAllEpics();
        taskManager.printAllSubtasks();

        // Иземенение и проверка
        System.out.println("-".repeat(50));
        Task newTask = new Task ("Задача1", "Описание1", Status.IN_PROGRESS);
        taskManager.updateTask(1, newTask);
        Subtask newSubtask = new Subtask("Подзадача1", "Описание1", Status.DONE, "Эпик1");
        taskManager.updateSubtask(2, newSubtask);

        taskManager.printAllTasks();
        taskManager.printAllEpics();
        taskManager.printAllSubtasks();

        //Удаление
        System.out.println("-".repeat(50));
        taskManager.removeTask(1);
        taskManager.removeEpic(2);

        taskManager.printAllTasks();
        taskManager.printAllEpics();
        taskManager.printAllSubtasks();
    }
}
