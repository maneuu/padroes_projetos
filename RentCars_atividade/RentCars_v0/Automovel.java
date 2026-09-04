
public class Automovel {

    public static final int BASICO = 0;
    public static final int FAMILIA = 1;
    public static final int LUXO = 2;
    private String descricao;
    private String placa;
    private int ano; // Ano de fabricação do automóvel
    private int codigoDoPreco;

    public Automovel(String descricao, String placa, int ano,
            int codigoDoPreco) {
        this.descricao = descricao;
        this.placa = placa;
        this.ano = ano;
        this.codigoDoPreco = codigoDoPreco;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPlaca() {
        return placa;
    }

    public int getAno() {
        return ano;
    }

    public int getCodigoDoPreco() {
        return codigoDoPreco;
    }

    public void setCodigoDoPreco(int codigoDoPreco) {
        this.codigoDoPreco = codigoDoPreco;
    }
}
