# meeting

meet room example

#### Dependencies
* Java : 1.8.x
* spring-boot : 2.1.3.RELEASE
* gradle : 5.2.1 
* springfox-swagger : 2.9.2
    - http://127.0.0.1/swagger-ui.html
* H2, Jdbc, Jpa
* Embedded Tomcat
* Lombok : 1.18.6
    - https://mvnrepository.com/artifact/org.projectlombok/lombok
* handlebars-spring-boot-starter : 0.3.0
    - https://github.com/allegro/handlebars-spring-boot-starter

### 실행
~~~
# test
./gradlew test

# build project
./gradlew bootJar

# Run project
java -jar ./build/libs/meeting-room-1.0.1.jar
~~~

### 프로젝트 설명
~~~
1. DB 구성
    - Room        -<   Reserve
      id                id
      name              roomId
      location          userName
                        usedDate
                        startTime
                        endTime
                        repeat
                        
2. web 페이지
    http://127.0.0.1/web/

3. web와 api 프로젝트를 서브모듈 구성으로 하지 않고 ajax를 이용한 api호출로 처리함.

4. 프로젝트 폴더 구성
    - config
    - domain
        reserve
        room
        rest
            model
        service
    - helper
    - web
~~~

### 문제 해결
~~~
1. 30분 단위 얘약
    - javascript, restDTO 에서 각각 %30 으로 validation
2. 1회성과 주단위 반복
    - repeat 를 입력 받아 해당 횟수 만큼 iterator
    - javascript number validation 생략함
3. 동일한 회의실 중첩 불가
    - 저장시 Before After 쿼리로 데이터베이스 질의
    - 캐시키 전략이 세워지면 캐시로 대체 가능
4. test case
    - repository layer, controller에 대한 내용 테스트 완료.
~~~

#### TODO:
~~~
- refactoring
- code smell 제거
- exception 처리
~~~