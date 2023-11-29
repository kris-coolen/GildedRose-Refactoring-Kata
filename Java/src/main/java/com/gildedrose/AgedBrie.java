package com.gildedrose;

public class AgedBrie extends GenericItem {
    public static final String NAME = "Aged Brie";

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQualityByOne();
    }

    @Override
    protected void handleOverDue() {
        increaseQualityByOne();
    }
}
