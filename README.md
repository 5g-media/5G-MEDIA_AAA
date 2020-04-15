###5G-MEDIA AAA portal

This is the 5G-MEDIA AAA portal, which includes 
* web portal
* plugins for OpenStack, OSM v5.0.5, KeyCloak v4.5 user/permission management
* docker-compose for pre-configured KeyCloak v4.5
* sample configuration for local environment on Virtual Box (DevStack + OSM)

TO INSTALL

- requirements: JDK 8, node v8.11.3
- launch 

```
yarn install
```


TO BUILD
- launch

```
yarn build
mvn package -Dmaven.test.skip=true
```
this will produce fivegmedia3a-0.0.6.jar and frontend artifacts (www) in the "target" folder

TO DEBUG

- launch backend: `java -cp fivegmedia3a-0.0.6.jar eu.fivegmedia.aaa.Fivegmedia3AApp`
- launch frontend: `yarn start` 
- backend URL: `http://localhost:8060`
- frontend URL: `http://localhost:9000` 

see webpack.dev.js to change URLs

TO DEPLOY
- copy target/fivegmedia3a-0.0.6.jar and target/www to the destination folder 
- launch java -jar fivegmedia3a-0.0.6.jar
- open http://localhost:8060

TO LAUNCH KEYCLOAK

```
cd docker/5gmedia_keycloak
docker-compose up
open http://localhost:8080
```
