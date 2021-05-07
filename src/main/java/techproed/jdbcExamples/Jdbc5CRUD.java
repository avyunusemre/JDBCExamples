package techproed.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Jdbc5CRUD {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String yol = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection(yol, "avyunusemre", "574563");

		Statement st = con.createStatement();
		
		/*=======================================================================
		  ORNEK1: urunler adinda bir tablo olusturalim id(NUMBER(3), 
		  isim VARCHAR2(10) fiyat NUMBER(7,2) 
		========================================================================*/ 
		/*
		String createQuery = "CREATE TABLE urunler("
							+ " id NUMBER(3),"
							+ " isim VARCHAR2(10),"
							+ " fiyat NUMBER(7,2))";
		
		st.execute(createQuery);
		System.out.println("urunler tablosu olusturuldu...");
		*/
		
		/*=======================================================================
		  ORNEK2: urunler tablosuna asagidaki kayitlari toplu bir sekilde ekleyelim
		========================================================================*/ 
		// Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir. 
		// PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari icin) bir yontemdir. 
		// Bunun icin; 
		// 	1) Veri girisine uygun bir POJO(Plain Old Java Object) sinifi olusturulur.
		// 	2) POJO Class nesnelerini saklayacak bir collection olusturulur
		// 	3) bir dongu ile kayitlar eklenir. 
		
		List<Urun> urunList = new ArrayList<>();
		urunList.add(new Urun(101, "Laptop", 6500));
		urunList.add(new Urun(102, "PC", 4500));
		urunList.add(new Urun(103, "Telefon", 4500));
		urunList.add(new Urun(104, "Anakart", 1500));
		urunList.add(new Urun(105, "Klavye", 200));
		urunList.add(new Urun(106, "Fare", 100));
		
		String insertQuery = "INSERT INTO urunler VALUES(?,?,?)";
		PreparedStatement pst = con.prepareStatement(insertQuery);
		
		
//		for(Urun each : urunList) {
//			pst.setDouble(1, each.getId());
//			pst.setString(2, each.getIsim());
//			pst.setDouble(3, each.getFiyat());
//			
//			pst.addBatch();
//		}
//		
//		int[] sonuc = pst.executeBatch();
//		System.out.println(sonuc.length + " kayıt eklendi...");
		
		
		/*=======================================================================
		  ORNEK3: urunler tablosundaki PC'nin fiyatini %10 zam yapiniz
		========================================================================*/
		String updateQuery = "UPDATE urunler"
							+ " SET fiyat = fiyat * 1.1"
							+ " WHERE isim = 'PC'";
		
//		int s1 = st.executeUpdate(updateQuery);
//		System.out.println(s1 + " satır guncellendi...");
		
		/*=======================================================================
		  ORNEK4: urunler tablosuna 107, Monitor, 1250 sekilnde bir kayit ekleyiniz
		========================================================================*/
		String insertQuery2 = "INSERT INTO urunler VALUES(107, 'Monitor', 1250)";
		
//		int s2 = st.executeUpdate(insertQuery2);
//		System.out.println(s2 + " kayıt eklendi...");
		
		/*=======================================================================
		  ORNEK5: urunler tablosundaki Klavyeyi siliniz.
		========================================================================*/
		String deleteQuery = "DELETE FROM urunler"
								+ " WHERE isim = 'Klavye'";
			
//		int s3 = st.executeUpdate(deleteQuery);
//		System.out.println(s3 + " kayıt silindi...");
		
		/*=======================================================================
		  ORNEK6: urunler tablosuna Marka adinda ve Default degeri ASUS olan yeni 
		  bir sutun ekleyiniz.
		========================================================================*/
		String alterQuery = "ALTER TABLE urunler"
							+ " ADD marka VARCHAR2(10) DEFAULT 'ASUS'";
		
//		int s4 = st.executeUpdate(alterQuery);
//		System.out.println(s4 + " sutun eklendi...");
		
		/*=======================================================================
		  ORNEK7: urunler tablosundaki kayitlari sorgulayiniz.
		========================================================================*/ 
		String selectQuery = "SELECT * FROM urunler";
		
		ResultSet rs = st.executeQuery(selectQuery);
		
		while(rs.next()) {
			System.out.println("ürün id : " + rs.getInt(1) + " ürün isim : " + rs.getString(2) + 
								" \tfiyat : " + rs.getDouble(3) + " marka : " + rs.getString(4));
		}
	
		
		/*=======================================================================
		  ORNEK8: urunler tablosunu siliniz.
		========================================================================*/ 
		String dropQuery = "DROP TABLE urunler";
		
//		st.execute(dropQuery);
//		System.out.println("ürünler tablosu silindi...");
		
		
		
	}

}
