import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class JDBCExample {
    public static void main(String []args){
        // String phoneOwnName = "q%' Delete from phonebook where phonebook.last_name like '%Stepur%'; -- ";
        String phoneOwnName = "";
        String user = "student";//Логин пользователя
        String password = "P@ssw0rd";//Пароль пользователя
        String url = "jdbc:mysql://217.146.253.33/tram_groundhog_day";//URL адрес
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
            String query = "select phone.added, phonecall.dt, phonebook.last_name, phonebook.id, " +
                    "phone.number, phonecall.phone_id, phonecall.description " +
                    "from phonebook, phone, phonecall where phonebook.last_name like '%" +
                    phoneOwnName + "%' and phonebook.id = phone.phonebook_id " +
                    "and phonebook.id = phonecall.phone_id;";
            System.out.println(query);
            ResultSet rsPhone = st.executeQuery(query);//Выполняем запрос к БД, результат в переменной rsPhone
            while (rsPhone.next()){
                Calendar date = Calendar.getInstance();
                date.setTime(rsPhone.getTime("added"));
                //System.out.println(date.get(Calendar.HOUR_OF_DAY));
                if (date.get(Calendar.HOUR_OF_DAY) < 16)
                    System.out.println("evening " + rsPhone.getString("added")+"|"+
                            rsPhone.getString("last_name") + "|"+
                            rsPhone.getString("phonecall.dt")+                        "  |" +
                            rsPhone.getString("number") +"         |         "+
                            rsPhone.getString("phonecall.description"));
                else
                    System.out.println("not evening");
            }
 
            /*while(rsPhone.next()){
                for (int i = 0; i < rsPhone.getMetaData().getColumnCount() ; i++) {
                    String columnName = rsPhone.getMetaData().getColumnName();
                    System.out.println(columnName);
                }
               // System.out.println(rsPhone.getString("Login"));//Последовательно для каждой строки выводим значение из колонки ColumnName
            }*/
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