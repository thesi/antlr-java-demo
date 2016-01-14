grammar ConditionalExp;
                 
expression 		: logicalExpr EOF;

logicalExpr		: 'AND' '[' logicalExpr ',' logicalExpr ']' # LogicalExpressionAnd 
				| 'OR' '[' logicalExpr ',' logicalExpr ']'  # LogicalExpressionOr 
				| primary 									# LogicalPrimary
				| BOOLEAN									# LogicalEntity
				;				
 
primary		: operand ('=' | '!=' | '<=' | '>=' | '>' | '<') operand 	# PrimaryAtomicCompare
			| operand 'IN' operand 										# PrimarySetCompare	
			| functionName '(' parameterList? ')'						# PrimaryFunctionCall
			;
			
operand	:	  'Subject.' fieldName (('.' INT)? ('.' fieldName))* 						# OperandSubjectAttribute
			| 'Resource.' fieldName (('.' INT)? ('.' fieldName))* 						# OperandResourceAttribute
			| 'Environment.' fieldName (('.' INT)? ('.' fieldName))* 					# OperandEnvironmentAttribute
			| (dbName '.')? collectionName '.' fieldName (('.' INT)? ('.' fieldName))*	# OperandDataPath
			| value 																	# OperandConstant
			;
			
fieldName		:	ID;
collectionName	:	ID;
functionName	:	ID;
dbName			:	ID;
parameterList	:  operand (',' operand)*;	
value 			:    INTEGER    |    FLOAT    |    STRING   |   BOOLEAN    ;

INT 			: '0'..'9'+ ;
INTEGER			: '-'? ('0'..'9')+;
FLOAT			: '-'? ('0'..'9')+ '.' ('0'..'9')+;
STRING 			: '"' (~('\\'|'"'))* '"';
BOOLEAN			: 'true'|    'false';
ID  			: ('a'..'z'|'A'..'Z')+ ;
WS  			: (' '|'\t')+ {skip();} ;
    