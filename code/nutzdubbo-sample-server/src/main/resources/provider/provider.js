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
	}
}