#!/bin/bash

rm -fr sharpen.mono/
git clone --recursive \
	https://github.com/mono/sharpen.git \
	sharpen.mono/

rm -fr sharpen.sample.config/
git clone --recursive \
	https://github.com/ydanila/sharpen_imazen_config.git \
	sharpen.sample.config/

rm -fr sharpen.sample.java.repo.for.convresion/
git clone --recursive \
	-b xmp-core \
	https://github.com/ydanila/n-metadata-extractor.git \
	sharpen.sample.java.repo.for.convresion/

rm -fr sharpen.sample.java.android.repo.for.convresion/
git clone --recursive \
	https://github.com/layerhq/quick-start-android.git \
	sharpen.sample.java.android.repo.for.convresion/


#------------------------------------------------------------------------------
# maven fixes

cp -f \
	sharpen.fixes/pom.mono.sharpen.fix.xml \
	sharpen.mono/pom.xml
cd sharpen.mono/
mvn clean test
mvn clean package
ls -al ./src/target/*.ja
cd ..

cp -f \
	sharpen.fixes/pom.config.project.fix.xml \
	sharpen.sample.config/pom.xml
cd sharpen.sample.config/
mvn clean package
ls -al ./sharpen.config/target/*.jar


cd sharpen.sample.java.repo.for.convresion/
java \
	-jar ../sharpen.mono/src/target/sharpencore-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
	src/ 

java \
	-jar ../sharpen.mono/src/target/sharpencore-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
	src/ \
	-configurationClass \
		../sharpen.sample.config/sharpen.config/target/MEConfiguration.sharpenconfig.jar \
	@sharpen-all-options

\
	@sharpen-all-options-without-configuration
