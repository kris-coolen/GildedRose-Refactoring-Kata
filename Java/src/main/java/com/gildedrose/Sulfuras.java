package com.gildedrose;

public class Sulfuras extends GenericItem {
    public static final String NAME = "Sulfuras, Hand of Ragnaros";

    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        //do nothing
    }

    @Override
    protected void decreaseSellInByOne() {
        //do nothing
    }

    @Override
    protected void handleOverDue() {
        //do nothing
    }
}
