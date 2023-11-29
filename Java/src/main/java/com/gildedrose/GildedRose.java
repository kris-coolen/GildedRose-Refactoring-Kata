package com.gildedrose;

class GildedRose {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item item : items) {
            updateInventory(item);
        }
    }

    private static void updateInventory(Item item) {
        updateQuality(item);
        decreaseSellInByOneIfNotSulfuras(item);
        if (isOverdue(item)) {
            handleOverDue(item);
        }
    }

    private static void updateQuality(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            increaseQualityByOne(item);
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
            increaseQualityByOne(item);
            if (item.sellIn < 11) {
                increaseQualityByOne(item);
            }
            if (item.sellIn < 6) {
                increaseQualityByOne(item);
            }

        } else {
            decreaseQualityByOneIfNotSulfuras(item);
        }
    }

    private static void decreaseQualityByOneIfNotSulfuras(Item item) {
        if (item.quality > 0) {
            if (item.name.equals(SULFURAS)) {
                return;
            }
            item.quality--;
        }
    }

    private static void increaseQualityByOne(Item item) {
        if (item.quality < 50) {
            item.quality++;

        }
    }

    private static void decreaseSellInByOneIfNotSulfuras(Item item) {
        if (item.name.equals(SULFURAS)) {
            return;
        }
        item.sellIn--;
    }

    private static void handleOverDue(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            increaseQualityByOne(item);
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
            setQualityToZero(item);
        } else {
            decreaseQualityByOneIfNotSulfuras(item);
        }
    }

    private static void setQualityToZero(Item item) {
        item.quality = 0;
    }

    private static boolean isOverdue(Item item) {
        return item.sellIn < 0;
    }
}
