/*
 * 
 */
var service = {
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