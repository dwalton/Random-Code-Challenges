all:
	javac */*.java 
	mv */*.class .
code:
	java TestRandomCode
math:
	java TestRandomArithmetic
clean:
	rm *.class

