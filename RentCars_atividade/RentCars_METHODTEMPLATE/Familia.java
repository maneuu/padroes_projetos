
public class Familia extends Classificacao {

    @Override
    public int getCodigoDoPreco() {
        return Automovel.FAMILIA;
    }

    @Override
    public double getValorDaLocacao(int diasAlugado) {
        return diasAlugado * 130.00;
    }
}
