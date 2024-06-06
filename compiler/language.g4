grammar language;

// rules

// minimal expression 
start: questionMarkExpr EOF;

// start symbol
expr: questionMarkExpr;

// expressions
questionMarkExpr: andOrExpr;

andOrExpr: cmpExpr;

cmpExpr: shiftExpr;

shiftExpr: bitAndOrExpr;

// plusMinusExpr ((BITAND | BITOR) plusMinusExpr)*
bitAndOrExpr: sumExpr ((BITAND | BITOR) sumExpr)*;

sumExpr: mulDivExpr (SUMOP  mulDivExpr)*;

mulDivExpr: unaryExpr;

unaryExpr: dashExpr;

dashExpr: arrowExpr;

arrowExpr: parantheseExpr;

parantheseExpr: NUMBER;


// tokens
NUMBER: [0-9]+;

// questionMarkExpr tokens

// andOrExpr tokens

// cmpExpr tokens

// shiftExpr tokens

// bitAndOrExpr tokens

// sumExpr tokens
SUMOP: PLUS|MINUS;
PLUS: '+';
MINUS: '-';
BITOR: '|';
BITAND: '&';

// mulDivExpr tokens

// unaryExpr tokens

// dashExpr tokens

// arrowExpr tokens

// parantheseExpr tokens

// skip whitespaces
WS: [ \t\r\n]+ -> skip;

