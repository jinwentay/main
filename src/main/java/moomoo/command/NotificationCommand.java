package moomoo.command;

import moomoo.task.Budget;
import moomoo.task.Category;
import moomoo.task.CategoryList;
import moomoo.task.MooMooException;
import moomoo.task.ScheduleList;
import moomoo.task.Storage;
import moomoo.task.Ui;

public class NotificationCommand extends Command {
    private String cat;
    private double expenditure;

    /**
     * Alerts user if user exceeded the budget.
     * @param cat The name of the category that user just added his expenditure to.
     * @param expenditure The total current expenditure of that category.
     */
    public NotificationCommand(String cat, double expenditure) {
        super(false, "");
        this.cat = cat;
        this.expenditure = expenditure;
    }

    @Override
    public void execute(ScheduleList calendar, Budget budget, CategoryList catList, Category category,
                        Ui ui, Storage storage) throws MooMooException {
        if (expenditure > budget.getBudgetFromCategory(cat)) {
            ui.setOutput("You have exceeded your budget of " + budget.getBudgetFromCategory(cat) + " for " + cat + "!");
            ui.showResponse();
        } else if (expenditure > budget.getBudgetFromCategory(cat) * 0.9) {
            ui.setOutput("You are reaching your budget limit of " + budget.getBudgetFromCategory(cat)
                    + " for " + cat + ".");
        }
        double balance = budget.getBudgetFromCategory(cat) - expenditure;
        ui.setOutput("Budget remaining = " + balance);
        ui.showResponse();
    }
}
