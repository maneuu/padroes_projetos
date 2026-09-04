
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

    public void adicionaLocacao(Locacao locacao) {
        carrosAlugados.add(locacao);
    }

    public String extrato() {
        final String fimDeLinha = System.getProperty("line.separator");
        double valorTotal = 0.0;
        int pontosDeAlugadorFrequente = 0;
        int sequencia = 0;
        Iterator<Locacao> locacoes = carrosAlugados.iterator();
        String resultado = "Registro de Alugueis de " + getNome()
                + fimDeLinha;
        resultado += String.format(
                "Seq Automovel Ano da Locacao Valor Pago" + fimDeLinha);
        resultado += String.format(
                "=== ==================== ================= ===========" + fimDeLinha);
        while (locacoes.hasNext()) {
            double valorCorrente = 0.0;
            Locacao cada = locacoes.next();
            // Determina valores para cada linha.
            switch (cada.getCarro().getCodigoDoPreco()) {
                case Automovel.BASICO: // R$ 90.00 por dia
                    valorCorrente += cada.getDiasAlugado() * 90.00;

                    break;

                case Automovel.FAMILIA: // R$ 130.00 por dia
                    valorCorrente += cada.getDiasAlugado() * 130.00;

                    break;

                case Automovel.LUXO: // R$ 200.00 por dia.
                    valorCorrente += cada.getDiasAlugado() * 200.00;
                    // Acima de 4 diárias tem 10% de desconto.
                    if (cada.getDiasAlugado() > 4) {
                        valorCorrente *= 0.9;
                    }
                    break;
            } //switch
            // Trata de pontos de alugador frequente.
            pontosDeAlugadorFrequente++;
            // Adiciona bônus para aluguel de um lançamento por pelo menos 2 dias.
            if (cada.getCarro().getCodigoDoPreco() == Automovel.LUXO
                    && cada.getDiasAlugado() > 2) {
                pontosDeAlugadorFrequente += 2;

            }
            // Mostra valores para este aluguel.
            sequencia++;
            resultado += String.format(
                    "%02d. %-20s %4d R$ %8.2f" + fimDeLinha,
                    sequencia,
                    cada.getCarro().getDescricao(),
                    cada.getCarro().getAno(),
                    valorCorrente);
            valorTotal += valorCorrente;
        } // while
        // Adiciona rodapé.
        resultado += "============================================"
                + fimDeLinha;
        resultado += String.format(
                "Valor Acumulado em diárias............: R$ % 8.2f" + fimDeLinha,
                valorTotal);
        resultado += "Voce acumulou " + pontosDeAlugadorFrequente
                + " pontos de alugador frequente";
        return resultado;
    }
}
