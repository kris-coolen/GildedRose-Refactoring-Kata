package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateInventory();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void givenStandardItemWithPostiveSellin_thenExpectSellinAndQualityDecreasesByOne() {
        Item someItem = new Item("some item", 3, 10);
        GildedRose app = new GildedRose(new Item[]{someItem});
        app.updateInventory();
        assertEquals(2, someItem.sellIn);
        assertEquals(9, someItem.quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -5})
    void givenStandardItemWithZeroOrNegativeSellin_thenExpectQualityDecreasesByTwo(int nonPositiveSellin) {
        Item someItem = new Item("some item", nonPositiveSellin, 10);
        GildedRose app = new GildedRose(new Item[]{someItem});
        app.updateInventory();
        assertEquals(nonPositiveSellin - 1, someItem.sellIn);
        assertEquals(8, someItem.quality);
    }

    @Test
    void givenStandardItemWithZeroQuality_thenExpectQualityNotChanged() {
        Item someItem = new Item("some item", 3, 0);
        GildedRose app = new GildedRose(new Item[]{someItem});
        app.updateInventory();
        assertEquals(2, someItem.sellIn);
        assertEquals(0, someItem.quality);
    }

    @Test
    void givenAgedBrieWithPositiveSellin_whenQualityIs50_thenExpectQualityNotIncreased() {
        Item nonExpiredBrie = new Item(AgedBrie.NAME, 3, 50);
        GildedRose app = new GildedRose(new Item[]{nonExpiredBrie});
        app.updateInventory();
        assertEquals(2, nonExpiredBrie.sellIn);
        assertEquals(50, nonExpiredBrie.quality);
    }

    @Test
    void givenAgedBrieWithNegativeSellin_whenQualityIs50_thenExpectQualityNotIncreased() {
        Item expiredBrie = new Item(AgedBrie.NAME, -1, 50);
        GildedRose app = new GildedRose(new Item[]{expiredBrie});
        app.updateInventory();
        assertEquals(-2, expiredBrie.sellIn);
        assertEquals(50, expiredBrie.quality);
    }

    @Test
    void givenAgedBrieWithPositive_whenQualityIsLessThan50_thenExpectQualityIncreasedByOne() {
        Item nonExpiredBrie = new Item(AgedBrie.NAME, 3, 20);
        GildedRose app = new GildedRose(new Item[]{nonExpiredBrie});
        app.updateInventory();
        assertEquals(2, nonExpiredBrie.sellIn);
        assertEquals(21, nonExpiredBrie.quality);
    }
    @ParameterizedTest
    @ValueSource(ints = {0, -5})
    void givenAgedBrieWithZeroOrNegativeSellin_whenQualityIsLessThan50_thenExpectQualityIncreasedByTwo(int nonPositiveSellin) {
        Item expiredBrie = new Item(AgedBrie.NAME, nonPositiveSellin, 20);
        GildedRose app = new GildedRose(new Item[]{expiredBrie});
        app.updateInventory();
        assertEquals(nonPositiveSellin-1, expiredBrie.sellIn);
        assertEquals(22, expiredBrie.quality);
    }
    @ParameterizedTest
    @ValueSource(ints = {0, -5})
    void givenAgedBrieWithZeroOrNegativeSEllin_whenQualityIsAlready49_thenExpectQuality50AfterUpdate(int nonPositiveSellin) {
        Item expiredBrie = new Item(AgedBrie.NAME, nonPositiveSellin, 49);
        GildedRose app = new GildedRose(new Item[]{expiredBrie});
        app.updateInventory();
        assertEquals(nonPositiveSellin-1, expiredBrie.sellIn);
        assertEquals(50, expiredBrie.quality);
    }

    @Test
    void givenSulfuras_thenQualityAndSellInNeverChanges() {
        Item sulfuras = new Item(Sulfuras.NAME, 5, 40);
        GildedRose app = new GildedRose(new Item[]{sulfuras});
        app.updateInventory();
        app.updateInventory();
        app.updateInventory();
        assertEquals(5, sulfuras.sellIn);
        assertEquals(40, sulfuras.quality);

    }

    @Test
    void givenBackStagePasses_whenSellinMoreThen10_thenQualityIncreasesByOne() {
        Item backStagePasses = new Item(BackStagePasses.NAME, 11, 20);
        GildedRose app = new GildedRose(new Item[]{backStagePasses});
        app.updateInventory();
        assertEquals(21, backStagePasses.quality);
    }

    @Test
    void givenBackStagePasses_whenSellinMoreThen10AndQualityAlready50_thenQualityRemains50() {
        Item backStagePasses = new Item(BackStagePasses.NAME, 11, 50);
        GildedRose app = new GildedRose(new Item[]{backStagePasses});
        app.updateInventory();
        assertEquals(50, backStagePasses.quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {10,9,8,7,6})
    void givenBackStagePasses_whenSellinisBetween10And6_thenQualityIncreasesByTwo(int sellIn) {
        Item backStagePasses = new Item(BackStagePasses.NAME, sellIn, 20);
        GildedRose app = new GildedRose(new Item[]{backStagePasses});
        app.updateInventory();
        assertEquals(22, backStagePasses.quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {5,4,3,2,1})
    void givenBackStagePasses_whenSellinisBetween5and1_thenQualityIncreasesByThree(int sellIn) {
        Item backStagePasses = new Item(BackStagePasses.NAME, sellIn, 20);
        GildedRose app = new GildedRose(new Item[]{backStagePasses});
        app.updateInventory();
        assertEquals(23, backStagePasses.quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-5})
    void givendBackStagePasses_whenSellinIsZeroOrNegative_thenQualityDropsToZero(int sellIn) {
        Item backStagePasses = new Item(BackStagePasses.NAME, sellIn, 20);
        GildedRose app = new GildedRose(new Item[]{backStagePasses});
        app.updateInventory();
        assertEquals(0, backStagePasses.quality);
    }

}
