grammar Play;

prog
    : stmt*
    ;

block
    : stmt
    | '{' stmt+ '}'
    ;

stmt
    : defn
    | value
    ;

defn
    : ID '=' value
    ;

value
    : INT
    | STRING
    | ref
    | call
    | anon
    | value op=BINOP value
    | '(' value ')'
    ;

ref
    : ID
    ;

call
    : ID '(' args ')'
    ;

args
    : value? (',' value)*
    ;

anon
    : params '=>' block
    ;

params
    : '()'
    | ID (',' ID)*
    ;

ID
    : [a-zA-Z_]+
    ;

INT
    : [0-9]+
    ;

STRING
    : '"' [a-zA-Z]* '"'
    ;

BINOP
    : [\+\-\*\/]
    ;

WS
    : [ \t\r\n]+ -> skip
    ;
