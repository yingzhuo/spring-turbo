timestamp := $(shell /bin/date "+%F %T")

usage:
	@echo "=============================================================="
	@echo "usage   =>  显示菜单"
	@echo "compile =>  编译"
	@echo "test    =>  单元测试"
	@echo "clean   =>  清理"
	@echo "package =>  打包"
	@echo "deploy  =>  发布"
	@echo "install =>  本地安装"
	@echo "version =>  调整版本号"
	@echo "format  =>  格式化代码"
	@echo "lines   =>  统计代码行数"
	@echo "github  =>  提交源代码"
	@echo "=============================================================="

compile:
	@mvn -f $(CURDIR)/pom.xml clean compile

test:
	@mvn -f $(CURDIR)/pom.xml clean test

package:
	@mvn -f $(CURDIR)/pom.xml clean package -Dmaven.test.skip=true

deploy: format
	@mvn -f $(CURDIR)/pom.xml clean deploy -Dmaven.test.skip=true -P"sonar"

install: format
	@mvn -f $(CURDIR)/pom.xml clean install -Dmaven.test.skip=true

version:
	@mvn -f $(CURDIR)/pom.xml versions:set
	@mvn -f $(CURDIR)/pom.xml -N versions:update-child-modules
	@mvn -f $(CURDIR)/pom.xml versions:commit

clean:
	@mvn -f $(CURDIR)/pom.xml clean -q

format:
	@mvn -f $(CURDIR)/pom.xml formatter:format -q

lines:
	@mvn -f $(CURDIR)/pom.xml io.github.orhankupusoglu:sloc-maven-plugin:sloc -Ddisplay=true -Dsave=true -DtrimPkgNames=false

github: clean format
	@git add .
	@git commit -m "$(timestamp)"
	@git push

.PHONY: usage compile test clean package install deploy version format lines github