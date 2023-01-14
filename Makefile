timestamp := $(shell /bin/date "+%F %T")

usage:
	@echo "=============================================================="
	@echo "usage   =>  显示菜单"
	@echo "wrapper =>  初始化maven-wrapper"
	@echo "test    =>  单元测试"
	@echo "clean   =>  清理"
	@echo "package =>  打包"
	@echo "deploy  =>  发布"
	@echo "install =>  本地安装"
	@echo "version =>  调整版本号"
	@echo "github  =>  提交源代码"
	@echo "=============================================================="

wrapper:
	@mvn wrapper:wrapper -Dmaven=3.8.7

clean:
	@mvnw -f $(CURDIR)/pom.xml clean -q

test:
	@mvnw -f $(CURDIR)/pom.xml clean test

package:
	@mvnw -f $(CURDIR)/pom.xml clean package -Dmaven.test.skip=true

deploy:
	@mvnw -f $(CURDIR)/pom.xml clean deploy -Dmaven.test.skip=true -P"sonar"

install:
	@mvnw -f $(CURDIR)/pom.xml clean install -Dmaven.test.skip=true -P"sonar"

version:
	@mvnw -f $(CURDIR)/pom.xml versions:set
	@mvnw -f $(CURDIR)/pom.xml -N versions:update-child-modules
	@mvnw -f $(CURDIR)/pom.xml versions:commit

github: clean
	@git add .
	@git commit -m "$(timestamp)"
	@git push

.PHONY: usage wrapper clean package install deploy version github