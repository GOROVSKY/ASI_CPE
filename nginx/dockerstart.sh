sudo docker build -t nginx-server .
sudo docker run -it -d --network host --name nginx_asi -p 80:80 nginx-server
