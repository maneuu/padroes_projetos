
public class Locadora {

    public static void main(String[] args) {
        Cliente c1 = new Cliente("Alex Sandro");
        c1.adicionaLocacao(new Locacao(new Automovel("Toiyota Corolla xEi",
                "PLN0525", 2021, Automovel.LUXO), 10));
        c1.adicionaLocacao(new Locacao(new Automovel("Fiat Mobi",
                "JPA2464", 2021, Automovel.BASICO), 2));
        c1.adicionaLocacao(new Locacao(new Automovel("BMW Série 7",
                "UBA0808", 2022, Automovel.LUXO), 30));
        c1.adicionaLocacao(new Locacao(new Automovel("Fiat Siena",
                "ABC0001", 2023, Automovel.FAMILIA), 4));
        c1.adicionaLocacao(new Locacao(new Automovel("Honda HRV",
                "KJD9745", 2024, Automovel.FAMILIA), 10));
        c1.adicionaLocacao(new Locacao(new Automovel("Volkswagen Gol",
                "JJJ0055", 2024, Automovel.BASICO), 3));
        System.out.println(c1.extrato());
    }
}
