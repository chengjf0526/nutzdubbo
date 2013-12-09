var consumer = {
	consumer.application : {
		type : 'com.alibaba.dubbo.config.ApplicationConfig',
		fields : {
			name : 'consumer'
		}
	},
	reference :{
		type :'com.alibaba.dubbo.config.ReferenceConfig',
		fields :{
			application : {
				refer : 'consumer.application'
			},
			registry : {
				refer : 'registry',
			}
			interfaceName : 'com.sunivo.nutzdubbo.services.IPetService'
		}
	}
}