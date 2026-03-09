# NinCode Feature Showcase

# Create variables
nin.setvar str name Shrawil;
nin.setvar int base 10;

# Print intro
nin.out "Welcome to NinCode Demo";
nl;
nin.out "Author: ";
nin.out str name;
nl;

# Loop demonstration
nin.out "\nRunning a loop 5 times:";
nl;
nin.loop 5 : nin.out "This is a loop iteration\t" : nin.delay 200;

# Generate random number
nin.out "\n\nGenerating a random number between 1 and 10...";
nl;
nin.rand x 1 10;

nin.out "Random value generated: ";
nin.out int x;
nl;

# Conditional check
if x <= 5 : nin.out "Value is smaller or equal to 5" : nin.out "Value is greater than 5";

# Math operation
nin.out "\n\nAdding 10 to the random value...";
nl;

nin.math result 10 + x;

nin.out "Result: ";
nin.out int result;
nl;

# End message
nin.out "\nDemo completed successfully!";
