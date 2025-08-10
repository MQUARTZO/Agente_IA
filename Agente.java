import java.util.*; // Importa todas as classes do pacote java.util, como List e ArrayList

// Classe que representa o agente inteligente no ambiente
public class Agente {
    private Posicao posicaoAtual; // Posição atual do agente no ambiente
    private Ambiente ambiente; // Referência ao ambiente onde o agente está
    private List<Posicao> caminhoPlanejado; // Lista de posições que formam o caminho planejado até o tesouro
    private int tics; // Contador de movimentos realizados pelo agente
    private boolean vivo; // Indica se o agente ainda está vivo

    // Construtor do agente, inicializa as variáveis
    public Agente(Ambiente ambiente) {
        this.ambiente = ambiente; // Define o ambiente do agente
        this.posicaoAtual = new Posicao(0, 0); // Define a posição inicial no canto inferior esquerdo
        this.caminhoPlanejado = new ArrayList<>(); // Inicializa a lista do caminho planejado
        this.vivo = true; // O agente começa vivo
        this.tics = 0; // Inicializa o contador de movimentos
    }

    // Método para iniciar a busca pelo caminho até o tesouro
    public void iniciarBusca() {
        System.out.println("🧠 Buscando o melhor caminho...");
        planejarCaminho(); // Chama o método que planeja o caminho
    }

    // Método privado que planeja o caminho usando o algoritmo A*
    private void planejarCaminho() {
        BuscaAEstrela busca = new BuscaAEstrela(ambiente); // Cria uma instância do algoritmo de busca A*
        caminhoPlanejado = busca.buscar(posicaoAtual, ambiente.getPosicaoTesouro()); // Busca o caminho até o tesouro

        // Se não encontrou caminho ou o caminho está vazio, informa que não é possível chegar ao tesouro
        if (caminhoPlanejado == null || caminhoPlanejado.isEmpty()) {
            System.out.println("❌ Nenhum caminho possível até o tesouro.");
        } else {
            // Caso contrário, informa quantos passos o caminho possui
            System.out.println("📍 Caminho planejado com " + caminhoPlanejado.size() + " passos.\n");
        }
    }

    // Executa o próximo movimento do agente no caminho planejado
    public void executarProximoMovimento() {
        // Se o agente está morto, não há caminho ou o caminho acabou, não faz nada
        if (!vivo || caminhoPlanejado == null || caminhoPlanejado.isEmpty()) return;

        Posicao proxima = caminhoPlanejado.remove(0); // Remove e obtém a próxima posição do caminho
        Celula destino = ambiente.getCelula(proxima); // Obtém a célula do ambiente correspondente à próxima posição

        // Se a célula de destino é um poço, o agente morre
        if (destino.getTipo() == TipoCelula.POCOS) {
            posicaoAtual = proxima; // Atualiza a posição do agente
            vivo = false; // Marca o agente como morto
            tics++; // Incrementa o contador de movimentos
            System.out.println("[Tic " + tics + "] ☠️ O agente caiu em um poço em " + posicaoAtual + " e morreu!");
            ambiente.imprimirAmbiente(posicaoAtual); // Imprime o ambiente com a posição atual
            return; // Encerra o método
        }

        // Se a célula de destino é um obstáculo, recalcula o caminho
        if (destino.getTipo() == TipoCelula.OBSTACULO) {
            System.out.println("[Tic " + tics + "] ⚠️ Obstáculo detectado em " + proxima + ", recalculando rota.");
            planejarCaminho(); // Recalcula o caminho
            return; // Encerra o método
        }

        // Caso contrário, move o agente para a próxima posição
        posicaoAtual = proxima; // Atualiza a posição do agente
        tics++; // Incrementa o contador de movimentos
        System.out.println("[Tic " + tics + "] ✅ Moveu-se para " + posicaoAtual);
        ambiente.imprimirAmbiente(posicaoAtual); // Imprime o ambiente com a nova posição
        System.out.println(); // Linha em branco para separar as saídas
    }

    // Verifica se o agente encontrou o tesouro
    public boolean encontrouTesouro() {
        return ambiente.getCelula(posicaoAtual).getTipo() == TipoCelula.TESOURO;
    }

    // Verifica se ainda há movimentos possíveis e se o agente está vivo
    public boolean aindaTemMovimentos() {
        return caminhoPlanejado != null && !caminhoPlanejado.isEmpty() && vivo;
    }

    // Retorna se o agente está vivo
    public boolean estaVivo() {
        return vivo;
    }

    // Retorna a posição atual do agente
    public Posicao getPosicaoAtual() {
        return posicaoAtual;
    }

}
