#!/bin/bash
set -e

cd /home/ubuntu/Moagong

echo ">>> 최신 코드 가져오기"
git pull origin main

echo ">>> 도커 재배포"
sudo docker compose -f docker-compose.prod.yml up -d --build

echo ">>> 안 쓰는 이미지 정리"
sudo docker image prune -f

echo ">>> 배포 완료"
sudo docker compose -f docker-compose.prod.yml ps
