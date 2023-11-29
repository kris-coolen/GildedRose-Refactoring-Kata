package com.gildedrose;

public class BackStagePasses extends GenericItem {
    public static final String NAME = "Backstage passes to a TAFKAL80ETC concert";

    public BackStagePasses(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQualityByOne();
        if (item.sellIn < 11) {
            increaseQualityByOne();
        }
        if (item.sellIn < 6) {
            increaseQualityByOne();
        }
    }

    @Override
    protected void handleOverDue() {
        setQualityToZero();
    }
}
