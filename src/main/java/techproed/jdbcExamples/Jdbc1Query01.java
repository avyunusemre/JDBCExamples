package techproed.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc1Query01 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//1-Ilgili driveri yukle
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2-Baglanti olustur
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain","avyunusemre","574563");
		
		//3- SQL komutlari icin bir Statement objesi olusturuyoruz
		Statement st = con.createStatement();
		
		//4- SQL ifadelerini yazıp onu ResultSet nesnesi icerisine depoluyoruz
		// personel_id 7369 olan personelin adını sorgulayınız.
		ResultSet isim = st.executeQuery("SELECT personel_isim, maas FROM personel WHERE personel_id = 7369");
		
		//5- Sonucları aldık ve isledik
		while(isim.next()) {
			System.out.println("ID : 3769 - Personel isim  : " + isim.getString("personel_isim"));
			System.out.println("ID : 3769 - Personel maaş  : " + isim.getDouble(2)); // ismi ve maası aldığımız icin 2 tane column var.
		}
		
		//6- Olusturulan nesneleri bellekten kaldıralım.
		isim.close();
		st.close();
		con.close();
	}
}
