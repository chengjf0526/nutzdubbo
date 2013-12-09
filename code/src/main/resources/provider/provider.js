/*
 * 
 */
var provider = {
	provider.application : {
		type : 'com.alibaba.dubbo.config.ApplicationConfig',
		fields : {
			name : 'provider'
		}
	},
	protocol : {
		type : 'com.alibaba.dubbo.config.ProtocolConfig',
		fields : {
			name : 'dubbo',
			port : 9090,
			threads : 200
		}
	},
	impl : {
		type : 'com.sunivo.nutzdubbo.services.impl.PetServiceImpl',
		fields : {}
	},
	myImpl : {
		type : 'com.sunivo.nutzdubbo.services.impl.MyPetServiceImpl',
		fields : {}
	},
	service : {
		type : 'com.alibaba.dubbo.config.ServiceConfig',
		fields : {
			application : {
				refer : 'provider.application'
			},
			registry : {
				refer : 'registry'
			},
			protocol : {
				refer : 'protocol'
			},
			interfaceName : 'com.sunivo.nutzdubbo.services.IPetService',
			ref : {
				refer : 'impl'
			},
			version : '1.0.0'
		}
	},
	myService : {
		type : 'com.alibaba.dubbo.config.ServiceConfig',
		fields : {
			application : {
				refer : 'provider.application'
			},
			registry : {
				refer : 'registry'
			},
			protocol : {
				refer : 'protocol'
			},
			interfaceName : 'com.sunivo.nutzdubbo.services.IPetService',
			ref : {
				refer : 'myImpl'
			},
			version : '1.0.1'
		}
	}
}