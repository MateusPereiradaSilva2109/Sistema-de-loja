// Pacote model
package model;

public class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - Preço: R$ " + preco;
    }
}

package model;

public class ProdutoVendido {
    private Produto produto;
    private int quantidade;

    public ProdutoVendido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return produto.getNome() + " - Quantidade: " + quantidade + " - Total: R$ " + (produto.getPreco() * quantidade);
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private List<ProdutoVendido> produtosVendidos;
    private double total;

    public Venda(int id) {
        this.id = id;
        this.produtosVendidos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void addProdutoVendido(ProdutoVendido produtoVendido) {
        produtosVendidos.add(produtoVendido);
        total += produtoVendido.getProduto().getPreco() * produtoVendido.getQuantidade();
    }

    public List<ProdutoVendido> getProdutosVendidos() {
        return produtosVendidos;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Venda ID: " + id + " - Total: R$ " + total;
    }
}

// Pacote controller
package controller;

import model.Produto;
import model.Venda;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private List<Produto> estoque = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();
    private int proximoIdVenda = 1;

    public void cadastrarProduto(Produto produto) {
        estoque.add(produto);
    }

    public List<Produto> listarProdutos() {
        return estoque;
    }

    public Venda novaVenda() {
        Venda venda = new Venda(proximoIdVenda++);
        vendas.add(venda);
        return venda;
    }

    public List<Venda> listarVendas() {
        return vendas;
    }
}

// Pacote view
package view;

import controller.Controlador;
import model.Produto;
import model.Venda;
import model.ProdutoVendido;

