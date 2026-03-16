package com.gildedrose;

/**
 * ConjuredItemUpdater – Exercício 3
 *
 * Regras do "Conjured Mana Cake":
 *  - Qualidade cai 2 por dia (mínimo 0).
 *  - Após a data de venda (sellIn < 0), qualidade cai 4 por dia.
 *
 * Implementado SEM alterar nenhuma classe existente, respeitando o
 * Princípio Aberto-Fechado (OCP).
 */
public class ConjuredItemUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        degrade(item, 2);       // perde 2 antes da data de venda
        item.sellIn--;
        if (item.sellIn < 0) {
            degrade(item, 2);   // perde mais 2 após a data (total 4/dia)
        }
    }

    private void degrade(Item item, int amount) {
        item.quality = Math.max(0, item.quality - amount);
    }
}
