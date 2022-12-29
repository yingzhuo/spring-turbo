timestamp := $(shell /bin/date "+%F %T")

usage:
	@echo "=============================================================="
	@echo "usage   =>  显示菜单"
	@echo "test    =>  单元测试"
	@echo "clean   =>  清理"
	@echo "package =>  打包"
	@echo "deploy  =>  发布"
	@echo "install =>  本地安装"
	@echo "version =>  调整版本号"
	@echo "github  =>  提交源代码"
	@echo "=============================================================="

clean:
	@mvn -f $(CURDIR)/pom.xml clean -q

test:
	@mvn -f $(CURDIR)/pom.xml clean test

package:
	@mvn -f $(CURDIR)/pom.xml clean package -Dmaven.test.skip=true

deploy:
	@mvn -f $(CURDIR)/pom.xml clean deploy -Dmaven.test.skip=true -P"sonar"

install:
	@mvn -f $(CURDIR)/pom.xml clean install -Dmaven.test.skip=true -P"sonar"

version:
	@mvn -f $(CURDIR)/pom.xml versions:set
	@mvn -f $(CURDIR)/pom.xml -N versions:update-child-modules
	@mvn -f $(CURDIR)/pom.xml versions:commit

github: clean
	@git add .
	@git commit -m "$(timestamp)"
	@git push

.PHONY: usage clean package install deploy version github