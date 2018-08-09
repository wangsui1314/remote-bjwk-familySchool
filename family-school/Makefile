git clone https://github.com/wangsui1314/family-school.git
mvn clean package
#打包结果  /root/jjxy/family-school/target/family-school.war


all:
	git pull
	rm -rf ./target
	mvn clean package
	docker stop fintech
	docker rm fintech
	docker rmi fintech
	docker build -t fintech .
	docker run -p 7070:8080  --name fintech  -d fintech
