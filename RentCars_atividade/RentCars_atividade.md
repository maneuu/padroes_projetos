# Resumo do Exercício de Refatoração - Sistema de Locadora de Veículos

## Visão Geral
Este exercício consistiu em refatorar um sistema simples de locadora de veículos, aplicando princípios de design de software (GRASP) e boas práticas de programação orientada a objetos. O código inicial apresentava diversos problemas de estrutura e manutenibilidade, que foram gradualmente corrigidos através de uma série de refatorações controladas.

---

## Etapa 1: Análise do Código Original

### Problemas Identificados
- **Método `extrato()` inchado**: Realizava múltiplas responsabilidades (cálculo de valores, cálculo de pontos, formatação de saída).
- **Baixa coesão**: A classe `Cliente` assumia responsabilidades que pertenciam a outras classes.
- **Alto acoplamento**: A lógica de cálculo de preço e pontos estava fortemente acoplada à classe `Cliente`.
- **Dificuldade de extensão**: Adicionar novos tipos de veículos ou alterar regras de precificação exigiria modificar o método `extrato()`.
- **Código duplicado**: Não havia reuso dos cálculos para outros formatos de saída (ex.: HTML).
- **Variáveis temporárias desnecessárias**: Aumentavam a complexidade do método.

### Objetivos da Refatoração
- Melhorar a estrutura interna do código sem alterar seu comportamento externo.
- Aplicar princípios GRASP: *Expert Information*, *Baixo Acoplamento*, *Alta Coesão* e *Creator*.
- Preparar o sistema para futuras extensões (novos tipos de veículos, novas regras de preço).

---

## Etapa 2: Refatoração do Método `extrato()` (Parte 1)

### Atividade 1 – Extração de Blocos Coesos
- **Problema**: O método `extrato()` era muito longo e com baixa coesão.
- **Solução**: Identificou-se um bloco de código responsável pelo cálculo do valor de uma locação (switch com os preços). Esse bloco foi extraído para um método privado `valorDeUmaLocacao()` dentro da classe `Cliente`.
- **Resultado**: Método `extrato()` ficou mais enxuto, e o novo método passou a ter uma responsabilidade única e clara.

### Atividade 2 – Refinamento e Nomes Significativos
- **Problema**: Nomes de variáveis pouco descritivos (ex: `valorCorrente`).
- **Solução**: Renomearam-se variáveis e parâmetros para melhor comunicar o propósito do código.
- **Benefício**: Código mais legível e autoexplicativo.

---

## Etapa 3: Delegando Responsabilidades (GRASP – Expert Information)

### Atividade 1 – Mover `valorDeUmaLocacao()` para a Classe `Locacao`
- **Problema**: O cálculo do valor de uma locação usava dados da classe `Locacao` (dias alugados) e do `Automovel` (código de preço), mas estava na classe `Cliente`.
- **Solução**: Segundo o princípio *Expert*, a responsabilidade deve ficar com a classe que possui a informação. Assim, moveu-se o método `valorDeUmaLocacao()` para a classe `Locacao`.
- **Impacto**: `Cliente` agora delega o cálculo para `Locacao`, reduzindo seu acoplamento com `Automovel`.

### Atividade 2 – Remoção de Variáveis Temporárias Desnecessárias
- **Problema**: A variável `valorCorrente` era usada apenas para acumular o valor de uma locação antes de ser adicionada ao total.
- **Solução**: Remoção da variável, usando o retorno de `valorDeUmaLocacao()` diretamente na soma e formatação.
- **Benefício**: Código mais limpo e menos propenso a erros.

### Atividade 3 – Extração do Cálculo de Pontos
- **Problema**: O cálculo dos pontos de alugador frequente também estava misturado no `extrato()`.
- **Solução**: Criou-se um método `calculaPontos()` na classe `Locacao` (pois a informação necessária está ali).
- **Resultado**: `Cliente` passou a delegar também o cálculo de pontos.

### Atividade 4 – Criação de Métodos para Totais
- **Problema**: O cálculo do valor total e dos pontos totais era feito dentro do loop no `extrato()`, dificultando a reutilização (ex.: para um `extratoHTML()`).
- **Solução**: Criaram-se os métodos `getValorTotal()` e `getPontosTotaisDeAlugadorFrequente()` na classe `Cliente`, que percorrem a lista de locações e acumulam os valores.
- **Impacto**: O método `extrato()` ficou ainda mais enxuto, e agora é possível gerar outros formatos de extrato sem repetir lógica de cálculo.

---

## Etapa 4: Criando Extrato em HTML e Reavaliando Responsabilidades

### Atividade 1 – Implementação do `extratoHTML()`
- **Problema**: O sistema precisava gerar extratos em formato HTML, mas o código atual não permitia reuso dos cálculos.
- **Solução**: Utilizando os métodos `getValorTotal()` e `getPontosTotaisDeAlugadorFrequente()`, implementou-se o método `extratoHTML()` na classe `Cliente` com pouca duplicação de código.
- **Benefício**: Demonstrou-se a vantagem de ter métodos especializados para cálculos.

### Atividade 2 – Movendo Métodos para a Classe `Automovel`
- **Problema**: A classe `Locacao` ainda continha um `switch` que dependia de dados do `Automovel`, violando o princípio *Expert* (a informação sobre o preço está no automóvel).
- **Solução**: Transferiram-se os métodos `valorDeUmaLocacao()` e `calculaPontos()` para a classe `Automovel`, que agora recebem o número de dias como parâmetro.
- **Em `Locacao`**: Os métodos foram mantidos, mas passaram a delegar a chamada para o objeto `Automovel`.
- **Resultado**: `Locacao` ficou mais coesa e com baixo acoplamento.

