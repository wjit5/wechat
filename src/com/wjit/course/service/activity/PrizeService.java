package com.wjit.course.service.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wjit.course.message.pojo.Picture;
import com.wjit.course.message.pojo.Prize;
import com.wjit.course.util.main.MySQLUtil;

/**
 * 奖品操作类
 * @author WANGJIAN
 * @date 2015-12-24
 */
public class PrizeService {
	
	/**
	 * 获取所有的prize
	 * 
	 * @param priid
	 * @return
	 *
	 */
	public static List<Prize> getAllPrize(String priid){
		List<Prize> priList=new ArrayList<Prize>();
		String sql="select * from prize where pri_id='"+priid+"'";
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Prize prize=new Prize();
				prize.setId(rs.getInt("id"));
				prize.setPri_id(rs.getString("pri_id"));
				prize.setPri_name(rs.getString("pri_name"));
				prize.setPri_amount(rs.getInt("pri_amount"));
				prize.setPri_des(rs.getString("pri_des"));
				prize.setPri_flag(rs.getString("pri_flag"));
				prize.setPri_price(rs.getString("pri_price"));
				prize.setPri_value(rs.getString("pri_value"));
				priList.add(prize);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		 return priList;
	}
}
