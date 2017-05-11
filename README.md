
﻿# build_static_resouse_version

maven plugin,build static resouse version

##      	目录


*	[using](#using)

## using

pom.xml：
	
	<plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.1.0</version>
        <configuration>
          <warSourceExcludes>**/*.js,**/*.css,**/*.html</warSourceExcludes>
        </configuration>
      </plugin>
      <plugin>
        <groupId>build_static_resouse_version</groupId>
        <artifactId>build_static_resouse_version</artifactId>
        <version>1.0</version>
        <executions>
          <execution>
            <goals>
              <goal>bulid_static</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <suffixs>
            <suffix>.html</suffix>
            <suffix>.js</suffix>
            <suffix>.css</suffix>
          </suffixs>
        </configuration>
      </plugin>
