package com.eazybytes.accounts.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionAggregator {


 
    public static Map<String, Integer> aggregateTransactions(List<String> transactions) {
        Map<String, Integer> userTotals = new HashMap<>();
 
        for (String record : transactions) {
            try {
                String[] parts = record.split(",");
                if (parts.length != 2) continue;
 
                String userId = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());
 
                userTotals.put(userId, userTotals.getOrDefault(userId, 0) + amount);
 
            } catch (NumberFormatException e) {
                // Skip malformed amount entries
                continue;
            }
        }
 
        return userTotals;
    }

    /***
     * output
     * {
     *   "U1": 300,
     *   "U2": 150,
     *   "U3": 50
     * }
     *
     * find
     * 1. Parse the list.
     * 2. Aggregate the total amount per user.
     * 3. Return the result as a Map<String, Integer>.
     *
     *
     * handled scenarios
     * Constraints:
     * 1. handle malformed entries (skip them silently).
     * 2. case-sensitive user IDs.
     * 3. optimize for readability and performance.
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> transactions = Arrays.asList("U1,100", "U2,150", "U1,200", "U3,50", "U2,Oops");
 
        Map<String, Integer> result = aggregateTransactions(transactions);
        result.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}