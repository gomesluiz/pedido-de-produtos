package br.pucpcaldas.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class TestaProduto {

    @Test
    void testeConstrutor_DeveCriarUmProdutoValido() {
        Produto caneta = new Produto(1, "caneta", 1.50);
        
        assertEquals(1, caneta.getCodigo());
        assertEquals("caneta", caneta.getNome());
        assertEquals(1.50, caneta.getPreco());
    }
}
