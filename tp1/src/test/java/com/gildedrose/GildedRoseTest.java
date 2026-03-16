package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * GildedRoseTest – Exercício 4
 *
 * Testes automatizados com JUnit 5 para garantir que a refatoração
 * não alterou os comportamentos esperados do sistema.
 *
 * Padrão: Arrange → Act → Assert
 */
public class GildedRoseTest {

    // ── Teste 1: Aged Brie aumenta qualidade até o máximo de 50 ─────────────
    @Test
    public void agedBrie_shouldIncreaseQualityUpTo50() {
        Item[] items = { new Item("Aged Brie", 5, 49) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(50, items[0].quality, "Qualidade deve ser 50 apos 1 dia");

        app.updateQuality(); // tenta ultrapassar 50
        assertEquals(50, items[0].quality, "Qualidade nao deve ultrapassar 50");
    }

    // ── Teste 2: Sulfuras nunca tem qualidade ou sellIn alterados ────────────
    @Test
    public void sulfuras_shouldNeverChange() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(80, items[0].quality, "Qualidade do Sulfuras nao deve mudar");
        assertEquals(10, items[0].sellIn,  "SellIn do Sulfuras nao deve mudar");
    }

    // ── Teste 3: Backstage Pass zera qualidade após a data do show ───────────
    @Test
    public void backstagePass_shouldDropQualityToZeroAfterShow() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 30) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(0, items[0].quality,
            "Qualidade do Backstage Pass deve ser 0 apos o show");
    }

    // ── Teste 4: Conjured perde qualidade 2x mais rápido que item normal ─────
    @Test
    public void conjured_shouldDegradeQualityTwiceAsFast() {
        Item[] items = { new Item("Conjured Mana Cake", 5, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(18, items[0].quality,
            "Conjured deve perder 2 de qualidade por dia");
    }

    // ── Teste 5: Conjured degrada 4 por dia após a data de venda ────────────
    @Test
    public void conjured_shouldDegradeFourAfterSellDate() {
        Item[] items = { new Item("Conjured Mana Cake", 0, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(16, items[0].quality,
            "Conjured deve perder 4 de qualidade apos data de venda");
    }

    // ── Teste 6: Qualidade nunca deve ser negativa ───────────────────────────
    @Test
    public void quality_shouldNeverGoBelowZero() {
        Item[] items = { new Item("Conjured Mana Cake", 0, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(0, items[0].quality,
            "Qualidade nunca deve ser negativa");
    }

    // ── Teste 7: Backstage Pass aumenta +2 quando sellIn <= 10 ──────────────
    @Test
    public void backstagePass_shouldIncreaseByTwoWhenTenDaysOrLess() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(22, items[0].quality,
            "Backstage Pass deve ganhar +2 quando sellIn <= 10");
    }

    // ── Teste 8: Backstage Pass aumenta +3 quando sellIn <= 5 ───────────────
    @Test
    public void backstagePass_shouldIncreaseByThreeWhenFiveDaysOrLess() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(23, items[0].quality,
            "Backstage Pass deve ganhar +3 quando sellIn <= 5");
    }

    // ── Teste 9: Item normal degrada 2x mais rápido após data de venda ───────
    @Test
    public void normalItem_shouldDegradeTwiceAsFastAfterSellDate() {
        Item[] items = { new Item("Elixir of the Mongoose", 0, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(8, items[0].quality,
            "Item normal deve perder 2 de qualidade apos data de venda");
    }
}
