package Blockchain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;

import com.user.DBconn;

public class ChainConsensus {

	public static int block1, block2, block3, block4;
	public static String T_id1, T_id2, T_id3, T_id4;
	public static int Sys2 = 0, Sys3 = 0, Sys4 = 0, Sys1 = 0, num = 0;
	public static Map<Integer, Integer> blocklist = new HashMap<Integer, Integer>();

	public static void ProofOfWork(String data) {
		try {
			// mining
			for (int i = 1; i < 5; i++) {
				String blockchainnumber = String.valueOf(i);
				String db = "blockchain" + i;
				int msg = Blockchain1.BlockchaindataMining(data, db,
						blockchainnumber);
				blocklist.put(i, msg);
			}
			System.out.println(blocklist);
			// majority count
			Sys1 = 0;
			Sys2 = 0;
			Sys3 = 0;
			Sys4 = 0;
			for (Entry<Integer, Integer> entry : blocklist.entrySet()) {
				int key = entry.getKey();
				int values = entry.getValue();
				System.out.println("Key=>"+key+"V=>"+values);
				if (values == 1) {
					if (key == 1) {
						Sys1 = values;
					} else if (key == 2) {
						Sys2 = values;
					} else if (key == 3) {
						Sys3 = values;
					} else if (key == 4) {
						Sys4 = values;
					} else {
						Sys1 = 0;
						Sys2 = 0;
						Sys3 = 0;
						Sys4 = 0;
					}
				}
			}
			System.out.println("Block Chain First=>" + Sys1);
			System.out.println("Block Chain Second=>" + Sys2);
			System.out.println("Block Chain Three=>" + Sys3);
			System.out.println("Block Chain Four=>" + Sys4);

			Connection con1 = (Connection) DBconn.conn1();
			Connection con2 = (Connection) DBconn.conn2();
			Connection con3 = (Connection) DBconn.conn3();
			Connection con4 = (Connection) DBconn.conn4();
			Statement st1, st2, st3, st4;
			Statement st01, st02, st03, st04;
			Statement st001, st002, st003, st004;
			ResultSet rs1, rs2, rs3, rs4;
			st1 = con1.createStatement();
			st2 = con2.createStatement();
			st3 = con3.createStatement();
			st4 = con4.createStatement();
			st01 = con1.createStatement();
			st02 = con2.createStatement();
			st03 = con3.createStatement();
			st04 = con4.createStatement();
			st001 = con1.createStatement();
			st002 = con2.createStatement();
			st003 = con3.createStatement();
			st004 = con4.createStatement();
			//recovery data
			if ((Sys1 == 0 && Sys2 == 0 && Sys3 == 0 && Sys4 == 0)) {
				System.out.println("All Block Valid");
			} else if (Sys1 == 1) {
				st001.executeUpdate("TRUNCATE transhash");
				rs1 = st2.executeQuery("select * from transhash");
				while (rs1.next()) {
					String PlainData = rs1.getString("PlainData");
					String BlocKData = rs1.getString("BlocKData");
					String PreviousHash = rs1.getString("PreviousHash");
					String HashBlock = rs1.getString("HashBlock");
					String Current_Times = rs1.getString("Current_Times");
					st01.executeUpdate("INSERT INTO transhash (PlainData, BlocKData, PreviousHash,HashBlock,Current_Times) values('"
							+ PlainData
							+ "','"
							+ BlocKData
							+ "','"
							+ PreviousHash
							+ "','"
							+ HashBlock
							+ "','"
							+ Current_Times + "')");

				}
				// st1.executeUpdate("update tblblockchain set PreviousHash='"+block2+"' where TransactionID='"+T_id1+"'");
				System.out.println("Block One Not valid");

			} else if (Sys2 == 1) {
				st002.executeUpdate("TRUNCATE transhash");
				rs2 = st3.executeQuery("select * from transhash");
				while (rs2.next()) {
					String PlainData = rs2.getString("PlainData");
					String BlocKData = rs2.getString("BlocKData");
					String PreviousHash = rs2.getString("PreviousHash");
					String HashBlock = rs2.getString("HashBlock");
					String Current_Times = rs2.getString("Current_Times");
					st02.executeUpdate("INSERT INTO transhash (PlainData, BlocKData, PreviousHash,HashBlock,Current_Times) values('"
							+ PlainData
							+ "','"
							+ BlocKData
							+ "','"
							+ PreviousHash
							+ "','"
							+ HashBlock
							+ "','"
							+ Current_Times + "')");

				}

				System.out.println("Block Two Not valid");

			} else if (Sys3 == 1) {
				st003.executeUpdate("TRUNCATE transhash");
				rs3 = st4.executeQuery("select * from transhash");
				while (rs3.next()) {
					String PlainData = rs3.getString("PlainData");
					String BlocKData = rs3.getString("BlocKData");
					String PreviousHash = rs3.getString("PreviousHash");
					String HashBlock = rs3.getString("HashBlock");
					String Current_Times = rs3.getString("Current_Times");
					st03.executeUpdate("INSERT INTO transhash (PlainData, BlocKData, PreviousHash,HashBlock,Current_Times) values('"
							+ PlainData
							+ "','"
							+ BlocKData
							+ "','"
							+ PreviousHash
							+ "','"
							+ HashBlock
							+ "','"
							+ Current_Times + "')");

				}

				System.out.println("Block Three Not valid");
			} else if (Sys4 == 1) {

				st004.executeUpdate("TRUNCATE transhash");
				rs4 = st1.executeQuery("select * from transhash");
				while (rs4.next()) {
					String PlainData = rs4.getString("PlainData");
					String BlocKData = rs4.getString("BlocKData");
					String PreviousHash = rs4.getString("PreviousHash");
					String HashBlock = rs4.getString("HashBlock");
					String Current_Times = rs4.getString("Current_Times");
					st04.executeUpdate("INSERT INTO transhash (PlainData, BlocKData, PreviousHash,HashBlock,Current_Times) values('"
							+ PlainData
							+ "','"
							+ BlocKData
							+ "','"
							+ PreviousHash
							+ "','"
							+ HashBlock
							+ "','"
							+ Current_Times + "')");

				}
				System.out.println("Block Four Not valid");
			} else {
				System.out.println("All Block Not Valid");
			}
			//new transaction
			Connection conn = (Connection) DBconn.conn1();

			Statement stat = (Statement) conn.createStatement();

			stat.executeUpdate("insert into transhash(PlainData,BlocKData,PreviousHash,HashBlock,Current_Times) values ('"
					+ data
					+ "','"
					+ Block.hash
					+ "','"
					+ DBconn.PrevHash1
					+ "','" + Block.timeStamp + "','" + Block.nonce + "')");
			Connection conn2 = (Connection) DBconn.conn2();
			Statement stat2 = (Statement) conn2.createStatement();
			stat2.executeUpdate("insert into transhash(PlainData,BlocKData,PreviousHash,HashBlock,Current_Times) values ('"
					+ data
					+ "','"
					+ Block.hash
					+ "','"
					+ DBconn.PrevHash1
					+ "','" + Block.timeStamp + "','" + Block.nonce + "')");
			Connection conn3 = (Connection) DBconn.conn3();
			Statement stat3 = (Statement) conn3.createStatement();
			stat3.executeUpdate("insert into transhash(PlainData,BlocKData,PreviousHash,HashBlock,Current_Times) values ('"
					+ data
					+ "','"
					+ Block.hash
					+ "','"
					+ DBconn.PrevHash1
					+ "','" + Block.timeStamp + "','" + Block.nonce + "')");
			Connection conn4 = (Connection) DBconn.conn4();
			Statement stat4 = (Statement) conn4.createStatement();
			stat4.executeUpdate("insert into transhash(PlainData,BlocKData,PreviousHash,HashBlock,Current_Times) values ('"
					+ data
					+ "','"
					+ Block.hash
					+ "','"
					+ DBconn.PrevHash1
					+ "','" + Block.timeStamp + "','" + Block.nonce + "')");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		ProofOfWork("jitu");
		// Consensus("String plaindata");
	}
}
