package zkt.mybatis.generator.junit;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * 功能：
 * charles on 2018/9/19.
 */
public class MyFullyQualifiesJavaType extends JavaTypeResolverDefaultImpl {

	public MyFullyQualifiesJavaType() {
		super();
		super.typeMap.put(Types.BIT, new JdbcTypeInformation("BIT",new FullyQualifiedJavaType(Integer.class.getName())));
		super.typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT",new FullyQualifiedJavaType(Integer.class.getName())));
		super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT",new FullyQualifiedJavaType(Integer.class.getName())));
	}
}
