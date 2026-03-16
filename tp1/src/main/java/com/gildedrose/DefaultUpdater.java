package com.gildedrose;

/**
 * DefaultUpdater – Exercício 2
 *
 * Regras de itens normais (padrão):
 *  - Qualidade -1 por dia (mínimo 0).
 *  - Após a data de venda (sellIn < 0), qualidade -2 por dia.
 */
public class DefaultUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        if (item.quality > 0) item.quality--;
        item.sellIn--;
        if (item.sellIn < 0 && item.quality > 0) item.quality--;
    }
}
