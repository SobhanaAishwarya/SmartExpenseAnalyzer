import java.util.ArrayList;
import java.util.List;

public class SmartExpenseAnalyzer {

    static class Expense {
        String category;
        String description;
        double amount;

        Expense(String category, String description, double amount) {
            this.category = category;
            this.description = description;
            this.amount = amount;
        }
    }

    private final List<Expense> expenses = new ArrayList<>();

    public void addExpense(String category, String description, double amount) {
        expenses.add(new Expense(category, description, amount));
    }

    public double getTotalExpense() {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.amount;
        }

        return total;
    }

    public double getHighestExpense() {
        double highest = 0;

        for (Expense expense : expenses) {
            if (expense.amount > highest) {
                highest = expense.amount;
            }
        }

        return highest;
    }

    public double getCategoryTotal(String category) {
        double total = 0;

        for (Expense expense : expenses) {
            if (expense.category.equalsIgnoreCase(category)) {
                total += expense.amount;
            }
        }

        return total;
    }

    public static void main(String[] args) {

        SmartExpenseAnalyzer analyzer = new SmartExpenseAnalyzer();

        analyzer.addExpense("Food", "Lunch", 250);
        analyzer.addExpense("Travel", "Bus", 100);
        analyzer.addExpense("Shopping", "Clothes", 1500);
        analyzer.addExpense("Food", "Dinner", 350);

        System.out.println("===== SMART EXPENSE ANALYZER =====");
        System.out.println("Total Expense: ₹" + analyzer.getTotalExpense());
        System.out.println("Highest Expense: ₹" + analyzer.getHighestExpense());
        System.out.println("Food Expenses: ₹" +
                analyzer.getCategoryTotal("Food"));

        System.out.println("==================================");
    }
}