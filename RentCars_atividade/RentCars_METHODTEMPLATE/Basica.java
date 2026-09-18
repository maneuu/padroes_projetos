
public class Basica extends Classificacao {

    @Override
    public int getCodigoDoPreco() {
        return Automovel.BASICO;
    }

    @Override
    public double getValorDaLocacao(int diasAlugado) {
        return diasAlugado * 90.00;
    }
}
