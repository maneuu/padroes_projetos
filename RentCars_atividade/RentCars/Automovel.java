
public class Automovel implements Alugavel {

    public static final int BASICO = 0;
    public static final int FAMILIA = 1;
    public static final int LUXO = 2;

    private String descricao;
    private String placa;
    private int ano;
    private Classificacao classificacao; // Substitui codigoDoPreco

    public Automovel(String descricao, String placa, int ano, int codigoDoPreco) {
        this.descricao = descricao;
        this.placa = placa;
        this.ano = ano;
        setCodigoDoPreco(codigoDoPreco);
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
        return classificacao.getCodigoDoPreco();
    }

    public void setCodigoDoPreco(int codigoDoPreco) {
        // Creator de classificações concretas
        switch (codigoDoPreco) {
            case BASICO:
                this.classificacao = new Basica();
                break;
            case FAMILIA:
                this.classificacao = new Familia();
                break;
            case LUXO:
                this.classificacao = new Luxo();
                break;
            default:
                throw new IllegalArgumentException("Código de preço inválido");
        }
    }

    @Override
    public double getValorDaLocacao(int diasAlugada) {
        return classificacao.getValorDaLocacao(diasAlugada);
    }

    @Override
    public int getPontosDeAlugadorFrequente(int diasAlugada) {
        return classificacao.getPontosDeAlugadorFrequente(diasAlugada);
    }
}
