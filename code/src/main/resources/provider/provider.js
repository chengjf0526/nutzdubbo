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
	baseService :{
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
			}
		}
	},
	service.1 : {
		parent : 'baseService',
		fields : {
			interfaceName : 'com.sunivo.nutzdubbo.services.IPetService',
			ref : {
				refer:'petService'
			},
			version : '1.0.0'
		}
	},
	service.2 : {
		parent : 'baseService',
		fields : {
			interfaceName : 'com.sunivo.nutzdubbo.services.IPetService',
			ref : {
				refer : 'myPetService'
			},
			version : '1.0.1'
		}
	},
	service.3 : {
		parent : 'baseService',
		fields : {
			interfaceName : 'com.sunivo.nutzdubbo.services.IHelloService',
			ref : {
				refer : 'helloService'
			},
			version : '1.0.0'
		}
	}
}