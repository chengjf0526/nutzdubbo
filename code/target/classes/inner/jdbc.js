var jdbc = {
	// 读取配置文件
	config : {
		type : "org.nutz.ioc.impl.PropertiesProxy",
		fields : {
			paths : [ "jdbc.properties" ]
		}
	},
	// 数据源
	dataSource : {
		type : "org.apache.commons.dbcp.BasicDataSource",
		events : {
			depose : "close"
		},
		fields : {
			driverClassName : {
				java : "$config.get('db.driver')"
			},
			url : {
				java : "$config.get('db.url')"
			},
			username : {
				java : "$config.get('db.username')"
			},
			password : {
				java : "$config.get('db.password')"
			},
			initialSize : 10,
			maxActive : 100,
			testOnReturn : true,
			// validationQueryTimeout : 5,
			validationQuery : "select 1"
		}
	},
	// Dao
	dao : {
		type : 'org.nutz.dao.impl.NutDao',
		args : [ {
			refer : "dataSource"
		} ]
	}
};