
public class Locacao {

    private Alugavel item;
    private int diasAlugado;

    public Locacao(Alugavel item, int diasAlugado) {
        this.item = item;
        this.diasAlugado = diasAlugado;
    }

    public Alugavel getItem() {
        return item;
    }

    public int getDiasAlugado() {
        return diasAlugado;
    }

    // Calcula o valor de uma única locação
    public double valorDeUmaLocacao() {
        return item.getValorDaLocacao(diasAlugado);
    }

    // Calcula os pontos de alugador frequente desta locação
    public int calculaPontos() {
        return item.getPontosDeAlugadorFrequente(diasAlugado);
    }
}
