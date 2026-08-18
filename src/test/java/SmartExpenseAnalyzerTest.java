import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmartExpenseAnalyzerTest {

    @Test
    void testTotalExpense() {
        SmartExpenseAnalyzer analyzer = new SmartExpenseAnalyzer();

        analyzer.addExpense("Food", "Lunch", 250);
        analyzer.addExpense("Travel", "Bus", 100);
        analyzer.addExpense("Food", "Dinner", 350);

        assertEquals(700, analyzer.getTotalExpense());
    }

    @Test
    void testHighestExpense() {
        SmartExpenseAnalyzer analyzer = new SmartExpenseAnalyzer();

        analyzer.addExpense("Food", "Lunch", 250);
        analyzer.addExpense("Shopping", "Clothes", 1500);
        analyzer.addExpense("Travel", "Bus", 100);

        assertEquals(1500, analyzer.getHighestExpense());
    }

    @Test
    void testCategoryTotal() {
        SmartExpenseAnalyzer analyzer = new SmartExpenseAnalyzer();

        analyzer.addExpense("Food", "Lunch", 250);
        analyzer.addExpense("Travel", "Bus", 100);
        analyzer.addExpense("Food", "Dinner", 350);

        assertEquals(600, analyzer.getCategoryTotal("Food"));
    }
}