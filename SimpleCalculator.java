import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Simple Calculator ===");
        boolean running = true;

        while (running) {
            System.out.print("\nEnter expression (a operator b) or 'exit' to quit: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                running = false;
                continue;
            }
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                System.out.println("Invalid format. Example: 2 + 3");
                continue;
            }
            try {
                double a = Double.parseDouble(parts[0]);
                double b = Double.parseDouble(parts[2]);
                String op = parts[1];
                double result = switch (op) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> a / b;
                    default -> Double.NaN;
                };
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Calculator closed.");
    }
}
