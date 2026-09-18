public class ExtratoHTML extends Extrato {

    @Override
    protected String cabecalho(Cliente cliente) {
        String fimDeLinha = System.getProperty("line.separator");

        String resultado = "<html><body>" + fimDeLinha;

        resultado += String.format(
                "<H2>Registro de Locacoes de <EM> %s </EM></H2>",
                cliente.getNome()
        ) + fimDeLinha;

        resultado += "<table border=\"1\"><tr>"
                + "<th>Seq</th>"
                + "<th>Automóvel</th>"
                + "<th>Ano</th>"
                + "<th>Diárias</th>"
                + "<th>Valor</th>"
                + "</tr>" + fimDeLinha;

        return resultado;
    }

    @Override
    protected String mostrarLocacao(Locacao locacao, int sequencia) {
        String fimDeLinha = System.getProperty("line.separator");

        return String.format(
                "<tr><th>%02d.</th><th>%s</th><th>%4d</th>"
                + "<th>%2d</th><th>R$ %8.2f</th></tr>"
                + fimDeLinha,
                sequencia,
                locacao.getItem().getDescricao(),
                locacao.getItem().getAno(),
                locacao.getDiasAlugado(),
                locacao.valorDeUmaLocacao()
        );
    }

    @Override
    protected String rodape(Cliente cliente) {
        String fimDeLinha = System.getProperty("line.separator");

        return String.format(
                "<tfoot><tr><td colspan=\"4\">"
                + "Valor Acumulado em diárias:"
                + "</td><td><EM>R$ %8.2f</EM></td></tr>"
                + "</tfoot></table>"
                + fimDeLinha,
                cliente.getValorTotal()
        );
    }

    @Override
    protected String finalizar() {
        return "<P>Extrato finalizado.</P></body></html>";
    }
}