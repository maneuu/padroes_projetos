
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cliente {

    private String nome;
    private List<Locacao> carrosAlugados = new ArrayList<Locacao>();
    

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Locacao> getCarrosAlugados() {
        return carrosAlugados;
    }

    public void adicionaLocacao(Locacao locacao) {
        carrosAlugados.add(locacao);
    }

    public String gerarExtrato(Extrato extrato) {
        return extrato.gerarExtrato(this);
    }

    // Método para calcular o valor total de todas as locações
    public double getValorTotal() {
        double valorTotal = 0.0;
        for (Locacao locacao : carrosAlugados) {
            valorTotal += locacao.valorDeUmaLocacao();
        }
        return valorTotal;
    }

    // Método para calcular o total de pontos de alugador frequente
    public int getPontosTotaisDeAlugadorFrequente() {
        int pontos = 0;
        for (Locacao locacao : carrosAlugados) {
            pontos += locacao.calculaPontos();
        }
        return pontos;
    }

}
