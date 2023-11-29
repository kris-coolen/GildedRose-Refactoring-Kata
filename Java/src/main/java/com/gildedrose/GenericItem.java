package com.gildedrose;

public class GenericItem {
    protected final Item item;

    public GenericItem(Item item) {
        this.item = item;
    }

    public static GenericItem create(Item item) {
        if (item.name.equals(AgedBrie.NAME)) {
            return new AgedBrie(item);
        }
        if (item.name.equals(Sulfuras.NAME)) {
            return new Sulfuras(item);
        }
        if (item.name.equals(BackStagePasses.NAME)) {
            return new BackStagePasses(item);
        }
        if (item.name.equals(Conjured.NAME)) {
            return new Conjured(item);
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
        decreaseQualityByOne();
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
        decreaseQualityByOne();
    }

    protected void setQualityToZero() {
        item.quality = 0;
    }
}
