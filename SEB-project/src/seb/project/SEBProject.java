package seb.project;

public class SEBProject {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null){
                System.out.println("Conexão bem-sucedida!");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
