
public abstract class Extrato {

    // Template Method
    public String gerarExtrato(Cliente cliente) {
        String resultado = "";

        resultado += cabecalho(cliente);

        int sequencia = 0;

        for (Locacao locacao : cliente.getCarrosAlugados()) {
            sequencia++;
            resultado += mostrarLocacao(locacao, sequencia);
        }

        resultado += rodape(cliente);

        resultado += finalizar();

        return resultado;
    }

    protected abstract String cabecalho(Cliente cliente);

    protected abstract String mostrarLocacao(
            Locacao locacao, int sequencia);

    protected abstract String rodape(Cliente cliente);

    // Hook
    protected String finalizar() {
        return "";
    }
}