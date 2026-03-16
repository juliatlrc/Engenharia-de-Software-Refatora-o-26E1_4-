package com.gildedrose;

/**
 * AgedBrieUpdater – Exercício 2
 *
 * Regras do "Aged Brie":
 *  - Qualidade aumenta 1 por dia (máximo 50).
 *  - Após a data de venda (sellIn < 0), qualidade aumenta 2 por dia.
 */
public class AgedBrieUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        if (item.quality < 50) item.quality++;
        item.sellIn--;
        if (item.sellIn < 0 && item.quality < 50) item.quality++;
    }
}
