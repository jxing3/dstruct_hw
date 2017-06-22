# $Id: Makefile 529 2006-09-09 16:43:14Z phf $
# A really simplistic Makefile...
all:
	javac -Xlint:all *.java
doc:
	javadoc -d html *.java
style:
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml ArrayDequeue.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml Calc.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml EmptyStackException.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml EmptyQueueException.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml Dequeue.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml Stack.java
	java -jar checkstyle-5.6-all.jar -c jhu_checks.xml ArrayStack.java
test:
	java -ea Run 1000 <sorted.data
	java -ea Run 1000 <reverse.data
	java -ea Run 1000 <random.data
clean:
	rm -rf html *.class sorting.tar.gz
archive:
	tar zcvf cs226-assignment-4-jxing3.tar.gz TestArrayDequeue.java ArrayDequeue.java Calc.java EmptyStackException.java EmptyQueueException.java Dequeue.java Stack.java ArrayStack.java README
