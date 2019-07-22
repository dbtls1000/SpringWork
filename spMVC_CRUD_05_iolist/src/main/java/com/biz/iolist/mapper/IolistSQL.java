package com.biz.iolist.mapper;

import org.apache.ibatis.jdbc.SQL;

public class IolistSQL {
	public String iolist_insert_sql() {
		SQL sql = new SQL();
		sql.INSERT_INTO("tbl_iolist");
		sql.INTO_COLUMNS("io_seq").INTO_VALUES("#{SEQ_IOLIST.NEXTVAL,jdbcType=VARCHAR}");
		sql.INTO_COLUMNS("io_date").INTO_VALUES("#{io_date,jdbcType=VARCHAR}");
		sql.INTO_COLUMNS("io_pcode").INTO_VALUES("#{io_pcdoe,jdbcType=VARCHAR}");
		sql.INTO_COLUMNS("io_ccode").INTO_VALUES("#{io_ccode,jdbcType=VARCHAR}");
		sql.INTO_COLUMNS("io_inout").INTO_VALUES("#{io_inout,jdbcType=VARCHAR}");
		sql.INTO_COLUMNS("io_amt").INTO_VALUES("#{io_amt,jdbcType=INTEGER}");
		sql.INTO_COLUMNS("io_price").INTO_VALUES("#{io_price,jdbcType=INTEGER}");
		sql.INTO_COLUMNS("io_total").INTO_VALUES("#{io_total,jdbcType=INTEGER}");
		return sql.toString();
	}

	public String iolist_update_sql() {
		//BUILD패턴 방식의 코드
		SQL sql = new SQL()
			.UPDATE("tbl_iolist")
			.SET("io_seq = #{io_seq}")
			.SET("io_date = #{io_date}")
			.SET("io_pcode = #{io_pcdoe}")
			.SET("io_ccode = #{io_ccode}")
			.SET("io_inout = #{io_inout}")
			.SET("io_amt = #{io_amt}")
			.SET("io_price = #{io_price}")
			.SET("io_total = #{io_total}");
		
		return sql.toString();
	}
}
