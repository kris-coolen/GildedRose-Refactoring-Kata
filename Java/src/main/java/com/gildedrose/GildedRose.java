package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item item : items) {
            GenericItem genericItem = GenericItem.create(item);
            genericItem.updateInventory();
        }
    }

}
