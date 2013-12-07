var provider = {
	application : {
		type : 'com.alibaba.dubbo.rpc.config.ApplicationConfig',
		name : 'ABC'
	},
	registry : {
		type : 'com.alibaba.dubbo.rpc.config.RegistryConfig',
		address : '10.20.130.230:9090',
		username : 'aaa',
		password : 'bbb'
	},
	protocol : {
		type : 'com.alibaba.dubbo.config.ProtocolConfig',
		name : 'dubbo',
		port : 12345,
		threads : 200
	},
	impl : {
		type : 'com.sunivo.nutzdubbo.services.IPetService',
	},
	service : {
		type : 'com.alibaba.dubbo.rpc.config.ServiceConfig',
		application : {
			refer : 'application'
		},
		registry : {
			refer : 'registry'
		},
		protocol : {
			refer : 'protocol'
		},
		ref : {
			refer : 'impl'
		},
		version : '1.0.0'
	}
}