all:
	javac */*.java 
	mv */*.class .
code:
	java TestRandomCode
math:
	java TestRandomArithmetic
google:
	java MakeGoogleForm
clean:
	rm *.class

