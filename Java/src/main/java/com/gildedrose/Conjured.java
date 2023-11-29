package com.gildedrose;

public class Conjured extends GenericItem {
    public final static String NAME = "Conjured";
    public Conjured(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        decreaseQualityByOne();
        decreaseQualityByOne();
    }

    @Override
    protected void handleOverDue() {
        decreaseQualityByOne();
        decreaseQualityByOne();
    }
}
