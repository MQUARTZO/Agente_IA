public class Main {
    public static void main(String[] args) {
        System.out.println("==== PROJETO: AGENTE CAÇADOR DE TESOUROS ====\n"); // Exibe o título do projeto

        Ambiente ambiente = new Ambiente(8, 8); // Cria um ambiente 8x8 com elementos aleatórios
        Agente agente = new Agente(ambiente);   // Cria o agente e o posiciona no ambiente

        System.out.println("Ambiente inicial:");
        ambiente.imprimirAmbiente(agente.getPosicaoAtual()); // Mostra o ambiente inicial com o agente
        System.out.println();

        agente.iniciarBusca(); // O agente planeja o caminho até o tesouro

        // Enquanto o agente não encontrou o tesouro e ainda pode se mover
        while (!agente.encontrouTesouro() && agente.aindaTemMovimentos()) {
            agente.executarProximoMovimento(); // Executa o próximo passo do agente
            try {
                Thread.sleep(700); // Pausa por 700ms para simular o tempo de um "tic"
            } catch (InterruptedException e) {
                e.printStackTrace(); // Mostra erro caso a thread seja interrompida
            }
        }

        // Verifica o motivo do término da execução e exibe mensagem apropriada
        if (agente.encontrouTesouro()) {
            System.out.println("🎉 Tesouro conquistado com sucesso!");
        } else if (!agente.estaVivo()) {
            System.out.println("☠️ Execução encerrada: agente morreu.");
        } else {
            System.out.println("⚠️ Execução encerrada: sem movimentos restantes.");
        }
    }
}
