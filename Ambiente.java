import java.util.Random; // Importa a classe Random para geração de números aleatórios

// Classe que representa o ambiente onde o agente irá atuar
public class Ambiente {
    private final int linhas; // Número de linhas da matriz do ambiente
    private final int colunas; // Número de colunas da matriz do ambiente
    private Celula[][] matriz; // Matriz de células que compõe o ambiente
    private Posicao posicaoTesouro; // Posição onde o tesouro está localizado
    private Random random = new Random(); // Objeto para gerar números aleatórios

    // Construtor do ambiente, define o tamanho e inicializa a matriz
    public Ambiente(int linhas, int colunas) {
        this.linhas = linhas; // Define o número de linhas
        this.colunas = colunas; // Define o número de colunas
        this.matriz = new Celula[linhas][colunas]; // Cria a matriz de células
        inicializarAleatorio(); // Inicializa o ambiente de forma aleatória
    }

    // Inicializa o ambiente aleatoriamente com poços, obstáculos e tesouro
    private void inicializarAleatorio() {
        // Preenche toda a matriz com células livres
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = new Celula(new Posicao(i, j), TipoCelula.LIVRE);
            }
        }

        // Define uma quantidade aleatória de poços (entre 3 e 7) e os insere
        int totalPocos = random.nextInt(5) + 3;
        for (int i = 0; i < totalPocos; i++) {
            adicionarAleatorio(TipoCelula.POCOS);
        }

        // Define uma quantidade aleatória de obstáculos (entre 3 e 7) e os insere
        int totalObstaculos = random.nextInt(5) + 3;
        for (int i = 0; i < totalObstaculos; i++) {
            adicionarAleatorio(TipoCelula.OBSTACULO);
        }

        // Insere o tesouro em uma posição aleatória que não seja a inicial (0,0)
        while (true) {
            int l = random.nextInt(linhas); // Gera linha aleatória
            int c = random.nextInt(colunas); // Gera coluna aleatória
            if (l != 0 || c != 0) { // Garante que não seja a posição inicial do agente
                matriz[l][c].setTipo(TipoCelula.TESOURO); // Define o tipo da célula como tesouro
                posicaoTesouro = new Posicao(l, c); // Salva a posição do tesouro
                break; // Sai do loop após inserir o tesouro
            }
        }
    }

    // Adiciona um elemento (poço ou obstáculo) em uma posição aleatória livre e diferente da inicial
    private void adicionarAleatorio(TipoCelula tipo) {
        while (true) {
            int l = random.nextInt(linhas); // Gera linha aleatória
            int c = random.nextInt(colunas); // Gera coluna aleatória
            // Verifica se a célula está livre e não é a posição inicial
            if (matriz[l][c].getTipo() == TipoCelula.LIVRE && (l != 0 || c != 0)) {
                matriz[l][c].setTipo(tipo); // Define o tipo da célula
                break; // Sai do loop após inserir
            }
        }
    }

    // Imprime o ambiente no console, mostrando a posição do agente e os tipos de células
    public void imprimirAmbiente(Posicao agentePosicao) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                Posicao p = new Posicao(i, j); // Cria a posição atual
                if (agentePosicao != null && p.equals(agentePosicao)) {
                    System.out.print(" A "); // Imprime o agente se estiver nesta posição
                } else {
                    // Imprime o símbolo correspondente ao tipo da célula
                    switch (matriz[i][j].getTipo()) {
                        case OBSTACULO -> System.out.print(" X ");
                        case POCOS -> System.out.print(" P ");
                        case INDICIO_TESOURO -> System.out.print(" + ");
                        case INDICIO_POCO -> System.out.print(" - ");
                        case TESOURO -> System.out.print(" T ");
                        default -> System.out.print(" . ");
                    }
                }
            }
            System.out.println(); // Quebra de linha ao final de cada linha da matriz
        }
    }

    // Retorna a célula correspondente a uma posição, se estiver dentro dos limites
    public Celula getCelula(Posicao pos) {
        if (estaDentro(pos)) {
            return matriz[pos.linha][pos.coluna];
        }
        return null; // Retorna null se a posição for inválida
    }

    // Verifica se uma posição está dentro dos limites do ambiente
    public boolean estaDentro(Posicao p) {
        return p.linha >= 0 && p.coluna >= 0 && p.linha < linhas && p.coluna < colunas;
    }

    // Retorna a posição do tesouro
    public Posicao getPosicaoTesouro() {
        return posicaoTesouro;
    }
}
