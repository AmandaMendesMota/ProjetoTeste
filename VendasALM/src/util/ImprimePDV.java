/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controller.ControllerProdutos;
import controller.ControllerVendas;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;
import model.ModelVendas;
import model.ModelVendasProdutos;

/**
 *
 * @author Amanda, Luiz Felipe e Milena
 */
public class ImprimePDV {

    public void geraCupomTXT(int pCodigo) throws IOException {

        ControllerProdutos controllerProdutos = new ControllerProdutos();
        ControllerVendas controllerVendas = new ControllerVendas();
        ArrayList<ModelVendasProdutos> listaModelVendasProdutoses = new ArrayList<>();
        ModelVendas modelVendas = new ModelVendas();
        modelVendas = controllerVendas.getVendasController(pCodigo);
        BLMascaras bLMascaras = new BLMascaras();

        //data e hora do sistema
        String data = "dd/MM/yyyy";
        String hora = "h:mm - a";
        String data1, hora1;
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        data1 = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        hora1 = formata.format(agora);
        //fim data e hora do sistema

        String conteudoFor = "";
        for (int i = 0; i < listaModelVendasProdutoses.size(); i++) {
            conteudoFor += listaModelVendasProdutoses.get(i).getVenProQuantidade()+ " x " + bLMascaras.arredondamentoDoubleComPontoDuasCasasString(controllerProdutos.retornarProdutoController(listaModelVendasProdutoses.get(i).getProduto()).getProValor()) + " - " + controllerProdutos.retornarProdutoController(listaModelVendasProdutoses.get(i).getProduto()).getProNome()+ "\n\r";
        }

        this.imprimir("MINHA EMPRESA AQUI"+ "\n\r"
                + "RUA: DAS FLORES, 234, CENTRO-UBÁ-MG" + "\n\r"
                + "CE:36500-000, TELEFONE: (32) 3531-0000" + "\n\r"
                + "Impressao:" + data1 + "-" + hora1 + "\n\r"
                + "--------------------------------\n\r"
                + "        CUPOM NAO FISCAL        \n\r"
                + "--------------------------------\n\r"
                + "QT   PRECO   DESCRICAO\n\r"
                + conteudoFor + ""
                + "--------------------------------\n\r"
                + "Valor bruto: " + bLMascaras.arredondamentoComPontoDuasCasasString(modelVendas.getVenValorBruto()) + "\n\r"
                + "   Desconto: " + bLMascaras.arredondamentoComPontoDuasCasasString(modelVendas.getVenDesconto()) + "\n\r"
                + "Valor total: " + bLMascaras.arredondamentoComPontoDuasCasasString(modelVendas.getVenValorLiquido()) + "\n\r"
                + "\n\r\n\r\n\r\n\r\f");

    }

    public void imprimir(String pTexto) {

        // /n/r para novas linhas e /f para fim da pagina  
        try {
            InputStream prin = new ByteArrayInputStream(pTexto.getBytes());
            DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            SimpleDoc documentoTexto = new SimpleDoc(prin, docFlavor, null);
            PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
            // pega a //impressora padrao  
            PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();
            printerAttributes.add(new JobName("Impressao", null));
            printerAttributes.add(OrientationRequested.PORTRAIT);
            printerAttributes.add(MediaSizeName.ISO_A4);
            // informa o tipo de folha  
            DocPrintJob printJob = impressora.createPrintJob();

            try {
                printJob.print(documentoTexto, (PrintRequestAttributeSet) printerAttributes);
                //tenta imprimir  
            } catch (PrintException e) {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar a impressão !!", "Erro", JOptionPane.ERROR_MESSAGE);
                // mostra //mensagem de erro  
            }

            prin.close();

        } catch (Exception e) {

        }

    }

}
