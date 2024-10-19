package seb.project.model;

public class ConsumoEnergetico {
    private int id;
    private int cliente_id;
    private String concessao;
    private String grupo;
    private float consumo_mensal;
    private float valor_kwh;
    
    public ConsumoEnergetico(int id, int cliente_id, String concessao, String grupo, float consumoMensal, float valor_kwh) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.concessao = concessao;
        this.grupo = grupo;
        this.consumo_mensal = consumoMensal;
        this.valor_kwh = valor_kwh;
    }
    
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

    public String getConcessao() {
        return concessao;
    }

    public void setConcessao(String concessao) {
        this.concessao = concessao;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public double getConsumoMensal() {
        return consumo_mensal;
    }

    public void setConsumoMensal(float consumo_mensal) {
        this.consumo_mensal = consumo_mensal;
    }

    public double getValor_kwh() {
        return valor_kwh;
    }

    public void setValor_kwh(float valor_kwh) {
        this.valor_kwh = valor_kwh;
    }
}