import javax.swing.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LojaFrame extends JFrame {
    private Controlador controlador;

    public LojaFrame(Controlador controlador) {
        this.controlador = controlador;

        setTitle("Gerenciamento de Loja");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton cadastrarProdutoButton = new JButton("Cadastrar Produto");
        JButton listarProdutosButton = new JButton("Listar Produtos");
        JButton novaVendaButton = new JButton("Nova Venda");

        cadastrarProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome;
                while (true) {
                    nome = JOptionPane.showInputDialog("Nome do produto:");
                    if (nome != null && !nome.trim().isEmpty()) {
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Nome do produto não pode ser vazio.");
                }

                double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do produto:"));
                controlador.cadastrarProduto(new Produto(nome, preco));
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            }
        });

        listarProdutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder produtos = new StringBuilder("Produtos no estoque:\n");
                for (Produto p : controlador.listarProdutos()) {
                    produtos.append(p).append("\n");
                }
                JOptionPane.showMessageDialog(null, produtos.toString());
            }
        });

        novaVendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Venda venda = controlador.novaVenda();
                StringBuilder vendaDetalhes = new StringBuilder("Venda iniciada - ID: " + venda.getId() + "\n");

                while (true) {
                    String nomeProduto = JOptionPane.showInputDialog("Nome do produto para adicionar (ou deixe em branco para finalizar):");
                    if (nomeProduto == null || nomeProduto.isBlank()) {
                        break;
                    }

                    Produto produtoEncontrado = controlador.listarProdutos().stream()
                            .filter(p -> p.getNome().equalsIgnoreCase(nomeProduto))
                            .findFirst()
                            .orElse(null);

                    if (produtoEncontrado == null) {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado no estoque.");
                        // Pacote model
package model;

                        public class Produto {
                            private String nome;
                            private double preco;

                            public Produto(String nome, double preco) {
                                this.nome = nome;
                                this.preco = preco;
                            }

                            public String getNome() {
                                return nome;
                            }

                            public void setNome(String nome) {
                                this.nome = nome;
                            }

                            public double getPreco() {
                                return preco;
                            }

                            public void setPreco(double preco) {
                                this.preco = preco;
                            }

                            @Override
                            public String toString() {
                                return nome + " - Preço: R$ " + preco;
                            }
                        }

package model;

                        public class ProdutoVendido {
                            private Produto produto;
                            private int quantidade;

                            public ProdutoVendido(Produto produto, int quantidade) {
                                this.produto = produto;
                                this.quantidade = quantidade;
                            }

                            public Produto getProduto() {
                                return produto;
                            }

                            public void setProduto(Produto produto) {
                                this.produto = produto;
                            }

                            public int getQuantidade() {
                                return quantidade;
                            }

                            public void setQuantidade(int quantidade) {
                                this.quantidade = quantidade;
                            }

                            @Override
                            public String toString() {
                                return produto.getNome() + " - Quantidade: " + quantidade + " - Total: R$ " + (produto.getPreco() * quantidade);
                            }
                        }

package model;

import java.util.ArrayList;
import java.util.List;

                        public class Venda {
                            private int id;
                            private List<ProdutoVendido> produtosVendidos;
                            private double total;

                            public Venda(int id) {
                                this.id = id;
                                this.produtosVendidos = new ArrayList<>();
                            }

                            public int getId() {
                                return id;
                            }

                            public void addProdutoVendido(ProdutoVendido produtoVendido) {
                                produtosVendidos.add(produtoVendido);
                                total += produtoVendido.getProduto().getPreco() * produtoVendido.getQuantidade();
                            }

                            public List<ProdutoVendido> getProdutosVendidos() {
                                return produtosVendidos;
                            }

                            public double getTotal() {
                                return total;
                            }

                            @Override
                            public String toString() {
                                return "Venda ID: " + id + " - Total: R$ " + total;
                            }
                        }

// Pacote controller
package controller;

import model.Produto;
import model.Venda;

import java.util.ArrayList;
import java.util.List;

                        public class Controlador {
                            private List<Produto> estoque = new ArrayList<>();
                            private List<Venda> vendas = new ArrayList<>();
                            private int proximoIdVenda = 1;

                            public void cadastrarProduto(Produto produto) {
                                estoque.add(produto);
                            }

                            public List<Produto> listarProdutos() {
                                return estoque;
                            }

                            public Venda novaVenda() {
                                Venda venda = new Venda(proximoIdVenda++);
                                vendas.add(venda);
                                return venda;
                            }

                            public List<Venda> listarVendas() {
                                return vendas;
                            }
                        }

// Pacote view
package view;

import controller.Controlador;
import model.Produto;
import model.Venda;
import model.ProdutoVendido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

                        public class LojaFrame extends JFrame {
                            private Controlador controlador;

                            public LojaFrame(Controlador controlador) {
                                this.controlador = controlador;

                                setTitle("Gerenciamento de Loja");
                                setSize(400, 300);
                                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

                                JButton cadastrarProdutoButton = new JButton("Cadastrar Produto");
                                JButton listarProdutosButton = new JButton("Listar Produtos");
                                JButton novaVendaButton = new JButton("Nova Venda");

                                cadastrarProdutoButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String nome;
                                        while (true) {
                                            nome = JOptionPane.showInputDialog("Nome do produto:");
                                            if (nome != null && !nome.trim().isEmpty()) {
                                                break;
                                            }
                                            JOptionPane.showMessageDialog(null, "Nome do produto não pode ser vazio.");
                                        }

                                        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do produto:"));
                                        controlador.cadastrarProduto(new Produto(nome, preco));
                                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                                    }
                                });

                                listarProdutosButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        StringBuilder produtos = new StringBuilder("Produtos no estoque:\n");
                                        for (Produto p : controlador.listarProdutos()) {
                                            produtos.append(p).append("\n");
                                        }
                                        JOptionPane.showMessageDialog(null, produtos.toString());
                                    }
                                });

                                novaVendaButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        Venda venda = controlador.novaVenda();
                                        StringBuilder vendaDetalhes = new StringBuilder("Venda iniciada - ID: " + venda.getId() + "\n");

                                        while (true) {
                                            String nomeProduto = JOptionPane.showInputDialog("Nome do produto para adicionar (ou deixe em branco para finalizar):");
                                            if (nomeProduto == null || nomeProduto.isBlank()) {
                                                break;
                                            }

                                            Produto produtoEncontrado = controlador.listarProdutos().stream()
                                                    .filter(p -> p.getNome().equalsIgnoreCase(nomeProduto))
                                                    .findFirst()
                                                    .orElse(null);

                                            if (produtoEncontrado == null) {
                                                JOptionPane.showMessageDialog(null, "Produto não encontrado no estoque.");
                                                continue;
                                            }

                                            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade do produto:"));
                                            venda.addProdutoVendido(new ProdutoVendido(produtoEncontrado, quantidade));
                                        }

                                        vendaDetalhes.append("Resumo da Venda:\n");
                                        for (ProdutoVendido pv : venda.getProdutosVendidos()) {
                                            vendaDetalhes.append(pv).append("\n");
                                        }
                                        vendaDetalhes.append("Total da Venda: R$ ").append(venda.getTotal());
                                        JOptionPane.showMessageDialog(null, vendaDetalhes.toString());
                                    }
                                });

                                add(cadastrarProdutoButton);
                                add(listarProdutosButton);
                                add(novaVendaButton);
                            }

                            public static void main(String[] args) {
                                Controlador controlador = new Controlador();
                                new LojaFrame(controlador).setVisible(true);
                            }
                        }
                        continue;
                    }

                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade do produto:"));
                    venda.addProdutoVendido(new ProdutoVendido(produtoEncontrado, quantidade));
                }

                vendaDetalhes.append("Resumo da Venda:\n");
                for (ProdutoVendido pv : venda.getProdutosVendidos()) {
                    vendaDetalhes.append(pv).append("\n");
                }
                vendaDetalhes.append("Total da Venda: R$ ").append(venda.getTotal());
                JOptionPane.showMessageDialog(null, vendaDetalhes.toString());
            }
        });

        add(cadastrarProdutoButton);
        add(listarProdutosButton);
        add(novaVendaButton);
    }

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        new LojaFrame(controlador).setVisible(true);
    }
}
