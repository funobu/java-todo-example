FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=todo

VOLUME todo-data:/var/lib/mysql