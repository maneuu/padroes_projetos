public class ExtratoTexto extends Extrato {

    @Override
    protected String cabecalho(Cliente cliente) {
        String fimDeLinha = System.getProperty("line.separator");

        String resultado = "Registro de Alugueis de "
                + cliente.getNome() + fimDeLinha;

        resultado += "Seq Automovel Ano da Locacao Valor Pago"
                + fimDeLinha;

        resultado += "=== ==================== ================= ==========="
                + fimDeLinha;

        return resultado;
    }

    @Override
    protected String mostrarLocacao(Locacao locacao, int sequencia) {
        String fimDeLinha = System.getProperty("line.separator");

        return String.format(
                "%02d. %-20s %4d R$ %8.2f" + fimDeLinha,
                sequencia,
                locacao.getItem().getDescricao(),
                locacao.getItem().getAno(),
                locacao.valorDeUmaLocacao()
        );
    }

    @Override
    protected String rodape(Cliente cliente) {
        String fimDeLinha = System.getProperty("line.separator");

        String resultado =
                "============================================"
                + fimDeLinha;

        resultado += String.format(
                "Valor Acumulado em diárias............: R$ %8.2f"
                + fimDeLinha,
                cliente.getValorTotal()
        );

        resultado += "Voce acumulou "
                + cliente.getPontosTotaisDeAlugadorFrequente()
                + " pontos de alugador frequente";

        return resultado;
    }
}