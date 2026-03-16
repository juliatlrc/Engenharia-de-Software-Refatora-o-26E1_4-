package com.gildedrose;

/**
 * Interface ItemUpdater
 *
 * Contrato para atualização de qualidade de um item.
 *
 * Segue o Princípio da Responsabilidade Única (SRP): cada implementação
 * cuida exclusivamente das regras de negócio de UM tipo de item.
 *
 * Segue o Princípio Aberto-Fechado (OCP): novos tipos de item são
 * adicionados criando novas implementações, sem alterar código existente.
 */
public interface ItemUpdater {

    /**
     * Atualiza a qualidade e o sellIn do item conforme suas regras específicas.
     *
     * @param item o item a ser atualizado (modificado in-place)
     */
    void update(Item item);
}
