package com.iss.gold.pojo;

import java.util.Date;

/**
 *
 * @author Vikram
 */
public class Buyer extends Consumer {

    public Buyer(String name, Date timestamp, int rate, int amount) {
        super(name, timestamp, rate, amount);
    }

    @Override
    public String getDisplayString() {
        return this.getName() + "/" + this.getAmount() + "/" + this.getRate();
    }
}
