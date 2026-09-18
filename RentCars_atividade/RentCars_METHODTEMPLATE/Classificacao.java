
public abstract class Classificacao {

    public abstract int getCodigoDoPreco();

    public abstract double getValorDaLocacao(int diasAlugado);

    public int getPontosDeAlugadorFrequente(int diasAlugado) {
        return 1; // Padrão para BASICO e FAMILIA
    }
}
