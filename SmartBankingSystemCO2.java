import java.util.*;

class FenwickTree {
    int[] bit;
    int n;

    FenwickTree(int n) {
        this.n = n;
        bit = new int[n + 1];
    }

    void update(int index, int value) {
        while (index <= n) {
            bit[index] += value;
            index += index & (-index);
        }
    }

    int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += bit[index];
            index -= index & (-index);
        }
        return sum;
    }

    int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }
}

class Customer {
    int accountNo;
    int balance;

    Customer(int accountNo, int balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }
}

public class SmartBankingSystemCO2 {

    public static void main(String[] args) {

        TreeMap<Integer, Customer> bPlusTree = new TreeMap<>();

        bPlusTree.put(1001, new Customer(1001, 50000));
        bPlusTree.put(1002, new Customer(1002, 75000));
        bPlusTree.put(1003, new Customer(1003, 30000));
        bPlusTree.put(1004, new Customer(1004, 90000));
        bPlusTree.put(1005, new Customer(1005, 60000));

        int searchAcc = 1004;

        if (bPlusTree.containsKey(searchAcc)) {
            Customer c = bPlusTree.get(searchAcc);
            System.out.println("Customer Account Found");
            System.out.println("Account Number : " + c.accountNo);
            System.out.println("Balance : " + c.balance);
        }

        int[] transactions = {5000, 3000, 4500, 2000, 6000};

        FenwickTree ft = new FenwickTree(transactions.length);

        for (int i = 0; i < transactions.length; i++) {
            ft.update(i + 1, transactions[i]);
        }

        System.out.println("\nTotal Transactions (Day 1-5): "
                + ft.rangeQuery(1, 5));

        System.out.println("Total Transactions (Day 2-4): "
                + ft.rangeQuery(2, 4));

        ft.update(3, 2000);

        System.out.println("\nAfter Updating Day 3 Transaction");

        System.out.println("Total Transactions (Day 1-5): "
                + ft.rangeQuery(1, 5));
    }
}