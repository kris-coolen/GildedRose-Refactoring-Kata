package com.gildedrose;

public class GenericItem {
    private final Item item;

    public GenericItem(Item item) {
        this.item = item;
    }

    public void updateInventory() {
        updateQuality();
        decreaseSellInByOne();
        if (isOverdue()) {
            handleOverDue();
        }
    }

    protected void updateQuality() {
        if (item.name.equals(GildedRose.SULFURAS)) {
            return;
        }
        if (item.name.equals(GildedRose.AGED_BRIE)) {
            increaseQualityByOne();
        } else if (item.name.equals(GildedRose.BACKSTAGE_PASSES)) {
            increaseQualityByOne();
            if (item.sellIn < 11) {
                increaseQualityByOne();
            }
            if (item.sellIn < 6) {
                increaseQualityByOne();
            }

        } else {
            decreaseQualityByOne();
        }
    }

    protected  void decreaseQualityByOne() {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    private  void increaseQualityByOne() {
        if (item.quality < 50) {
            item.quality++;

        }
    }

    private  void decreaseSellInByOne() {
        if (item.name.equals(GildedRose.SULFURAS)) {
            return;
        }
        item.sellIn--;
    }

    private  void handleOverDue() {
        if (item.name.equals(GildedRose.SULFURAS)) {
            return;
        }
        if (item.name.equals(GildedRose.AGED_BRIE)) {
            increaseQualityByOne();
        } else if (item.name.equals(GildedRose.BACKSTAGE_PASSES)) {
            setQualityToZero();
        } else {
            decreaseQualityByOne();
        }
    }

    private void setQualityToZero() {
        item.quality = 0;
    }

    private  boolean isOverdue() {
        return item.sellIn < 0;
    }
}
