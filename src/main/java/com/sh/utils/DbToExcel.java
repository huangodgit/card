package com.sh.utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbToExcel<T> {
	/**
	 * �÷���ʵ�ֽ����ӱ��е����ݵ����Ӧ�����ݿ���
	 * 
	 * @param excelpath   �����ӱ�·��
	 * @param table       �����ݿ����ݱ���
	 * @param fieldList   �����ݿ��ֶ��������ڲ������ݿ��У����ֶ���Ϣ�������ö��ż����
	 * @param columnCount ��Ҫ��ӵ��ֶεĸ���
	 * @throws Exception
	 */
	public static void excelToDb(final String excelpath, final String table, final String fieldList, final int columnCount) throws Exception {
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory =null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.doWork(new Work() {
				@Override
				public void execute(Connection conn) throws SQLException {
					PreparedStatement ps = null;
					Workbook workbook = null;
					Sheet sheet = null;

					String sql = "insert into " + table + " " + fieldList + "  values (";
					for (int i = 1; i < columnCount; i++) {
						sql += "?,";
					}
					sql += "?) ";

					ps = conn.prepareStatement(sql);
			
					try {
						workbook = Workbook.getWorkbook(new File(excelpath));
					} catch (BiffException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					sheet = workbook.getSheet(0);
					int r = sheet.getRows();
					for (int i = 1; i < r; i++) {
						for (int j = 0; j < columnCount; j++)
							ps.setString(j + 1, sheet.getCell(j, i).getContents());
						ps.addBatch();
					}
					ps.executeBatch();
					workbook.close();
				}
			});
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * �÷���ʵ�ֽ����ݿ��е�ĳ���ݱ������γɵ��ӱ�Excel
	 * 
	 * @param table     :���ݱ�����
	 * @param fieldList �����ݱ��ֶ����������ַ����������δ��
	 * @param titles    �����γɵĵ��ӱ��ͷ�ֶ���Ϣ�������ַ���������
	 * @param condition ����ѯ���ݿ��������������������ļ�¼��ŵ����ӱ���
	 * @param order     �������ֶ�������ʽ��asc\desc��
	 * @param file      �����ӱ�����
	 * @throws Exception
	 */
	public static void dBToExcel(final String table, final String[] fieldList, final String[] titles, final String condition, final String order,
								 final String file) throws Exception {
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.doWork(new Work() {
				@Override
				public void execute(Connection conn) throws SQLException {
					String flist = "";
					int flen = fieldList.length;
					for (int i = 0; i < flen - 1; i++) {
						flist += fieldList[i] + ",";
					}
					flist += fieldList[fieldList.length - 1];
					String sql = "select " + flist + " from " + table + "  where 1=1 ";
					if (condition != null && !condition.equals("")) {
						sql = sql + " and " + condition;
					}
					if (order != null && !order.equals("")) {
						sql = sql + " order by " + order;
					}
					PreparedStatement pstm = conn.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();

					WritableWorkbook wwb = null;
					WritableSheet ws = null;
					try {
						wwb = Workbook.createWorkbook(new File(file));
					} catch (IOException e) {
						e.printStackTrace();
					}
					ws = wwb.createSheet("sheet1", 0);

					for (int i = 0; i < flen; i++) {
						try {
							ws.addCell(new Label(i, 0, titles[i]));
						} catch (RowsExceededException e) {
							e.printStackTrace();
						} catch (WriteException e) {
							e.printStackTrace();
						}
					}

					int count = 1;
					while (rs.next()) {
						for (int j = 0; j < flen; j++) {
							try {
								ws.addCell(new Label(j, count, rs.getString(j + 1)));
							} catch (RowsExceededException e) {
								e.printStackTrace();
							} catch (WriteException e) {
								e.printStackTrace();
							}
						}
						count++;
					}
					try {
						wwb.write();
					} catch (IOException e) {
						e.printStackTrace();
					}

					if (wwb != null) {
						try {
							wwb.close();
						} catch (WriteException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
	}
}
