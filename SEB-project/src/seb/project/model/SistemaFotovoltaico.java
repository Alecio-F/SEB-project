package seb.project.model;

public class SistemaFotovoltaico {
    private int id;
    private int cliente_id; // ID do cliente associado
    private String localidade;
    private String tipo_estrutura;
    private float potencia_sistema; // Potência do sistema em kW
    private float irradiacao_diaria; // Irradiação diária em kWh/m²
    private float inclinacao_paineis; // Inclinação dos painéis em graus
    private float area_util; // Área útil em m²
    private float percentual_perda; // Percentual de perda de energia

    // Construtor
    public SistemaFotovoltaico(int id, int cliente_id, String localidade, String tipo_estrutura,
                               float potencia_sistema, float irradiacao_diaria, 
                               float inclinacao_paineis, float area_util, 
                               float percentual_perda) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.localidade = localidade;
        this.tipo_estrutura = tipo_estrutura;
        this.potencia_sistema = potencia_sistema;
        this.irradiacao_diaria = irradiacao_diaria;
        this.inclinacao_paineis = inclinacao_paineis;
        this.area_util = area_util;
        this.percentual_perda = percentual_perda;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getTipo_estrutura() {
        return tipo_estrutura;
    }

    public void setTipo_estrutura(String tipo_estrutura) {
        this.tipo_estrutura = tipo_estrutura;
    }

    public float getPotencia_sistema() {
        return potencia_sistema;
    }

    public void setPotencia_sistema(float potencia_sistema) {
        this.potencia_sistema = potencia_sistema;
    }

    public float getIrradiacao_diaria() {
        return irradiacao_diaria;
    }

    public void setIrradiacao_diaria(float irradiacao_diaria) {
        this.irradiacao_diaria = irradiacao_diaria;
    }

    public float getInclinacao_paineis() {
        return inclinacao_paineis;
    }

    public void setInclinacao_paineis(float inclinacao_paineis) {
        this.inclinacao_paineis = inclinacao_paineis;
    }

    public float getArea_util() {
        return area_util;
    }

    public void setArea_util(float area_util) {
        this.area_util = area_util;
    }

    public float getPercentual_perda() {
        return percentual_perda;
    }

    public void setPercentual_perda(float percentual_perda) {
        this.percentual_perda = percentual_perda;
    }
}
