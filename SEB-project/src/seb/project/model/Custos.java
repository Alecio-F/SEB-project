package seb.project.model;

public class Custos {
    private int id;
    private int cliente_id; // Mudamos para cliente_id
    private float custo_materiais; // Mudamos para float
    private float custo_mao_obra; // Mudamos para float
    private float total_custo; // Mudamos para float

    // Construtor
    public Custos(int id, int cliente_id, float custo_materiais, float custo_mao_obra, float total_custo) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.custo_materiais = custo_materiais;
        this.custo_mao_obra = custo_mao_obra;
        this.total_custo = total_custo;
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

    public float getCusto_materiais() {
        return custo_materiais;
    }

    public void setCusto_materiais(float custo_materiais) {
        this.custo_materiais = custo_materiais;
    }

    public float getCusto_mao_obra() {
        return custo_mao_obra;
    }

    public void setCusto_mao_obra(float custo_mao_obra) {
        this.custo_mao_obra = custo_mao_obra;
    }

    public float getTotal_custo() {
        return total_custo;
    }

    public void setTotal_custo(float total_custo) {
        this.total_custo = total_custo;
    }
}
