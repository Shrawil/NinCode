# Documentation of NinCode v1.3

**DataTypes in NinCode**
1. int - Stores any integer value.
2. str - Stores any String value, can be a single character too.
3. bool - Stores only true/false values (0 & 1 are not allowed).
[Float are yet to come]


**Current features**
1. nin.out - Outputs a string literal or a variable value. (Also supports escape sequences like \n \t \b \r).

Syntax : nin.out argument;

Ex.1 nin.out "Hello world!";

Ex.2 nin.out varname;

Constraints - You can not provide string literal and variable both in same nin.out argument. 

2. nin.setvar - Stores a variable of any datatype (int/str/bool).
Note: Variables of different datatypes can have same name.

Syntax : nin.setvar dataType varName varValue;

Ex. nin.setvar x y z;

Creates a variable names "y" of datatype "x" containing value "z".

Constraints - 

a. You can not use setvar inside loops. 
b. Variables must be initialised when created.

3. nin.delay - Waits for n seconds before executing next line.
