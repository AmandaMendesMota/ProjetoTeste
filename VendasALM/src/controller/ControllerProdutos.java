/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import DAO.DaoProdutos;
import java.util.ArrayList;
import model.ModelProdutos;

/**
 *
 * @author Amanda, Luiz Felipe e Milena
 */
public class ControllerProdutos {
    
    private DaoProdutos daoProdutos = new DaoProdutos();
    
    
    /**
     * Salvar produtos controller
     * @param pModelProdutos
     * @return int
     */
    public int salvarProdutoController(ModelProdutos pModelProdutos){
        return this.daoProdutos.salvarProdutosDAO(pModelProdutos);  
    }
    
    /**
     * excluir um produto pelo código
     * @param pCodigo
     * @return boolean
     */
    public boolean excluirProdutoController(int pCodigo){
        return this.daoProdutos.excluirProdutoDAO(pCodigo);
    }
    
    /**
     * alterar um produto
     * @param pModelProdutos
     * @return boolean
     */
    public boolean alterarProdutoController(ModelProdutos pModelProdutos){
        return this.daoProdutos.alterarProdutoDAO(pModelProdutos);
    }
    
    /**
     * retornar produto pelo código
     * @param pCodigo
     * @return model produto
     */
    public ModelProdutos retornarProdutoController(int pCodigo){
        return this.daoProdutos.retornarProdutoDAO(pCodigo);
    }
    
    /**
     * retornar produto pelo código
     * @param pNomeProduto
     * @return model produto
     */
    public ModelProdutos retornarProdutoController(String pNomeProduto){
        return this.daoProdutos.retornarProdutoDAO(pNomeProduto);
    }
    
    /**
     * retornar uma lista de produtos
     * @return lista Model produtos
     */
    public ArrayList<ModelProdutos> retornarListaProdutoController(){
        return this.daoProdutos.retornarListaProdutosDAO();
    }

    /**
     * Alterar lista de produtos no banco
     * @param pListaModelProdutoses
     * @return 
     */
    public boolean alterarEstoqueProdutoController(ArrayList<ModelProdutos> pListaModelProdutoses) {
         return this.daoProdutos.alterarEstoqueProdutosDAO(pListaModelProdutoses);
    }

    
}
