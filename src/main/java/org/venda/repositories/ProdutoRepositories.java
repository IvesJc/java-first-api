package org.venda.repositories;

import org.venda.models.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepositories {

    public static List<Produto> produtos = new ArrayList<>();

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public ProdutoRepositories() {
    }

    public List<Produto> getProdutos(){
        List<Produto> lista = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUTO");
             ResultSet rs = st.executeQuery()){

            while (rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("ID"));
                produto.setNome(rs.getString("NOME"));
                produto.setPreco(rs.getDouble("PRECO"));
                lista.add(produto);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Produto getProdutoById(int id){
        Produto produto = null;

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUTO WHERE ID = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                produto = new Produto();
                produto.setId(rs.getInt("ID"));
                produto.setNome(rs.getString("NOME"));
                produto.setPreco(rs.getDouble("PRECO"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return produto;
    }

    public int createProduto(Produto produto){
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO PRODUTO (nome, preco) VALUES (?, ?)")){

            st.setString(1, produto.getNome());
            st.setDouble(2, produto.getPreco());

            return st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Produto deleteProduto(int id){
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
        PreparedStatement st = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?")){
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



}
