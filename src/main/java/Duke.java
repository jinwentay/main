import duke.command.Command;
import duke.task.Storage;
import duke.task.TaskList;
import duke.task.Ui;
import duke.task.DukeException;
import duke.task.Parser;

/**
 * Runs Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Storage, Ui and TaskList.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the command line interface, reads input from user and returns result accordingly.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
    }

    /**
     * Runs Duke.
     * @param args Argument values given when running the program
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
