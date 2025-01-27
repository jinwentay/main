package moomoo.command;

import moomoo.feature.Budget;
import moomoo.feature.MooMooException;
import moomoo.feature.Ui;
import moomoo.stubs.CategoryListStub;
import moomoo.stubs.ScheduleListStub;
import moomoo.stubs.StorageStub;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BudgetCommandTest {
    @Test
    public void testSetBudgetCommand() throws MooMooException, IOException {
        File budgetFile = File.createTempFile("budget", ".txt");
        budgetFile.deleteOnExit();

        File scheduleFile = File.createTempFile("schedule", ".txt");
        scheduleFile.deleteOnExit();

        File expenditureFile = File.createTempFile("expenditure", ".txt");
        expenditureFile.deleteOnExit();

        CategoryListStub newCatList = new CategoryListStub();

        newCatList.add(null);

        ArrayList<String> categories = new ArrayList<>();

        categories.add("Shoes");
        categories.add("Food");
        categories.add("Places to go");
        categories.add("Test Value");

        ArrayList<Double> budgets = new ArrayList<>();

        budgets.add(50.0);
        budgets.add(100.0);
        budgets.add(-150.0);
        budgets.add(200.0);


        ScheduleListStub newCalendar = new ScheduleListStub();
        StorageStub newStorage = new StorageStub();
        Budget newBudget = new Budget();

        SetBudgetCommand setBudget = new SetBudgetCommand(false, categories, budgets);
        setBudget.execute(newCalendar, newBudget, newCatList, newStorage);
        assertEquals(
                ".__________________________________.\n"
                        + "| ___ _   _ ___   ___ ___ _____    |\n"
                        + "|| _ ) | | |   \\ / __| _ |_   _|   |\n"
                        + "|| _ \\ |_| | |) | (_ | _|  | |     |\n"
                        + "||___/\\___/|___/ \\___|___| |_|     |\n"
                        + "|                                  |\n"
                        + "|Category : shoes                  |\n"
                        + "|$50.00                            |\n"
                        + ".----------------------------------.\n"
                        + "        \\   ^__^\n"
                        + "         \\  (oo)\\_______\n"
                        + "            (__)\\       )\\/\\\n"
                        + "                ||----w |\n"
                        + "                ||     ||\n"
                + ".__________________________________.\n"
                        + "| ___ _   _ ___   ___ ___ _____    |\n"
                        + "|| _ ) | | |   \\ / __| _ |_   _|   |\n"
                        + "|| _ \\ |_| | |) | (_ | _|  | |     |\n"
                        + "||___/\\___/|___/ \\___|___| |_|     |\n"
                        + "|                                  |\n"
                        + "|Category : food                   |\n"
                        + "|$100.00                           |\n"
                        + ".----------------------------------.\n"
                        + "        \\   ^__^\n"
                        + "         \\  (oo)\\_______\n"
                        + "            (__)\\       )\\/\\\n"
                        + "                ||----w |\n"
                        + "                ||     ||\n"
                + "Please set your budget for places to go to a value more than 0\n"
                + "test value category does not exist. Please add it first.\n", Ui.getOutput());
    }

    @Test
    public void testEditBudgetCommand() throws MooMooException, IOException  {
        File budgetFile = File.createTempFile("budget", ".txt");
        budgetFile.deleteOnExit();

        File scheduleFile = File.createTempFile("schedule", ".txt");
        scheduleFile.deleteOnExit();

        File expenditureFile = File.createTempFile("expenditure", ".txt");
        expenditureFile.deleteOnExit();

        CategoryListStub newCatList = new CategoryListStub();
        newCatList.add(null);

        ArrayList<String> categories = new ArrayList<>();

        categories.add("shoes");
        categories.add("window");
        categories.add("sweets");
        categories.add("Invalid Value");

        ArrayList<Double> budgets = new ArrayList<>();

        budgets.add(123.45);
        budgets.add(1123.0);
        budgets.add(217.0);
        budgets.add(122.23);

        ScheduleListStub newCalendar = new ScheduleListStub();
        StorageStub newStorage = new StorageStub();
        Budget newBudget = new Budget();

        SetBudgetCommand setBudget = new SetBudgetCommand(false, categories, budgets);
        setBudget.execute(newCalendar, newBudget, newCatList, newStorage);

        budgets.set(0, 300.0);
        budgets.set(1, -675.0);
        budgets.set(2, 675.0);

        EditBudgetCommand editBudget = new EditBudgetCommand(false, categories, budgets);
        editBudget.execute(newCalendar, newBudget, newCatList, newStorage);

        assertEquals(".___________________________________________________________________.\n"
                + "|You have changed the budget for shoes from $123.45 to $300.00      |\n"
                + "|You have changed the budget for sweets from $217.00 to $675.00     |\n"
                + ".-------------------------------------------------------------------.\n", Ui.getTestOutput());
    }

    @Test
    public void testListBudgetCommand() throws MooMooException, IOException  {
        File budgetFile = File.createTempFile("budget", ".txt");
        budgetFile.deleteOnExit();

        File scheduleFile = File.createTempFile("schedule", ".txt");
        scheduleFile.deleteOnExit();

        File expenditureFile = File.createTempFile("expenditure", ".txt");
        expenditureFile.deleteOnExit();

        CategoryListStub newCatList = new CategoryListStub();
        newCatList.add(null);

        ArrayList<String> categories = new ArrayList<>();

        categories.add("shoes");
        categories.add("food");
        categories.add("places to go");
        categories.add("Test Value");
        categories.add("sweets");
        ArrayList<Double> budgets = new ArrayList<>();

        budgets.add(183.0);
        budgets.add(128.0);
        budgets.add(527.0);
        budgets.add(103.34);
        budgets.add(105.0);

        ScheduleListStub newCalendar = new ScheduleListStub();
        StorageStub newStorage = new StorageStub();

        Budget newBudget = new Budget();

        SetBudgetCommand setBudget = new SetBudgetCommand(false, categories, budgets);
        setBudget.execute(newCalendar, newBudget, newCatList, newStorage);

        ArrayList<String> listCategories = new ArrayList<>();

        listCategories.add("shoes");
        listCategories.add("food");
        listCategories.add("places to go");
        listCategories.add("Test Value");
        listCategories.add("sweets");

        ListBudgetCommand listBudget = new ListBudgetCommand(false, listCategories);
        listBudget.execute(newCalendar, newBudget, newCatList, newStorage);

        assertEquals(".__________________________________________________.\n"
                + "|Budget for shoes is $183.00                       |\n"
                + "|Budget for food is $128.00                        |\n"
                + "|Budget for places to go is $527.00                |\n"
                + "|Budget for sweets is $105.00                      |\n"
                + ".--------------------------------------------------.\n", Ui.getTestOutput());
    }

    @Test
    public void testSavingsBudgetCommand() throws MooMooException, IOException  {
        File budgetFile = File.createTempFile("budget", ".txt");
        budgetFile.deleteOnExit();

        File scheduleFile = File.createTempFile("schedule", ".txt");
        scheduleFile.deleteOnExit();

        File expenditureFile = File.createTempFile("expenditure", ".txt");
        expenditureFile.deleteOnExit();

        CategoryListStub newCatList = new CategoryListStub();
        newCatList.add(null);

        ArrayList<String> categories = new ArrayList<>();

        categories.add("slippers");
        categories.add("window");
        categories.add("laptop");
        categories.add("Dogs");

        ArrayList<Double> budgets = new ArrayList<>();

        budgets.add(875.0);
        budgets.add(938.0);
        budgets.add(75.89);
        budgets.add(100.58);

        for (int i = 0; i < newCatList.getCategoryList().size(); ++i) {
            newCatList.getCategoryList().get(i).add(null);
        }

        ScheduleListStub newCalendar = new ScheduleListStub();
        StorageStub newStorage = new StorageStub();
        Budget newBudget = new Budget();

        SetBudgetCommand setBudget = new SetBudgetCommand(false, categories, budgets);
        setBudget.execute(newCalendar, newBudget, newCatList, newStorage);

        LocalDate startDate = LocalDate.of(2019, 9, 15);
        LocalDate endDate = LocalDate.of(2019, 11, 15);

        SavingsBudgetCommand savingsBudget = new SavingsBudgetCommand(false, categories, startDate, endDate);
        savingsBudget.execute(newCalendar, newBudget, newCatList, newStorage);

        assertEquals("._____________________________________________________________________________________"
                + "_____________.\n"
                + "|slippers category does not exist. Please create it first.                                         "
                + "|\n"
                + "|Your savings for window from SEPTEMBER 2019 to NOVEMBER 2019 is: $2549.00                         "
                + "|\n"
                + "|You have overspent for your budget for laptop from SEPTEMBER 2019 to NOVEMBER 2019 by: $37.33     "
                + "|\n"
                + "|dogs category does not exist. Please create it first.                                             "
                + "|\n"
                + "|Your total savings: $2511.67                                                                      "
                + "|\n"
                + ".--------------------------------------------------------------------------------------------------."
                + "\n", Ui.getTestOutput());

        savingsBudget = new SavingsBudgetCommand(false, categories, startDate, null);
        savingsBudget.execute(newCalendar, newBudget, newCatList, newStorage);

        assertEquals(".___________________________________________________________________________.\n"
                + "|slippers category does not exist. Please create it first.                  |\n"
                + "|Your savings for window for SEPTEMBER 2019 is: $788.00                     |\n"
                + "|You have overspent your budget for laptop for SEPTEMBER 2019 by $74.11     |\n"
                + "|dogs category does not exist. Please create it first.                      |\n"
                + "|Your total savings: $713.89                                                |\n"
                + ".---------------------------------------------------------------------------.\n",
                Ui.getTestOutput());

        categories.clear();
        assertEquals(0, categories.size());

        savingsBudget = new SavingsBudgetCommand(false, categories, startDate, null);
        savingsBudget.execute(newCalendar, newBudget, newCatList, newStorage);

        assertEquals(".________________________________________________________________________________.\n"
                + "|The budget for shoes does not exist. Please set it using budget set.            |\n"
                + "|The budget for food does not exist. Please set it using budget set.             |\n"
                + "|Your savings for window for SEPTEMBER 2019 is: $788.00                          |\n"
                + "|The budget for places to go does not exist. Please set it using budget set.     |\n"
                + "|The budget for sweets does not exist. Please set it using budget set.           |\n"
                + "|You have overspent your budget for laptop for SEPTEMBER 2019 by $74.11          |\n"
                + "|Your total savings: $713.89                                                     |\n"
                + ".--------------------------------------------------------------------------------.\n",
                Ui.getTestOutput());

        categories.clear();
        assertEquals(0, categories.size());
        categories.add("laptop");
        assertEquals(1, categories.size());

        startDate = LocalDate.of(2017, 9, 15);
        endDate = LocalDate.of(2019, 2, 15);

        savingsBudget = new SavingsBudgetCommand(false, categories, startDate, endDate);
        savingsBudget.execute(newCalendar, newBudget, newCatList, newStorage);

        assertEquals(".______________________________________________________________________________.\n"
                + "|Your savings for laptop from SEPTEMBER 2017 to FEBRUARY 2019 is: $1066.02     |\n"
                + "|Your total savings: $1066.02                                                  |\n"
                + ".------------------------------------------------------------------------------.\n",
                Ui.getTestOutput());
    }
}