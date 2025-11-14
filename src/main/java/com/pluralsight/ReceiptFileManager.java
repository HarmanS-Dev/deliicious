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
            writer.write("==================================================\n");
            writer.write("              DELI-cious Receipt\n");
            writer.write("Order Time: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("==================================================\n\n");

            writer.write("ORDER DETAILS:\n");
            for (OrderItem item : order.getItems()) {
                writer.write("--------------------------------------------------\n");
                writer.write(item.getDescription() + "\n");
            }
            writer.write("--------------------------------------------------\n");
            writer.write(String.format("TOTAL COST: $%.2f\n", order.getTotalCost()));
            writer.write("==================================================\n");
            System.out.println("\n✅ Order completed! Receipt saved to: " + filename);

        } catch (IOException e) {
            System.err.println("Error writing receipt file: " + e.getMessage());
        }
    }
}
