# 🤖 Agente_IA

Um projeto em **Java** que implementa um agente inteligente utilizando o algoritmo de busca heurística **A\*** para explorar um ambiente representado por uma grade (grid).  
O agente é capaz de navegar pelo mapa, evitando obstáculos e encontrando o caminho mais eficiente até o objetivo.

---

## 📂 Estrutura do Projeto

| Arquivo            | Descrição |
|--------------------|-----------|
| `Main.java`        | Ponto de entrada da aplicação. Inicializa o ambiente e executa o agente. |
| `Ambiente.java`    | Representa o mundo do agente, organizado em uma grade de células. |
| `Celula.java`      | Estrutura que representa cada posição do grid, podendo conter custo, tipo, etc. |
| `Posicao.java`     | Classe para coordenadas (linha e coluna) no grid. |
| `TipoCelula.java`  | Enum que define o tipo de cada célula (livre, obstáculo, início, objetivo, etc.). |
| `Agente.java`      | Representa o agente e sua lógica de movimentação e decisão. |
| `BuscaAEstrela.java` | Implementa o algoritmo A\*, responsável por calcular o caminho mais curto. |

---

## 🚀 Funcionalidades

- 🗺️ **Configuração do ambiente** — Define mapa, obstáculos, ponto inicial e objetivo.
- 🔍 **Busca A\*** — Calcula o melhor caminho com base em custo e heurística.
- ⛔ **Detecção de obstáculos** — Evita células bloqueadas.
- 📊 **Caminho otimizado** — Menor custo possível até o destino.

---

## 🛠️ Como Executar

1. **Clonar o repositório**
    ```bash
    git clone https://github.com/MQUARTZO/Agente_IA.git
    cd Agente_IA
    ```

2. **Compilar o projeto**
    ```bash
    javac Main.java Agente.java Ambiente.java BuscaAEstrela.java Celula.java Posicao.java TipoCelula.java
    ```

3. **Executar**
    ```bash
    java Main
    ```

4. *(Opcional)* Edite o código em `Main.java` para testar diferentes mapas e posições.

---

## 🎯 Exemplo de Uso

Mapa com obstáculos:

- `S` = Início  
- `G` = Objetivo  
- `#` = Obstáculo  
- `.` = Caminho livre  

O agente aplicará o **A\*** para encontrar o trajeto mais curto evitando obstáculos.

---

## 💡 Melhorias Futuras

- 📥 Importar mapas de arquivos externos (CSV, TXT, JSON)
- 🎨 Interface gráfica com **JavaFX** ou **Swing**
- ⚙️ Suporte a múltiplos algoritmos (Dijkstra, BFS, DFS)
- 🔄 Geração aleatória de mapas
- 🧠 Diferentes heurísticas (Manhattan, Euclidiana, Octile)

---

## 🤝 Contribuindo

Contribuições são bem-vindas!  
Siga os passos:

1. Fork o repositório  
2. Crie uma branch (`git checkout -b minha-feature`)  
3. Commit suas alterações (`git commit -m 'Minha melhoria'`)  
4. Push para a branch (`git push origin minha-feature`)  
5. Abra um Pull Request

---

## 📜 Licença

Este projeto atualmente **não possui licença**.  
Recomenda-se adicionar uma licença como **MIT** ou **GPL** para definir permissões de uso.

---

Feito com ❤️ por [Mateus Araujo Silva]
