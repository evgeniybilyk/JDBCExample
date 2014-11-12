import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
    public static void main(String []args){
        String user = "student";//Логин пользователя
        String password = "P@ssw0rd";//Пароль пользователя
        String url = "jdbc:mysql://217.146.253.33/tram_groundhog_day";//URL адрес
        //String driver = "oracle.jdbc.driver.OracleDriver";//Имя драйвера
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Connection c = null;//Соединение с БД
        try{
            c = DriverManager.getConnection(url, user, password);//Установка соединения с БД
            Statement st = c.createStatement();//Готовим запрос
            ResultSet rs = st.executeQuery("select * from tram_groundhog_day_event;");//Выполняем запрос к БД, результат в переменной rs
            while(rs.next()){
                System.out.println(rs.getString("id"));//Последовательно для каждой строки выводим значение из колонки ColumnName
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            //Обязательно необходимо закрыть соединение
            try {
                if(c != null)
                    c.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}