import java.util.Scanner;

public class Play {
    static Scanner scanner;

    public static void main(String[] args) {

        IWorld world = world(args);
        scanner = new Scanner(System.in);
        Robot robot;
        String name = getInput("What do you want to name your robot?");
        Display("Hello Kiddo!");

        robot = new Robot(name,world);
        Display(robot.toString());

        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            System.out.println(robot);
        } while (shouldContinue);
    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    public static void Display(String message){
        System.out.println(message);
    }
    public static IWorld world(String[] args) {
        IWorld world;
        Maze maze;
        if (args.length == 0) {
            maze = new EmptyMaze();
            Display("Loaded EmptyMaze.");
            world = new TextWorld(maze);
            return world;
        } else if (args[0].equalsIgnoreCase("text")) {
            String choiceMaze = args[1].toLowerCase();
            switch (choiceMaze) {
                case "":
                case "emptymaze":
                    maze = new EmptyMaze();
                    Display("Loaded EmptyMaze.");
                    world = new TextWorld(maze);
                    return world;
                case "simplemaze":
                    maze = new SimpleMaze();
                    Display("Loaded SimpleMaze.");
                    world = new TextWorld(maze);
                    return world;
                case "randommaze":
                    maze = new RandomMaze();
                    Display("Loaded RandomMaze.");
                    world = new TextWorld(maze);
                    return world;
//                case "designedmaze":
//                    maze = new DesignedMaze();
//                    Display("Loaded DesignedMaze.");
//                    world = new TextWorld(maze);
//                    return world;

            }
        } else if (args[0].equalsIgnoreCase("turtle")) {
            String choiceMaze = args[1].toLowerCase();
            switch (choiceMaze) {
                case "":
                case "emptymaze":
                    maze = new EmptyMaze();
                    Display("Loaded EmptyMaze.");
                    world = new TurtleWorld(maze);
                    return world;
                case "simplemaze":
                    maze = new SimpleMaze();
                    Display("Loaded SimpleMaze");
                    world = new TurtleWorld(maze);
                    return world;
                case "randommaze":
                    maze = new RandomMaze();
                    Display("Loaded RandomMaze.");
                    world = new TurtleWorld(maze);
                    return world;
//                case "designedmaze":
//                    maze = new DesignedMaze();
//                    Display("Loaded DesignedMaze.");
//                    world = new TurtleWorld(maze);
//                    return world;
            }
        }
        maze = new EmptyMaze();
        Display("Loaded EmptyMaze.");
        world = new TextWorld(maze);
        return world;
    }
}