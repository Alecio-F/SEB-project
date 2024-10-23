package seb.project.dao;

// import da biblioteca java.sql e outros
import java.sql.Connection; //Gerencia a conexão com o banco de dados.
import java.sql.PreparedStatement; //Permite criar consultas parametrizadas, prevenindo injeções de SQL.
import java.sql.SQLException; //Trata exceções relacionadas a operações com o banco de dados.
import java.sql.ResultSet; //Manipula os resultados de consultas SQL.
import java.sql.Statement; //Executa consultas SQL simples (sem parâmetros).
import seb.project.model.Cliente; // Representa a entidade Cliente no sistema.
import seb.project.util.DatabaseConnection; //Gerencia a conexão reutilizável com o banco de dados.
import java.util.ArrayList; //Estrutura de dados que armazena listas redimensionáveis.
import java.util.List; // Interface que define uma lista ordenada de elementos.
import java.util.stream.Collectors; // Utilitário para transformar coleções de dados em listas ou strings.

// A classe ClienteDAO é responsável por interagir com a base de dados para operações relacionadas ao cliente
public class ClienteDAO {

    // Método para inserir um novo cliente no banco de dados
    public void inserirCliente(Cliente cliente) {
        // Definimos a query SQL para inserir um cliente, com placeholders "?" para os valores a serem inseridos
        String sql = "INSERT INTO Cliente (nome, cnpj, endereco, cidade, estado, cep, email, telefone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Usamos um bloco try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados corretamente
        try (Connection connection = DatabaseConnection.getConnection(); // Obtemos a conexão com o banco de dados
             PreparedStatement statement = connection.prepareStatement(sql)) { // Preparamos a query SQL para execução
             
            // Atribuímos os valores do objeto cliente aos placeholders da query
            statement.setString(1, cliente.getNome());     // Nome do cliente
            statement.setString(2, cliente.getCnpj());     // CNPJ do cliente
            statement.setString(3, cliente.getEndereco()); // Endereço do cliente
            statement.setString(4, cliente.getCidade());   // Cidade do cliente
            statement.setString(5, cliente.getEstado());   // Estado do cliente
            statement.setString(6, cliente.getCep());      // CEP do cliente
            statement.setString(7, cliente.getEmail());    // Email do cliente
            statement.setString(8, cliente.getTelefone()); // Telefone do cliente

            // Executamos a query e verificamos quantas linhas foram inseridas
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // Se uma ou mais linhas foram inseridas, indicamos sucesso
                System.out.println("Cliente inserido com sucesso!");
            }
        } catch (SQLException e) {
            // Em caso de erro, imprimimos o stack trace da exceção
            e.printStackTrace();
        }
    }
    
    // Método para listar todos os clientes do banco de dados
    public List<Cliente> listarClientes() {
        // Query SQL para selecionar todos os clientes
        String sql = "SELECT * FROM Cliente";
        // Criamos uma lista que armazenará os objetos Cliente
        List<Cliente> listaClientes = new ArrayList<>();
        
        // Bloco try-with-resources para garantir fechamento da conexão, Statement e ResultSet
        try (Connection connection = DatabaseConnection.getConnection();  // Obtemos a conexão
             Statement statement = connection.createStatement();          // Criamos o Statement para executar a query
             ResultSet resultSet = statement.executeQuery(sql)) {         // Executamos a query e armazenamos o resultado

            // Enquanto houver resultados no ResultSet, iteramos sobre eles
            while (resultSet.next()) {
                // Extraímos os dados de cada cliente a partir das colunas do banco
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String endereco = resultSet.getString("endereco");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String cep = resultSet.getString("cep");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");

                // Criamos um objeto Cliente com os dados extraídos
                Cliente cliente = new Cliente(id, nome, cnpj, endereco, cidade, estado, cep, email, telefone);
                // Adicionamos o cliente à lista
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            // Em caso de erro, imprimimos o stack trace da exceção
            e.printStackTrace();
        }

        // Retornamos a lista de clientes
        return listaClientes;
    }
    
    // Método para atualizar os dados de um cliente específico no banco de dados
    public void atualizarCliente(Cliente cliente) {
        // Query SQL para atualizar os dados de um cliente. Estamos atualizando vários campos de endereço e contato.
        String sql = "UPDATE Cliente SET endereco = ?, cidade = ?, estado = ?, cep = ?, email = ?, telefone = ? WHERE id = ?";
        
        // Bloco try-with-resources para garantir fechamento da conexão e PreparedStatement
        try (Connection connection = DatabaseConnection.getConnection(); // Obtemos a conexão com o banco de dados
             PreparedStatement statement = connection.prepareStatement(sql)) { // Preparamos a query SQL para execução
            
            // Atribuímos os valores do cliente aos placeholders da query
            statement.setString(1, cliente.getEndereco());  // Novo endereço
            statement.setString(2, cliente.getCidade());    // Nova cidade
            statement.setString(3, cliente.getEstado());    // Novo estado
            statement.setString(4, cliente.getCep());       // Novo CEP
            statement.setString(5, cliente.getEmail());     // Novo email
            statement.setString(6, cliente.getTelefone());  // Novo telefone
            statement.setInt(7, cliente.getId());           // ID do cliente para identificar qual será atualizado

            // Executamos a query de atualização e verificamos quantas linhas foram afetadas
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Se uma ou mais linhas foram atualizadas, indicamos sucesso
                System.out.println("Cliente atualizado com sucesso!");
            }
        } catch (SQLException e) {
            // Em caso de erro, imprimimos o stack trace da exceção
            e.printStackTrace();
        }
    }

    // Método para deletar vários clientes com base em uma lista de IDs
    public void deletarClientes(List<Integer> ids) {
        // Verificamos se a lista de IDs não está vazia
        if (ids == null || ids.isEmpty()) {
            System.out.println("Nenhum cliente para deletar.");
            return;  // Se a lista estiver vazia, saímos do método
        }

        // Criamos a query com placeholders "?" correspondentes ao número de IDs
        String sql = "DELETE FROM Cliente WHERE id IN (" + 
                 ids.stream().map(id -> "?").collect(Collectors.joining(", ")) + ")";

        // Bloco try-with-resources para garantir fechamento da conexão e PreparedStatement
        try (Connection connection = DatabaseConnection.getConnection(); // Obtemos a conexão
            PreparedStatement statement = connection.prepareStatement(sql)) { // Preparamos a query

            // Para cada ID na lista, associamos ao respectivo placeholder "?" na query
            for (int i = 0; i < ids.size(); i++) {
                statement.setInt(i + 1, ids.get(i));  // i+1 porque os índices do PreparedStatement começam em 1
            }

            // Executamos a query de exclusão e verificamos quantas linhas foram deletadas
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                // Se uma ou mais linhas foram deletadas, indicamos sucesso
                System.out.println("Clientes deletados com sucesso!");
            } else {
                // Caso nenhum cliente tenha sido encontrado para os IDs fornecidos
                System.out.println("Nenhum cliente encontrado com os IDs fornecidos.");
            }
        } catch (SQLException e) {
            // Em caso de erro, imprimimos o stack trace da exceção
            e.printStackTrace();
        }
    }

}
