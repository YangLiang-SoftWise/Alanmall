package com.itcrazy.alan.leadtest.designmode.chain.matter;

import org.springframework.stereotype.Component;

@Component
public class Chips extends AbstractSnacksFood{

    @Override
    public boolean isBuy() {
        return true;
    }

    @Override
    public void eat() {
        System.out.println("i am eating chips");
    }
}
