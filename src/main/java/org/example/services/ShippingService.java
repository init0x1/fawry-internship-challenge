package org.example.services;

import org.example.interfaces.Shippable;

import java.util.*;

public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 10.0;

    public static double calculateShippingFee(List<Shippable> items) {
        if (items.isEmpty()) {
            return 0.0;
        }

        double totalWeight = 0.0;
        for (Shippable item : items) {
            totalWeight += item.getWeight();
        }

        return totalWeight * SHIPPING_RATE_PER_KG;
    }

    public static void processShipment(List<Shippable> items, Map<String, Integer> quantities) {
        if (items.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");

        double totalWeight = 0;
        for (Shippable item : items) {
            int qty = quantities.get(item.getName());
            double itemWeight = item.getWeight() * qty;
            totalWeight += itemWeight;

            System.out.printf("%dx %s %.0fg%n", qty, item.getName(), itemWeight * 1000);
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }


}
