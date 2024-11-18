package seb.project.util;

// Importa classes necessárias do pacote java.sql
import java.sql.Connection;  // Representa a conexão com o banco de dados
import java.sql.DriverManager;  // Gerencia os drivers e estabelece conexões com o banco
import java.sql.SQLException;  // Classe para tratar erros relacionados a SQL e ao banco de dados

/**
 * Classe utilitária para gerenciar a conexão com o banco de dados MySQL.
 * Ela oferece um método estático para estabelecer a conexão e gerenciar erros relacionados a isso.
 */
public class DatabaseConnection {

    // URL do banco de dados MySQL. Formato: jdbc:mysql://[host]:[porta]/[nome-do-banco]
    private static final String URL = "jdbc:mysql://localhost:3306/seb_project";
    
    // Nome do usuário que acessa o banco de dados (geralmente definido na instalação do MySQL)
    private static final String USER = "root";
    
    // Senha correspondente ao usuário do banco de dados
    private static final String PASSWORD = "senha";

    /**
     * Método estático que retorna uma conexão com o banco de dados.
     * @return Connection - uma conexão ativa com o banco de dados.
     * @throws SQLException - Lança uma exceção se houver problemas ao estabelecer a conexão.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Tenta carregar o driver JDBC do MySQL.
            // O driver JDBC é necessário para permitir a comunicação entre o Java e o banco de dados MySQL.
            // Class.forName() tenta carregar a classe do driver usando o nome da classe como string.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Se o driver for carregado com sucesso, tenta estabelecer uma conexão com o banco.
            // DriverManager.getConnection() usa a URL do banco, o usuário e a senha para abrir a conexão.
            // Se os dados estiverem corretos e o banco de dados estiver acessível, uma conexão é retornada.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Se o driver JDBC não for encontrado, esta exceção será capturada.
            // Imprime no console a pilha de erros para ajudar na depuração.
            e.printStackTrace();

            // Lança uma exceção de SQL personalizada, indicando que o driver não foi encontrado.
            // Isso ajuda o programa que chamou este método a saber exatamente o que deu errado.
            throw new SQLException("Driver JDBC não encontrado.");
        }
    }
}

