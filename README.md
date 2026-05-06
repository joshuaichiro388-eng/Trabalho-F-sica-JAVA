# 🚗 Calculadora de Viagem com Trechos (Java Swing)

Este projeto é uma aplicação desktop desenvolvida em Java utilizando a biblioteca gráfica Swing. O programa simula o cálculo de uma viagem dividida em múltiplos trechos, permitindo ao usuário determinar a velocidade necessária para completar o percurso dentro de um intervalo de tempo específico.

## 📌 Funcionalidades

* Inserção de:

  * Distância total da viagem
  * Velocidade e distância de dois trechos iniciais
  * Horário de saída e chegada
* Adição dinâmica de trechos extras
* Cálculo automático de:

  * Tempo total disponível
  * Tempo já utilizado nos trechos informados
  * Distância restante
  * Velocidade necessária para concluir a viagem no tempo previsto
* Botão de reset para limpar todos os campos

## 🧠 Lógica do Programa

O sistema calcula o tempo gasto em cada trecho com base na fórmula:

> tempo = distância / velocidade

A partir disso:

* Soma o tempo e a distância dos trechos informados
* Calcula quanto ainda falta percorrer
* Determina quanto tempo ainda resta
* Calcula a velocidade necessária para completar o percurso

Caso o tempo restante seja insuficiente ou a distância já tenha sido ultrapassada, o sistema informa que não é possível concluir a viagem dentro do prazo.

## 🖥️ Interface Gráfica

A interface é construída com Java Swing e organizada em três partes principais:

* **Topo:** campos de entrada de dados
* **Centro:** área dinâmica para adicionar trechos extras
* **Base:** botões de ação e exibição de resultados

## ⚙️ Tecnologias utilizadas

* Java
* Java Swing (GUI)
* Programação orientada a eventos
* Estruturas de dados (ArrayList)

## ▶️ Como executar

1. Abra o projeto no IntelliJ IDEA ou outra IDE Java
2. Execute a classe `Main`
3. Preencha os campos e clique em **CALCULAR**

## 📚 Objetivo

Este projeto foi desenvolvido como trabalho acadêmico para aplicar conceitos de física (movimento uniforme) aliados à programação em Java, incluindo interface gráfica e manipulação de eventos.

---

<img width="481" height="590" alt="image" src="https://github.com/user-attachments/assets/4958ab36-a747-448e-8e2c-126a96389f08" />