---

## Etapa 5: Introdução de Polimorfismo – Interface `Alugavel`

### Problema
O sistema precisava ser preparado para alugar outros tipos de itens (motocicletas, veículos elétricos), mas `Locacao` estava fortemente acoplada à classe `Automovel`. Além disso, o `switch` ainda existia, indicando a necessidade de polimorfismo.

### Solução
- Criou-se a interface `Alugavel`, com os métodos:
  - `String getDescricao()`
  - `int getAno()`
  - `double getValorDaLocacao(int dias)`
  - `int getPontosDeAlugadorFrequente(int dias)`
- A classe `Automovel` passou a implementar essa interface.
- A classe `Locacao` passou a ter um atributo do tipo `Alugavel` (ao invés de `Automovel`), e seus métodos delegam para a interface.
- A classe `Cliente` foi ajustada para usar `getItem()` no lugar de `getCarro()`.

### Benefícios
- **Desacoplamento**: `Locacao` não depende mais de `Automovel`, apenas da abstração `Alugavel`.
- **Extensibilidade**: Novos tipos de itens alugáveis podem ser adicionados sem modificar `Locacao` ou `Cliente`.
- **Polimorfismo**: Cada tipo de item pode ter sua própria lógica de cálculo.

---

## Etapa 6: Substituindo o `switch` por Composição – Hierarquia de Classificações

### Problema
O `switch` dentro de `Automovel` ainda definia o comportamento de acordo com o código de preço. Isso violava o princípio *Open/Closed* e dificultava a adição de novas classificações (ex.: Premium, Elétrico).

### Solução
- Criou-se uma classe abstrata `Classificacao`, com métodos abstratos:
  - `int getCodigoDoPreco()`
  - `double getValorDaLocacao(int dias)`
  - Um método concreto `getPontosDeAlugadorFrequente(int dias)` que retorna 1 (padrão).
- Criaram-se três subclasses concretas:
  - `Basica`: retorna preço * dias e código BASICO.
  - `Familia`: retorna preço * dias e código FAMILIA.
  - `Luxo`: retorna preço * dias com desconto de 10% se > 4 dias, e sobrescreve `getPontosDeAlugadorFrequente()` para adicionar bônus.
- A classe `Automovel` passou a ter um atributo `Classificacao` em vez do `codigoDoPreco`.
- O método `setCodigoDoPreco()` foi mantido, mas agora ele cria a classificação concreta adequada (padrão *Creator*).
- Os métodos `getValorDaLocacao()` e `getPontosDeAlugadorFrequente()` delegam para o objeto `classificacao`.

### Benefícios
- **Composição sobre Herança**: A classificação pode ser alterada em tempo de execução (um carro pode mudar de Basica para Luxo sem recriar o objeto).
- **Fácil extensão**: Para adicionar uma nova classificação, basta criar uma nova subclasse de `Classificacao` e atualizar o `setCodigoDoPreco()`.
- **Eliminação do `switch`**: O comportamento polimórfico é obtido naturalmente via delegação.
- **Maior coesão**: Cada classe de classificação encapsula sua própria lógica.

---

## Etapa 7: Conclusão – Análise Final

### O que foi alcançado?
- **Código mais limpo e organizado**: Responsabilidades bem distribuídas entre classes.
- **Alta coesão e baixo acoplamento**: Cada classe tem uma única razão para mudar.
- **Extensibilidade**: Fácil adicionar novos tipos de itens alugáveis ou novas classificações.
- **Reutilização**: Métodos como `getValorTotal()` e `getPontosTotais...` podem ser usados em diferentes contextos (extrato texto, HTML, etc.).
- **Flexibilidade**: A composição permite alterar comportamento em tempo de execução.

### Princípios GRASP Aplicados
- **Expert Information**: As responsabilidades foram colocadas nas classes que possuem a informação (Locacao, Automovel, Classificacao).
- **Baixo Acoplamento**: Interfaces e delegação reduziram dependências diretas.
- **Alta Coesão**: Cada método e classe tem um propósito claro e único.
- **Creator**: `Automovel` é responsável por criar sua própria classificação via `setCodigoDoPreco()`.

### Aprendizados
- Refatoração não é apenas "arrumar código", mas sim melhorar a estrutura interna sem alterar o comportamento externo.
- A composição é frequentemente superior à herança quando se deseja flexibilidade e mudanças em tempo de execução.
- O uso de interfaces e classes abstratas permite desacoplar e polimorfizar comportamentos.
- A aplicação de princípios GRASP e SOLID resulta em um sistema mais manutenível e preparado para mudanças futuras.

---

## Código Final – Estrutura de Classes

```
Alugavel (interface)
    └── Automovel (implementa)
            └── composição com Classificacao

Classificacao (abstract)
    ├── Basica
    ├── Familia
    └── Luxo

Locacao (usa Alugavel)
Cliente (agrega Locacao)
Locadora (main)
```

A saída do programa permaneceu **idêntica** após todas as refatorações, comprovando que o comportamento externo foi preservado, enquanto a qualidade interna foi drasticamente melhorada.

---

**Observação final:** O exercício demonstrou que refatorações incrementais, guiadas por princípios de design, transformam um código frágil e inflexível em uma base sólida e adaptável, pronta para receber novas funcionalidades com mínimo impacto.