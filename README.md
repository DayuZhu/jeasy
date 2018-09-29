# jeasy-mybatis-generator
目前jeasy只提供mybatis-generator扩展,扩展分页插件，模板插件等

分页插件使用
```
   <plugin type="xin.jeasy.mybatis.generator.plugin.LimitForMysqlPagePlugin"/>
```
分页插件LimitForMysqlPagePlugin可以生成形如如下的SQL，符合sql规范
```
SELECT
	t.*
FROM
	(
		SELECT
			id
		FROM
			member t
		WHERE
			thread_id = 771025
		AND deleted = 0
		ORDER BY
			gmt_create ASC
		LIMIT 0,
		15
	) a,
	t
WHERE
	a.id = t.id
  ```
