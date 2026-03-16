package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

/**
 * ═══════════════════════════════════════════════════════════════════════════
 *  GildedRose – Versão Final Refatorada
 *  Gilded Rose Refactoring Kata – DR4 TP1
 * ═══════════════════════════════════════════════════════════════════════════
 *
 *  AVALIAÇÃO FINAL DE DESIGN (Exercício 5)
 *
 *  1. A estrutura facilita adicionar novos tipos de item? (OCP)
 *  ─────────────────────────────────────────────────────────
 *  SIM. A estrutura baseada em ItemUpdater + Map está ABERTA para extensão
 *  e FECHADA para modificação.
 *
 *  Para adicionar um novo tipo (ex: "Elixir of the Mongoose"):
 *    a) Cria-se uma nova classe que implementa ItemUpdater.
 *    b) Registra-se no mapa dentro do construtor:
 *         updaters.put("Elixir of the Mongoose", new ElixirUpdater());
 *    c) Nenhuma classe existente é modificada.
 *
 *  Isso é exatamente o que o OCP prescreve: o comportamento do sistema
 *  é estendido sem alterar código já testado e estável.
 *
 *  2. Os ItemUpdater respeitam o SRP?
 *  ─────────────────────────────────────────────────────────
 *  SIM. Cada implementação de ItemUpdater encapsula exclusivamente as
 *  regras de negócio de UM tipo de item:
 *
 *   - AgedBrieUpdater      → somente regras do Aged Brie
 *   - SulfurasUpdater      → somente regras do Sulfuras
 *   - BackstagePassUpdater → somente regras do Backstage Pass
 *   - DefaultUpdater       → somente regras de itens normais
 *   - ConjuredItemUpdater  → somente regras de itens Conjurados
 *
 *  GildedRose também respeita o SRP: sua única responsabilidade é
 *  ORQUESTRAR a atualização, delegando toda lógica aos updaters.
 *  Mudanças nas regras de um item afetam apenas 1 classe.
 *
 *  3. Alguma violação do LSP pode ser identificada?
 *  ─────────────────────────────────────────────────────────
 *  NÃO há violação de LSP na hierarquia proposta. Todas as implementações:
 *
 *   a) Respeitam o contrato da interface: recebem um Item e produzem
 *      efeito sobre ele (ou não, como Sulfuras).
 *   b) Podem ser substituídas umas pelas outras sem que o código cliente
 *      (GildedRose) quebre ou exija tratamento especial.
 *   c) Não lançam exceções inesperadas nem alteram pré-condições.
 *
 *  Atenção: SulfurasUpdater é uma implementação vazia (no-op), o que é
 *  semanticamente correto – o contrato não exige que o item MUDE, apenas
 *  que seja "atualizado" conforme suas regras. Isso NÃO viola o LSP.
 *
 *  Uma violação ocorreria se SulfurasUpdater lançasse
 *  UnsupportedOperationException, forçando o cliente a tratá-la
 *  especialmente. Isso não acontece aqui.
 * ═══════════════════════════════════════════════════════════════════════════
 */
public class GildedRose {

    Item[] items;

    private final Map<String, ItemUpdater> updaters;
    private final ItemUpdater defaultUpdater = new DefaultUpdater();

    public GildedRose(Item[] items) {
        this.items = items;

        updaters = new HashMap<>();
        updaters.put("Aged Brie",                                    new AgedBrieUpdater());
        updaters.put("Sulfuras, Hand of Ragnaros",                   new SulfurasUpdater());
        updaters.put("Backstage passes to a TAFKAL80ETC concert",    new BackstagePassUpdater());
        updaters.put("Conjured Mana Cake",                           new ConjuredItemUpdater());
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = updaters.getOrDefault(item.name, defaultUpdater);
            updater.update(item);
        }
    }
}
