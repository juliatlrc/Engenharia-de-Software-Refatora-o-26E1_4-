package com.gildedrose;

/**
 * SulfurasUpdater – Exercício 2
 *
 * Regras do "Sulfuras, Hand of Ragnaros":
 *  - Item lendário: qualidade e sellIn NUNCA são alterados.
 *  - Qualidade fixa em 80 (não entra nesta lógica, apenas não é tocada).
 *
 * A implementação vazia é semanticamente correta e não viola o LSP:
 * o contrato da interface não exige que o item mude, apenas que seja
 * "processado" conforme suas regras — que neste caso são de imutabilidade.
 */
public class SulfurasUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        // Sulfuras é um item lendário e imutável – nenhuma operação necessária.
    }
}
