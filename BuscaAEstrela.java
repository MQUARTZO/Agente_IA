import java.util.*; // Importa todas as classes utilitárias necessárias (List, Set, PriorityQueue, etc.)

// Classe responsável por realizar a busca A* no ambiente
public class BuscaAEstrela {

    private Ambiente ambiente; // Referência ao ambiente onde a busca será realizada

    // Construtor que recebe o ambiente
    public BuscaAEstrela(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    // Classe interna (record) que representa um nó na busca
    // Guarda a posição, custo até o nó, estimativa até o objetivo e o nó pai
    private record No(Posicao pos, int custo, int estimativa, No pai) implements Comparable<No> {
        // Compara nós pelo custo total (custo real + heurística)
        @Override
        public int compareTo(No outro) {
            return Integer.compare(this.custo + this.estimativa, outro.custo + outro.estimativa);
        }
    }

    // Método principal que executa a busca A* do início até o objetivo
    public List<Posicao> buscar(Posicao inicio, Posicao objetivo) {
        PriorityQueue<No> fronteira = new PriorityQueue<>(); // Fila de prioridade dos nós a serem explorados
        Set<Posicao> visitados = new HashSet<>(); // Conjunto de posições já visitadas

        // Adiciona o nó inicial na fronteira
        fronteira.add(new No(inicio, 0, inicio.distancia(objetivo), null));

        // Enquanto houver nós para explorar
        while (!fronteira.isEmpty()) {
            No atual = fronteira.poll(); // Pega o nó com menor custo total

            // Se já visitou essa posição, ignora
            if (visitados.contains(atual.pos())) continue;
            visitados.add(atual.pos()); // Marca como visitado

            // Se chegou ao objetivo, reconstrói e retorna o caminho
            if (atual.pos().equals(objetivo)) {
                return reconstruirCaminho(atual);
            }

            // Para cada vizinho da posição atual
            for (Posicao vizinho : vizinhos(atual.pos())) {
                if (visitados.contains(vizinho)) continue; // Ignora se já visitado

                Celula cel = ambiente.getCelula(vizinho); // Obtém a célula do vizinho
                if (cel == null || !cel.ehLivre()) continue; // Ignora se não é livre ou fora do ambiente

                int novoCusto = atual.custo + 1; // Custo do caminho até o vizinho (incrementa 1)
                int heuristica = vizinho.distancia(objetivo); // Heurística (distância de Manhattan)

                // Adiciona o vizinho à fronteira para ser explorado
                fronteira.add(new No(vizinho, novoCusto, heuristica, atual));
            }
        }

        return null; // Nenhum caminho encontrado
    }

    // Retorna uma lista com as posições vizinhas (cima, baixo, esquerda, direita)
    private List<Posicao> vizinhos(Posicao pos) {
        int[][] direcoes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Direções possíveis
        List<Posicao> viz = new ArrayList<>();

        for (int[] d : direcoes) {
            Posicao nova = new Posicao(pos.linha + d[0], pos.coluna + d[1]); // Calcula a nova posição
            if (ambiente.estaDentro(nova)) { // Verifica se está dentro dos limites do ambiente
                viz.add(nova);
            }
        }

        return viz;
    }

    // Reconstrói o caminho do objetivo até o início usando os nós pais
    private List<Posicao> reconstruirCaminho(No no) {
        List<Posicao> caminho = new LinkedList<>();
        while (no != null) {
            caminho.add(0, no.pos()); // Adiciona no início da lista para inverter a ordem
            no = no.pai(); // Vai para o nó pai
        }
        caminho.remove(0); // Remove a posição inicial, pois o agente já está nela
        return caminho;
    }
}
