# 카프카 도커 컴포즈 설치 및 실행



##### 도커 컴포즈 설치

```
$ sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```

```
$ sudo chmod +x /usr/local/bin/docker-compose
```

```
$ sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
```

```
$ docker-compose -version 
```

##### 용량확인

```
$ df -h
```

![image](https://github.com/newcodingtest/kafka_project/assets/57785267/629cbe66-1ecf-4fba-8421-e95ae8e1e027)


실제 호스트의 /dev/shm 공간을 도커가 공유 할 수 있도록 설정한다



##### 도커 컴포즈 세팅 및 실행

```
$ vi docker-compose.yml
```

```
version: '2'
services:
  zookeeper:
    container_name: zookeeper
    image: wurstmeister/zookeeper
    ports:
      - "2181:2181"
  kafka:
    container_name: kafka
    image: wurstmeister/kafka
    depends_on:     #먼저 실행이 되어야할 컨테이너 목록 정리
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 192.168.0.190
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```

```
docker-compose -f docker-compose.yml up -d
```

