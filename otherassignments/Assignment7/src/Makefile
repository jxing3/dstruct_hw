all: classes profs

classes:
	javac -Xlint:all *.java

profs:
	java -agentlib:hprof=heap=sites,depth=6,file=biased1kH.hprof.txt Unique <biased1k.txt >null
	java -agentlib:hprof=heap=sites,depth=6,file=biased10kH.hprof.txt Unique <biased10k.txt >null
	java -agentlib:hprof=heap=sites,depth=6,file=biased100kH.hprof.txt Unique <biased100k.txt >null
	java -agentlib:hprof=cpu=samples,depth=6,file=biased1kS.hprof.txt Unique <biased1k.txt >null
	java -agentlib:hprof=cpu=times,depth=6,file=biased1kt.hprof.txt Unique <biased1k.txt >null
	java -agentlib:hprof=cpu=samples,depth=6,file=biased10kS.hprof.txt Unique <biased10k.txt >null
	java -agentlib:hprof=cpu=times,depth=6,file=biased2kt.hprof.txt Unique <biased2k.txt >null
	java -agentlib:hprof=cpu=samples,depth=6,file=biased100kS.hprof.txt Unique <biased100k.txt >null
	java -agentlib:hprof=cpu=times,depth=6,file=biased4kt.hprof.txt Unique <biased4k.txt >null
#
	java -agentlib:hprof=heap=sites,depth=6,file=mixed1kH.hprof.txt Unique <mixed1k.txt >null
	java -agentlib:hprof=heap=sites,depth=6,file=mixed10kH.hprof.txt Unique <mixed10k.txt >null
	java -agentlib:hprof=heap=sites,depth=6,file=mixed100kH.hprof.txt Unique <mixed100k.txt >null
	java -agentlib:hprof=cpu=samples,depth=6,file=mixed1kS.hprof.txt Unique <mixed1k.txt >null
	java -agentlib:hprof=cpu=times,depth=6,file=mixed1kt.hprof.txt Unique <mixed1k.txt >null
	java -agentlib:hprof=cpu=samples,depth=6,file=mixed10kS.hprof.txt Unique <mixed10k.txt >null
	java -agentlib:hprof=cpu=times,depth=6,file=mixed2kt.hprof.txt Unique <mixed2k.txt >null
	java -agentlib:hprof=cpu=samples,depth=6,file=mixed100kS.hprof.txt Unique <mixed100k.txt >null
	java -agentlib:hprof=cpu=times,depth=6,file=mixed4kt.hprof.txt Unique <mixed4k.txt >null
#
	java -agentlib:hprof=heap=sites,depth=6,file=random1kH.hprof.txt Unique <random1k.txt >null
	java -agentlib:hprof=heap=sites,depth=6,file=random10kH.hprof.txt Unique <random10k.txt >null
	java -agentlib:hprof=heap=sites,depth=6,file=random100kH.hprof.txt Unique <random100k.txt >null
	java -agentlib:hprof=cpu=samples,depth=6,file=random1kS.hprof.txt Unique <random1k.txt >null
	java -agentlib:hprof=cpu=times,depth=6,file=random1kt.hprof.txt Unique <random1k.txt >null
	java -agentlib:hprof=cpu=samples,depth=6,file=random10kS.hprof.txt Unique <random10k.txt >null
	java -agentlib:hprof=cpu=times,depth=6,file=random2kt.hprof.txt Unique <random2k.txt >null
	java -agentlib:hprof=cpu=samples,depth=6,file=random100kS.hprof.txt Unique <random100k.txt >null
	java -agentlib:hprof=cpu=times,depth=6,file=random4kt.hprof.txt Unique <random4k.txt >null


clean:
	rm -rf *.class *.hprof.txt
