
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
        int sequencia = 0;

        String resultado = "Registro de Alugueis de " + getNome()
                + fimDeLinha;

        resultado += String.format(
                "Seq Automovel Ano da Locacao Valor Pago" + fimDeLinha);

        resultado += String.format(
                "=== ==================== ================= ===========" + fimDeLinha);

        for (Locacao locacao : carrosAlugados) {
            sequencia++;
            resultado += String.format(
                    "%02d. %-20s %4d R$ %8.2f" + fimDeLinha,
                    sequencia,
                    locacao.getItem().getDescricao(),
                    locacao.getItem().getAno(),
                    locacao.valorDeUmaLocacao());
        }

        // Adiciona rodapé
        resultado += "============================================"
                + fimDeLinha;

        resultado += String.format(
                "Valor Acumulado em diárias............: R$ % 8.2f" + fimDeLinha,
                getValorTotal());

        resultado += "Voce acumulou " + getPontosTotaisDeAlugadorFrequente()
                + " pontos de alugador frequente";

        return resultado;
    }

    public String extratoHTML() {
        final String fimDeLinha = System.getProperty("line.separator");
        int sequencia = 0;
        Iterator<Locacao> locacoes = carrosAlugados.iterator();

        String resultado = "<html><body>" + fimDeLinha;

        resultado = String.format(
                "<H2>Registro de Locacoes de <EM> %s </EM></H2>",
                getNome()
        ) + fimDeLinha;

        resultado += "<table border=\"1\"><tr>"
                + "<th>Seq</th>"
                + "<th>Automóvel</th>"
                + "<th>Ano</th>"
                + "<th>Diárias</th>"
                + "<th>Valor</th>"
                + "</tr>" + fimDeLinha;

        while (locacoes.hasNext()) {
            Locacao cada = locacoes.next();

            // mostra valores para este Locacao
            sequencia++;

            resultado += String.format(
                    "<tr><th>%02d.</th><th>%s</th><th>%4d</th><th>%2d</th>"
                    + "<th>R$ %8.2f</th></tr>" + fimDeLinha,
                    sequencia,
                    cada.getItem().getDescricao(),
                    cada.getItem().getAno(),
                    cada.getDiasAlugado(),
                    cada.valorDeUmaLocacao()
            );
        } // while

        // adiciona rodapé
        resultado += String.format(
                "<tfoot><tr><td colspan=\"4\">"
                + "Valor Acumulado em diárias:"
                + "</td><td><EM>R$ %8.2f</EM></td></tr></tfoot></table>"
                + fimDeLinha,
                getValorTotal()
        );

        resultado += "<P>Voce acumulou <EM>"
                + getPontosTotaisDeAlugadorFrequente()
                + " pontos </EM> de alugador frequente</p></body></html>";

        return resultado;
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
