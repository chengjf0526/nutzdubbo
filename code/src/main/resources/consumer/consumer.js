var consumer = {
	consumer.application : {
		type : 'com.alibaba.dubbo.config.ApplicationConfig',
		fields : {
			name : 'consumer'
		}
	},
	reference :{
		type :'com.alibaba.dubbo.config.ReferenceConfig',
		singleton : false,
		fields :{
			application : {
				refer : 'consumer.application'
			},
			registry : {
				refer : 'registry',
			}
		}
	}
}