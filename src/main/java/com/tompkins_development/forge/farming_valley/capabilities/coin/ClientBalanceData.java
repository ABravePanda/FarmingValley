package com.tompkins_development.forge.farming_valley.capabilities.coin;

public class ClientBalanceData {
    private static int balance;


    public static void set(int balance) {
        ClientBalanceData.balance = balance;
    }

    public static int getBalance() {
        return balance;
    }

}
