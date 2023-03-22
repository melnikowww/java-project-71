.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/java-package/bin/java-package

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions

helpMe:
	./build/install/app/bin/app -h

files:
	./build/install/app/bin/app src/main/java/hexlet/code/file1.json src/main/java/hexlet/code/file2.json


build-run: build run

.PHONY: build
