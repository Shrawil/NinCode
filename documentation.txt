# Documentation of NinCode

**Current features**
1. nin.out - Outputs a string literal or a variable value. (Also supports escape sequences like \n \t \b \r).

Syntax : nin.out argument;

Ex.1 nin.out "Hello world!";
Ex.2 nin.out varname;

Constraints - 

2. nin.setvar - Stores a variable of any datatype (int/str/bool).
Note: Variables of different datatypes can have same name.
Syntax : nin.setvar dataType varName varValue;