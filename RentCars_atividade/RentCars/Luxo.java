
public class Luxo extends Classificacao {

    @Override
    public int getCodigoDoPreco() {
        return Automovel.LUXO;
    }

    @Override
    public double getValorDaLocacao(int diasAlugado) {
        double valor = diasAlugado * 200.00;
        if (diasAlugado > 4) {
            valor *= 0.9; // 10% de desconto
        }
        return valor;
    }

    @Override
    public int getPontosDeAlugadorFrequente(int diasAlugado) {
        int pontos = 1;
        if (diasAlugado > 2) {
            pontos += 2;
        }
        return pontos;
    }
}
