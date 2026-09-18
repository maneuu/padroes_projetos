
public interface Alugavel {

    String getDescricao();

    int getAno();

    double getValorDaLocacao(int diasAlugada);

    int getPontosDeAlugadorFrequente(int diasAlugada);
}
