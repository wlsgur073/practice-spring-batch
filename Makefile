# 백그라운드 실행, 강제 재생성
db-up:
	docker-compose up -d --force-recreate

# volume 삭제
db-down:
	docker-compose down -v

# 컨테이너 재시작
db-restart:
	docker-compose down
	docker-compose up -d

# 컨테이너 재시작 + 볼륨 삭제 후 재생성
db-restart-clean:
	docker-compose down -v
	docker-compose up -d