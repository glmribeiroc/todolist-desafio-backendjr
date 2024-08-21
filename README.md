<h1 align="center">
  TODO List
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio" />
</p>

API para gerenciar tarefas (CRUD) que faz parte [desse desafio](https://file.notion.so/f/f/545ab2f7-d487-4337-abe0-25d859d96529/0ec6dee0-4121-4a33-97b4-817ff940fbbc/DesafiosTecnicos.pdf?table=block&id=8036f57a-ee97-4de4-a128-65397aced6ae&spaceId=545ab2f7-d487-4337-abe0-25d859d96529&expirationTimestamp=1724335200000&signature=rx0-wICoPotR1AGv4ntHwus6fAVA5XFAfUDvOrRSby0&downloadName=DesafiosTecnicos.pdf) para desenvolvedores java.


## Tecnologias
 
- [Spring Boot]
- [Spring MVC]
- [Spring Data JPA]
- [Bean Validation]
- [PostgreSQL]

## Práticas adotadas

- SOLID
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Testes unitarios e de integração

## Usando docker-compose

- Clonar repositório git
- Executar o container:
```
docker-compose up
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizado o postman.

Utilize os cURL:

- Criar Lista de Tarefa 
```
curl --location 'http://localhost:8080/todo-list' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Todo List"
}'
```

- Listar Listas de Tarefa
```
curl --location 'http://localhost:8080/todo-list'

com filtros:

curl --location 'http://localhost:8080/todo-list?status=PENDING&isFavorite=false'

[
    {
        "id": 53,
        "title": "tcc",
        "todoList": [
            {
              "id": 1,
              "title": "Desenvolver Front",
              "description": "Iniciar o desenvolvimento do front-end",
              "isFavorite": true,
              "completionDate": null,
              "expectedcCompletionDate": "2024-08-19T15:30:00",
              "status": "PENDING",
              "createdAt": "2024-08-19T15:23:57.597666",
              "updatedAt": "2024-08-20T17:56:48.16499",
              "subTasks": []
            },
            {
              "id": 3,
              "title": "Criar repositório",
              "description": "Criar o repositório no github",
              "isFavorite": true,
              "completionDate": "2024-08-21T14:47:47.2550553",
              "expectedcCompletionDate": "2024-08-30T15:30:00",
              "status": "COMPLETED",
              "createdAt": "2024-08-19T16:24:24.411395",
              "updatedAt": "2024-08-21T14:47:47.255577",
              "subTasks": []
            }
        ]
    }
]
```
- Remover Tarefas Finalizadas da Lista de Tarefa
```
curl --location --request DELETE 'http://localhost:8080/todo-list/{id}/completed-todos'
```
- Criar Tarefa 
```
curl --location 'http://localhost:8080/todo' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Nova tarefa",
    "description": "Tarefa Descrição",
    "expectedCompletionDate": "2024-08-30T15:30:00",
    "isFavorite": true,
    "todoListId": 53
}'
```

- Atualizar Tarefa
```
curl --location --request PUT 'http://localhost:8080/todo/{id}' \
--header 'Content-Type: application/json' \
--data '{
    "title": "",
    "description": "",
    "expectedCompletionDate": ""
}'
```

- Favoritar Tarefa
```
curl --location --request PATCH 'http://localhost:8080/todo/{id}/update-favorite' \
--header 'Content-Type: application/json' \
--data '{
    "isFavorite": false
}'

{
    "id": 3,
    "title": "Criar repositório",
    "description": "Criar o repositório no github",
    "isFavorite": false,
    "completionDate": "2024-08-21T14:47:47.255055",
    "expectedcCompletionDate": "2024-08-30T15:30:00",
    "status": "COMPLETED",
    "createdAt": "2024-08-19T16:24:24.411395",
    "updatedAt": "2024-08-21T14:57:46.165885",
    "subTasks": []
}
```
- Atualizar Status da Tarefa
```
curl --location --request PATCH 'http://localhost:8080/todo/{id}/update-status' \
--header 'Content-Type: application/json' \
--data '{
    "status": "COMPLETED"
}'

{
    "id": 3,
    "title": "Criar repositório",
    "description": "Criar o repositório no github",
    "isFavorite": false,
    "completionDate": "2024-08-21T14:47:47.255055",
    "expectedcCompletionDate": "2024-08-30T15:30:00",
    "status": "COMPLETED",
    "createdAt": "2024-08-19T16:24:24.411395",
    "updatedAt": "2024-08-21T14:57:46.165885",
    "subTasks": []
}
```

- Remover Tarefa
```
curl --location --request DELETE 'http://localhost:8080/todo/{id}'
```