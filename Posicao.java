public class Posicao {
    public int linha;   // Representa a linha da posição na matriz
    public int coluna;  // Representa a coluna da posição na matriz

    // Construtor: inicializa a posição com linha e coluna
    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    // Compara se esta posição é igual a outra (mesma linha e coluna)
    public boolean equals(Object o) {
        if (!(o instanceof Posicao)) return false; // Se não for do tipo Posicao, retorna falso
        Posicao p = (Posicao) o; // Faz o cast para Posicao
        return this.linha == p.linha && this.coluna == p.coluna; // Compara linha e coluna
    }

    // Gera um código hash para a posição (usado em coleções como HashSet)
    public int hashCode() {
        return 31 * linha + coluna;
    }

    // Retorna a posição como string no formato (linha,coluna)
    public String toString() {
        return "(" + linha + "," + coluna + ")";
    }

    // Calcula a distância de Manhattan até outra posição (usada na heurística do A*)
    public int distancia(Posicao destino) {
        return Math.abs(this.linha - destino.linha) + Math.abs(this.coluna - destino.coluna);
    }
}
