/*
 * 
 */
var provider = {
	application : {
		type : 'com.alibaba.dubbo.config.ApplicationConfig',
		fields : {
			name : 'provider'
		}
	},
	protocol : {
		type : 'com.alibaba.dubbo.config.ProtocolConfig',
		fields : {
			name : 'dubbo',
			port : 9999,
			threads : 200
		}
	},
	impl : {
		type : 'com.sunivo.nutzdubbo.services.impl.PetServiceImpl',
		fields : {}
	},
	service : {
		type : 'com.alibaba.dubbo.config.ServiceConfig',
		fields : {
			application : {
				refer : 'application'
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
	}
}