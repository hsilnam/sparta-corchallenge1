# Dockerfile: 단일 어플리케이션의 이미지 정의 (빌드, 실행방법)

## -- 빌드 -- (아직 작동안함)
## Base image 설정
## (Base image: Docker이미지 생성할 때, 기본 환경 시작점
##  ex)운영체제, 프로그래밍 언어 런타임, 특정 도구, 프레임워크)
#FROM gradle:7.6-jdk17 AS build
## 작업 디렉토리
#WORKDIR /app
#
#COPY . .
#
## Gradle 빌드 실행 (jar 파일 생성)
#RUN chmod +x ./gradlew
#RUN ./gradlew bootJar --stacktrace

# -- 실행 --
FROM openjdk:17
WORKDIR /app

## JAR 파일 복사
COPY ./build/libs/corporate-challenge-1.jar app.jar

## 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]