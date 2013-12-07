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
			application : 'consumer.application',
			registry : 'registry',
			interfaceName : 'com.sunivo.nutzdubbo.services.IPetService',
			version : '1.0.0'
		}
	}
}