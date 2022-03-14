db = db.getSiblingDB('informacoescadastrais-db');

//create user to connect db
db.createUser({
  user: 'informacoescadastrais',
  pwd: 'informacoescadastrais123',
  roles: [
	{
		role: "readWrite",
		db: "informacoescadastrais-db"
	}
  ]
});

//create user associado
oid_userTypeAssociado = ObjectId();
oid_userAssociado = ObjectId();

db.userType.save( {
	_id: oid_userTypeAssociado,
    description : "associado"
});

db.user.save( {
	_id: oid_userAssociado,
    username : "associado" , 
    password : "associado123",
	userType : {
		$ref: "userType",
		$id: oid_userTypeAssociado
	}	
});

db.associate.save( {
	cpf: "34056471851",
	birthDate: "05/10/1985",
	user: {
		$ref: "user",
		$id: oid_userAssociado
	}	
});	

//create user conveniado

oid_userTypeConveniado = ObjectId();
oid_userConveniado = ObjectId();

db.userType.save( {
	_id: oid_userTypeConveniado,
    description : "conveniado"
});

db.user.save( {
	_id: oid_userConveniado,
    username : "conveniado" , 
    password : "conveniado123",
	userType : {
		$ref: "userType",
		$id: oid_userTypeConveniado
	}	
});

db.convened.save( {
	user: {
		$ref: "user",
		$id: oid_userConveniado
	}
});

//associate status

db.status.save( {
	description : "Ativo"
});

db.status.save( {
	description : "Suspenso"
});

db.status.save( {
	description : "Inativo"
});


//age group

ageGroup0_18 = ObjectId();
db.ageGroup.save( {
	_id: ageGroup0_18,
	description : "0 a 18 anos",
	initialAge: "0",
	finalAge: "18"
});

ageGroup19_23 = ObjectId();
db.ageGroup.save( {
	_id: ageGroup19_23,
	description : "19 a 23 anos",
	initialAge: "19",
	finalAge: "23"
});

ageGroup24_28 = ObjectId();
db.ageGroup.save( {
	_id: ageGroup24_28,
	description : "24 a 28 anos",
	initialAge: "24",
	finalAge: "28"
});

ageGroup29_33 = ObjectId();
db.ageGroup.save( {
	_id: ageGroup29_33,
	description : "29 a 33 anos",
	initialAge: "29",
	finalAge: "33"
});

ageGroup34_38 = ObjectId();
db.ageGroup.save( {
	_id: ageGroup34_38,
	description : "34 a 38 anos",
	initialAge: "34",
	finalAge: "38"
});

ageGroup39_43 = ObjectId();
db.ageGroup.save( {
	_id: ageGroup39_43,
	description : "39 a 43  anos",
	initialAge: "39",
	finalAge: "43"
});

ageGroup44_48 = ObjectId();
db.ageGroup.save( {
	_id: ageGroup44_48,
	description : "44 a 48  anos",
	initialAge: "44",
	finalAge: "48"
});

ageGroup49_53 = ObjectId();
db.ageGroup.save( {
	_id: ageGroup49_53,
	description : "49 a 53 anos",
	initialAge: "49",
	finalAge: "53"
});

ageGroup54_58 = ObjectId();
db.ageGroup.save( {
	_id: ageGroup54_58,
	description : "54 a 58  anos",
	initialAge: "54",
	finalAge: "58"
});

ageGroup59_more = ObjectId();
db.ageGroup.save( {
	_id: ageGroup59_more,
	description : "59 anos ou mais",
	initialAge: "59",
	finalAge: "130"
});

// plan type

planType_enf = ObjectId();
db.planType.save( {
	_id: planType_enf,
	description : "Enfermaria com coparticipação e sem plano odontológico",
	dental: false,
	coparticipation: true
});

planType_apart = ObjectId();
db.planType.save( {
	_id: planType_apart,
	description : "Apartamento sem coparticipação e sem plano odontológico",
	dental: false,
	coparticipation: false
});

planType_vip = ObjectId();
db.planType.save( {
	_id: planType_vip,
	description : "Vip sem coparticipação e com plano odontológico",
	dental: true,
	coparticipation: false	
});

// health plan "Enfermaria"
healthPlan_enf = ObjectId();
db.healthPlan.save( {
	_id: healthPlan_enf,
	code: "Enf",
	name : "Enfermaria",
	planType: {
		$ref: "planType",
		$id: planType_enf
	},
	ageGroupValues: [
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup0_18
			},
			value: 216.35	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup19_23
			},
			value: 274.82	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup0_18
			},
			value: 343.50	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup29_33
			},
			value: 361.42	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup34_38
			},
			value: 383.39
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup39_43
			},
			value: 438.45	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup44_48
			},
			value: 530.67	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup49_53
			},
			value: 610.24	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup54_58
			},
			value: 850.10
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup59_more
			},
			value: 1297.49	
		}
	]
});


// health plan "Apartamento"
healthPlan_apart = ObjectId();
db.healthPlan.save( {
	_id: healthPlan_apart,
	code: "Apart",
	name : "Apartamento",
	planType: {
		$ref: "planType",
		$id: planType_apart
	},
	ageGroupValues: [
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup0_18
			},
			value: 300.69	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup19_23
			},
			value: 382.05	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup29_33
			},
			value: 477.48	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup29_33
			},
			value: 502.41	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup34_38
			},
			value: 532.95
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup39_43
			},
			value: 609.44
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup44_48
			},
			value: 737.65	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup49_53
			},
			value: 848.25	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup54_58
			},
			value: 1181.63	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup59_more
			},
			value: 1803.17	
		}
	]
});



// health plan "VIP"
healthPlan_vip = ObjectId();
db.healthPlan.save( {
	_id: healthPlan_vip,
	code: "VIP",
	name : "VIP",
	planType: {
		$ref: "planType",
		$id: planType_vip
	},
	ageGroupValues : [
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup0_18
			},
			value: 345.16
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup19_23
			},
			value: 438.52	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup29_33
			},
			value: 548.07	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup29_33
			},
			value: 576.69	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup34_38
			},
			value: 611.74	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup39_43
			},
			value: 699.60
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup44_48
			},
			value: 846.70	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup49_53
			},
			value: 973.73	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup54_58
			},
			value: 1356.39	
		},
		{
			_id: ObjectId(),
			ageGroup: {
				$ref: "ageGroup",
				$id: ageGroup59_more
			},
			value: 2069.83	
		}
	]
});

