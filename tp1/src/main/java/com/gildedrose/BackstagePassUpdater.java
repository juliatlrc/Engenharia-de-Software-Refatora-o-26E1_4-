package com.gildedrose;

/**
 * BackstagePassUpdater – Exercício 2
 *
 * Regras do "Backstage passes to a TAFKAL80ETC concert":
 *  - Qualidade +1 quando sellIn > 10.
 *  - Qualidade +2 quando sellIn <= 10.
 *  - Qualidade +3 quando sellIn <= 5.
 *  - Qualidade máxima: 50.
 *  - Após o show (sellIn < 0), qualidade cai para 0.
 */
public class BackstagePassUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        if (item.quality < 50) {
            item.quality++;
            if (item.sellIn < 11 && item.quality < 50) item.quality++;
            if (item.sellIn < 6  && item.quality < 50) item.quality++;
        }
        item.sellIn--;
        if (item.sellIn < 0) item.quality = 0;
    }
}
