public class Celula {
    private Posicao posicao; // Posição da célula na matriz do ambiente
    private TipoCelula tipo; // Tipo da célula (livre, poço, obstáculo, tesouro, etc.)

    // Construtor: inicializa a célula com uma posição e um tipo
    public Celula(Posicao posicao, TipoCelula tipo) {
        this.posicao = posicao;
        this.tipo = tipo;
    }

    // Retorna a posição da célula
    public Posicao getPosicao() {
        return posicao;
    }

    // Retorna o tipo da célula
    public TipoCelula getTipo() {
        return tipo;
    }

    // Define (altera) o tipo da célula
    public void setTipo(TipoCelula tipo) {
        this.tipo = tipo;
    }

    // Retorna true se a célula for perigosa (poço ou obstáculo)
    public boolean ehPerigosa() {
        return tipo == TipoCelula.POCOS || tipo == TipoCelula.OBSTACULO;
    }

    // Retorna true se a célula for livre para o agente passar
    public boolean ehLivre() {
        return tipo == TipoCelula.LIVRE || 
               tipo == TipoCelula.INDICIO_TESOURO || 
               tipo == TipoCelula.INDICIO_POCO || 
               tipo == TipoCelula.TESOURO;
    }
}
