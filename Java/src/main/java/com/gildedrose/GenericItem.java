package com.gildedrose;

public class GenericItem {
    private final Item item;

    public GenericItem(Item item) {
        this.item = item;
    }

    public static GenericItem create(Item item) {
        if (item.name.equals(AgedBrie.NAME)) {
            return new AgedBrie(item);
        }
        if(item.name.equals(GildedRose.SULFURAS)) {
            return new Sulfuras(item);
        }
        return new GenericItem(item);
    }

    public void updateInventory() {
        updateQuality();
        decreaseSellInByOne();
        if (isOverdue()) {
            handleOverDue();
        }
    }

    protected void updateQuality() {
        if (item.name.equals(GildedRose.BACKSTAGE_PASSES)) {
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

    protected void decreaseQualityByOne() {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    protected void decreaseSellInByOne() {
        item.sellIn--;
    }

    private boolean isOverdue() {
        return item.sellIn < 0;
    }

    protected void increaseQualityByOne() {
        if (item.quality < 50) {
            item.quality++;

        }
    }

    protected void handleOverDue() {
        if (item.name.equals(GildedRose.BACKSTAGE_PASSES)) {
            setQualityToZero();
        } else {
            decreaseQualityByOne();
        }
    }

    private void setQualityToZero() {
        item.quality = 0;
    }
}
