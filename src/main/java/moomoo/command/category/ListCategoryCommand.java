package moomoo.command.category;

import moomoo.command.Command;
import moomoo.feature.ScheduleList;
import moomoo.feature.Budget;
import moomoo.feature.category.Category;
import moomoo.feature.category.CategoryList;
import moomoo.feature.Ui;
import moomoo.feature.storage.Storage;

public class ListCategoryCommand extends Command {
    public ListCategoryCommand() {
        super(false, "");
    }

    @Override
    public void execute(ScheduleList calendar, Budget budget, CategoryList categoryList,
                        Storage storage) {
        String categoryString = "";
        for (int i = 0; i < categoryList.size(); i++) {
            Category category = categoryList.get(i);
            categoryString = categoryString.concat("\n" + (i + 1) + ". "
                    + category.name() + " [ $"
                    + category.getTotal() + " ]");
        }
        showCategoryList(categoryString);
    }

    private void showCategoryList(String categories) {
        if (categories.isBlank()) {
            Ui.setOutput("There are no existing categories now, add some using the (category add) command.");
        } else {
            Ui.setOutput("These are your current categories:"
                    + "\n_______________________________________________"
                    + categories
                    + "\n_______________________________________________");
        }
    }
}
