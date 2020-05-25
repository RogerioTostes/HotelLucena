/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.util.Relatorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;
/**
 *
 * @author Gerim
 */
@Named
@ViewScoped
public class RelatorioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String relatorio;
    
    private Map<String, String> tipos;
    
    private List<Date> range;

    public RelatorioBean() {
        tipos = new HashMap<String, String>();
        tipos.put("Relatório de Quartos Reservados", "Reservado");
        tipos.put("Relatório de Quartos Ocupados", "Ocupado");
        tipos.put("Relatório de Reservas Concluídas", "Concluido");
        
        range = new ArrayList<Date>();
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    public Map<String, String> getTipos() {
        return tipos;
    }   
    
    public void gerarRelatorio() {
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorio(this.relatorio, null, null);
    }
    
    public List<Date> getRange() {
        return range;
    }

    public void setRange(List<Date> range) {
        this.range = range;
    }
    
    public void setTipos(Map<String, String> tipos) {
        this.tipos = tipos;
    }
    
}