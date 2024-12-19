/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.java;

import java.util.*;


class Produto {
    private int id;
    private String nome;
    private double preco;

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}

class CarrinhoDeCompras {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println(produto.getNome() + " foi adicionado ao carrinho.");
    }

    public void removerProduto(int id) {
        boolean removido = produtos.removeIf(produto -> produto.getId() == id);
        if (removido) {
            System.out.println("Produto com ID " + id + " foi removido do carrinho.");
        } else {
            System.out.println("Produto com ID " + id + " não encontrado no carrinho.");
        }
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("O carrinho está vazio.");
        } else {
            System.out.println("Produtos no carrinho:");
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    public double calcularTotal() {
        return produtos.stream().mapToDouble(Produto::getPreco).sum();
    }
}

public class SimuladorDeComercio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Produto> produtosDisponiveis = new ArrayList<>();
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        produtosDisponiveis.add(new Produto(1, "Martelo", 50.0));
        produtosDisponiveis.add(new Produto(2, "Chave de Fenda", 25.0));
        produtosDisponiveis.add(new Produto(3, "Alicate", 30.0));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Listar Produtos Disponíveis");
            System.out.println("2. Adicionar Produto ao Carrinho");
            System.out.println("3. Remover Produto do Carrinho");
            System.out.println("4. Ver Carrinho");
            System.out.println("5. Finalizar Compra");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.hasNextInt() ? scanner.nextInt() : -1;
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.println("\nProdutos disponíveis:");
                    for (Produto produto : produtosDisponiveis) {
                        System.out.println(produto);
                    }
                    break;
                case 2:
                    System.out.print("\nDigite o ID do produto que deseja adicionar: ");
                    if (scanner.hasNextInt()) {
                        int idAdicionar = scanner.nextInt();
                        scanner.nextLine(); // Limpar buffer
                        Optional<Produto> produtoEncontrado = produtosDisponiveis.stream()
                                .filter(produto -> produto.getId() == idAdicionar)
                                .findFirst();

                        if (produtoEncontrado.isPresent()) {
                            carrinho.adicionarProduto(produtoEncontrado.get());
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    } else {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine(); // Limpar buffer
                    }
                    break;
                case 3:
                    System.out.print("\nDigite o ID do produto que deseja remover: ");
                    if (scanner.hasNextInt()) {
                        int idRemover = scanner.nextInt();
                        scanner.nextLine(); // Limpar buffer
                        carrinho.removerProduto(idRemover);
                    } else {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine(); // Limpar buffer
                    }
                    break;
                case 4:
                    carrinho.listarProdutos();
                    break;
                case 5:
                    double total = carrinho.calcularTotal();
                    System.out.println("\nTotal da compra: R$" + total);
                    System.out.print("Digite o valor do frete: ");
                    if (scanner.hasNextDouble()) {
                        double frete = scanner.nextDouble();
                        System.out.println("Valor final (com frete): R$" + (total + frete));
                    } else {
                        System.out.println("Entrada inválida. Valor do frete não informado corretamente.");
                        scanner.nextLine(); // Limpar buffer
                    }
                    return;
                case 6:
                    System.out.println("\nObrigado por usar o simulador. Até logo!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
