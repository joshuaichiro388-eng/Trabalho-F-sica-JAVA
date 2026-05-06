import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // criando a janela principal
        JFrame frame = new JFrame("Trabalho Fisica Java");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Usado para dividir a tela
        frame.setLayout(new BorderLayout());

        // Adiciona o painel principal o de cima com as informaçoes principais
        JPanel painelTopo = new JPanel(new GridLayout(10, 2, 5, 5));

        // Informaçoes fixas da questão
        JTextField distanciaTotal = new JTextField();
        JTextField v1 = new JTextField();
        JTextField d1 = new JTextField();
        JTextField v2 = new JTextField();
        JTextField d2 = new JTextField();
        JTextField horaSaida = new JTextField("08:00");
        JTextField horaChegada = new JTextField("11:15");

        // ampo pra quantidade de trechos extras
        JTextField qtdExtra = new JTextField();
        //Adiciona tudo no painel de cima
        painelTopo.add(new JLabel("Distância total (km):"));
        painelTopo.add(distanciaTotal);

        painelTopo.add(new JLabel("Velocidade trecho 1 (km/h):"));
        painelTopo.add(v1);

        painelTopo.add(new JLabel("Distância trecho 1 (km):"));
        painelTopo.add(d1);

        painelTopo.add(new JLabel("Velocidade trecho 2 (km/h):"));
        painelTopo.add(v2);

        painelTopo.add(new JLabel("Distância trecho 2 (km):"));
        painelTopo.add(d2);

        painelTopo.add(new JLabel("Hora saída (hh:mm):"));
        painelTopo.add(horaSaida);

        painelTopo.add(new JLabel("Hora chegada (hh:mm):"));
        painelTopo.add(horaChegada);

        painelTopo.add(new JLabel("Trechos extras:"));
        painelTopo.add(qtdExtra);
        //Botão que cria os trechos extras
        JButton adicionar = new JButton("ADICIONAR TRECHOS");
        painelTopo.add(adicionar);

        frame.add(painelTopo, BorderLayout.NORTH);

        // Painel dos trechos extras
        JPanel painelExtras = new JPanel();
        painelExtras.setLayout(new BoxLayout(painelExtras, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(painelExtras);

        frame.add(scroll, BorderLayout.CENTER);

        // Lista para guardar os campos criados
        ArrayList<JTextField> vExtras = new ArrayList<>();
        ArrayList<JTextField> dExtras = new ArrayList<>();

        // Resultados
        JLabel resultado = new JLabel("Resultado: ");
        JLabel velocidade = new JLabel("Velocidade necessária: ");
        JLabel tempos = new JLabel("Tempos: ");
        //Painel de baixo
        JPanel painelBaixo = new JPanel(new GridLayout(4,1));
        //Botão principal
        JButton calcular = new JButton("CALCULAR");
        JButton reset = new JButton("RESET");

        painelBaixo.add(calcular);
        painelBaixo.add(reset);
        painelBaixo.add(resultado);
        painelBaixo.add(velocidade);
        painelBaixo.add(tempos);

        frame.add(painelBaixo, BorderLayout.SOUTH);

        // função converter hora
        java.util.function.Function<String, Double> converterHora = (hora) -> {
            String[] partes = hora.split(":");
            int h = Integer.parseInt(partes[0]);
            int m = Integer.parseInt(partes[1]);
            return h + (m / 60.0);
        };

        // Codigo do trecho extra
        adicionar.addActionListener(e -> {
            try {
                int n = Integer.parseInt(qtdExtra.getText());
                painelExtras.removeAll();
                vExtras.clear();
                dExtras.clear();

                for (int i = 0; i < n; i++) {

                    JPanel linha = new JPanel(new GridLayout(1,4,5,5));

                    JTextField v = new JTextField();
                    JTextField d = new JTextField();

                    linha.add(new JLabel("Velocidade trecho " + (i+3)));
                    linha.add(v);
                    linha.add(new JLabel("Distância trecho " + (i+3)));
                    linha.add(d);

                    painelExtras.add(linha);

                    vExtras.add(v);
                    dExtras.add(d);
                }

                painelExtras.revalidate();
                painelExtras.repaint();

            } catch (Exception ex) {
                resultado.setText("Erro ao adicionar trechos.");
            }
        });

        // botão calcular
        calcular.addActionListener(e -> {
            try {
                //Pega os valores digitados
                double distTotal = Double.parseDouble(distanciaTotal.getText());
                double vel1 = Double.parseDouble(v1.getText());
                double dist1 = Double.parseDouble(d1.getText());
                double vel2 = Double.parseDouble(v2.getText());
                double dist2 = Double.parseDouble(d2.getText());

                double hSaida = converterHora.apply(horaSaida.getText());
                double hChegada = converterHora.apply(horaChegada.getText());
                //Tempo disponivel
                double tempoTotal = hChegada - hSaida;

                double somaTempo = 0;
                double somaDist = 0;

                // trecho 1 e 2
                double t1 = dist1 / vel1;
                double t2 = dist2 / vel2;

                somaTempo += t1 + t2;
                somaDist += dist1 + dist2;

                // Aumento de complexidade
                for (int i = 0; i < vExtras.size(); i++) {

                    double v = Double.parseDouble(vExtras.get(i).getText());
                    double d = Double.parseDouble(dExtras.get(i).getText());

                    somaTempo += d / v;
                    somaDist += d;
                }

                // restante
                double distRestante = distTotal - somaDist;
                double tempoRestante = tempoTotal - somaTempo;
                // Caso não der tempo de chegar
                if (tempoRestante <= 0 || distRestante < 0) {
                    resultado.setText("Não é possível chegar a tempo.");
                    return;
                }
                //Velocidade necessária
                double vFinal = distRestante / tempoRestante;
                //Mostra o resultado
                resultado.setText(String.format("Tempo total: %.2f h", tempoTotal));
                velocidade.setText(String.format("Velocidade necessária: %.2f km/h", vFinal));
                tempos.setText(String.format("Tempo usado nos trechos: %.2f h", somaTempo));

            } catch (Exception ex) {
                resultado.setText("Erro nos dados.");
            }
        });

        // botão reset limpa tudo
        reset.addActionListener(e -> {
            distanciaTotal.setText("");
            v1.setText("");
            d1.setText("");
            v2.setText("");
            d2.setText("");
            horaSaida.setText("08:00");
            horaChegada.setText("11:15");
            qtdExtra.setText("");

            painelExtras.removeAll();
            vExtras.clear();
            dExtras.clear();

            resultado.setText("Resultado: ");
            velocidade.setText("Velocidade necessária: ");
            tempos.setText("Tempos: ");

            painelExtras.revalidate();
            painelExtras.repaint();
        });
        //Jalena visivel
        frame.setVisible(true);
    }
}



