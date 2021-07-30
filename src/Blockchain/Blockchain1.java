package Blockchain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.JavaX;

import com.user.DBconn;

public class Blockchain1 {
	public static ArrayList<Block> blockchain1 = new ArrayList<Block>();
	public static int difficulty = 1;
	public static String Chash = "";

	public static int BlockchaindataMining(String data,String databasename,String blockchainnumber) {
		try {

			String PrevHash = GetPreviousHash1(databasename);
			DBconn.PrevHash1 = PrevHash;

			blockchain1 = GetChain1(databasename);
			// if genasis
			int size = blockchain1.size();
			if (PrevHash == "0") {
				blockchain1.add(new Block(data, "0"));
			} else {
				blockchain1.add(new Block(data, PrevHash));
			}
			System.out.println("Mining Start=>"+blockchainnumber);
			blockchain1.get(size).mineBlock1(difficulty);
			if (isChainValid1(databasename)) {
				System.out.println("BlockChain=>"+blockchainnumber);

				DBconn.blockchain1msg = 0;
			} else {
				DBconn.blockchain1msg = 1;
				System.out.println("Blockchain is Invalid=>"+blockchainnumber);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return DBconn.blockchain1msg;
	}

	public static ArrayList<Block> GetChain1(String databasename) throws Exception {
		String previous = "0";
		ArrayList<Block> wholeTransactionChain = new ArrayList<Block>();
		Connection con = (Connection) DBconn.connblockchain(databasename);
		Statement stat = (Statement) con.createStatement();
		stat.executeQuery("select * from transhash");
		ResultSet rs = stat.getResultSet();
		int i = 0;
		while (rs.next()) {
			if (i == 0)
				wholeTransactionChain.add(new Block(rs.getString(2), "0"));
			else {
				wholeTransactionChain.add(new Block(rs.getString(2), previous));
			}
			previous = rs.getString(3);
			i++;
		}
		return wholeTransactionChain;
	}

	public static ArrayList<String> GetChainConsensus1(String databasename) throws Exception {
		ArrayList<String> wholeTransactionChain = new ArrayList<>();
		JavaX.initTransaction();
		Connection con = (Connection) DBconn.connblockchain(databasename);
		Statement stat = (Statement) con.createStatement();
		stat.executeQuery("select * from transhash");
		ResultSet rs = stat.getResultSet();
		while (rs.next()) {
			wholeTransactionChain.add(rs.getString(2) + "," + rs.getString(3)
					+ "," + rs.getString(4));
		}
		return wholeTransactionChain;
	}

	// its give the state it is a genasis block or not
	public static String GetPreviousHash1(String databasename) {
		String finalhash = "0";
		try {

			Connection con = (Connection) DBconn.connblockchain(databasename);
			Statement stat = (Statement) con.createStatement();
			stat.executeQuery("select * from transhash");
			ResultSet rs = stat.getResultSet();
			while (rs.next()) {
				finalhash = rs.getString(3);
			}
		} catch (Exception ex) {
		}
		return finalhash;
	}

	// This is the strategy of consus algorithm, and its applicable for n nodes
	public static Boolean isChainValid1(String databasename) throws Exception {
		ArrayList<String> CompleteList = new ArrayList<>();
		int flag = 0;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		CompleteList = GetChainConsensus1(databasename);
		for (int startpoint = 0; startpoint < CompleteList.size(); startpoint++) {
			String[] parts = CompleteList.get(startpoint).toString().split(",");
			String Previsblk = parts[2];
			//System.out.println("\t Data=>"+CompleteList.get(startpoint));
//			System.out.println(Previsblk+"\t Chash=>" + Chash);
			if (startpoint == 0)
				Chash = parts[1];
			else {
				if (!Previsblk.equals(Chash)) {
					System.out.println("Previsblk=>" + Previsblk);
					flag = 1;
					//break;
					
				}
			}

			if (!parts[1].substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				flag = 1;
				break;
			}
			Chash = parts[1];
		}
		if (flag == 1)
			return false;
		else
			return true;
	}
}