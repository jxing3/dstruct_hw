# $Id: Makefile 529 2006-09-09 16:43:14Z phf $
# A really simplistic Makefile...
all:
	javac -Xlint:all *.java
doc:
	javadoc -d html *.java
style:
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml DOS.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml Position.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml TreeImplementation.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml Tree.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml Operation.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml RemovalException.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml InvalidPositionException.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml InsertionException.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml EmptyTreeException.java

clean:
	rm -rf html *.class sorting.tar.gz
archive:
	tar zcvf cs226-assignment-6-jxing3.tar.gz Tree.java Position.java Operation.java TestTree.java TreeImplementation.java EmptyTreeException.java RemovalException.java InvalidPositionException.java InsertionException.java DOS.java README
