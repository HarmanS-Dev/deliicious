package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {
    private static final String RECEIPT_FOLDER = "src/main/resources/receipts/";

    public static void saveReceipt(Order order) {
        java.io.File folder = new java.io.File(RECEIPT_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Generate the filename: yyyyMMdd-hhmmss.txt
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String filename = RECEIPT_FOLDER + timestamp + ".txt";

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("----------------------------------------\n");
            writer.write("              DELI-cious\n");
            writer.write("        Home of the Good Burger\n");
            writer.write("----------------------------------------\n");
            writer.write("ORDER TIME: " + now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + "\n");
            writer.write("----------------------------------------\n");

            writer.write(String.format("%-28s %10s\n", "ITEM", "PRICE"));
            writer.write("----------------------------------------\n");

            double subtotal = 0.0;

            // Write each item and its details
            for (OrderItem item : order.getItems()) {
                writer.write(item.getDescription() + "\n");
                subtotal += item.getPrice();
            }

            // Summary and Totals
            writer.write("----------------------------------------\n");
            writer.write(String.format("%-28s %10.2f\n", "SUBTOTAL", subtotal));
            // Assuming no tax for simplicity
            writer.write(String.format("%-28s %10.2f\n", "TAX (0.00%)", 0.00));
            writer.write("----------------------------------------\n");
            writer.write(String.format("%-28s $%9.2f\n", "**TOTAL**", order.getTotalCost()));
            writer.write("========================================\n");
            writer.write("          THANK YOU FOR YOUR ORDER!\n");
            writer.write("========================================\n");

            System.out.println("\n✅ Order completed! Receipt saved to: " + filename);

        } catch (IOException e) {
            System.err.println("Error writing receipt file: " + e.getMessage());
        }
    }
}
