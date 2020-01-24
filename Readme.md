# Java Training Example Project

## Overview

Project for providing and examples for Java Trainings.

Приложение для предоставления расписания занятий на тренингах, контроля посещаемости и выставления оценок.

## Сущности
Ниже перечисленный сущности в предметной области проекта и их поля.

### Student (Студент)
Пользователь проходящий обучение на курсе. Может быть записан на различные курсы.

Поля:
- Email
- ФИО
- Пол
- Дата рождения
- Информация о себе

Связи:
- появляются в рамках курса

### Teacher (Преподаватель)
Пользователь, который ответственнен за курс и/или за конкретное занятие.
Если Преподаватель ответственный за курс, то он может редактировать курс и занятия.
Если Преподаватель ответственный за конкретное занятие, то он может редактировать это занятие.

Поля:
- Email
- ФИО
- Пол
- Дата рождения
- Информация о себе

Связи:
- появляются в рамках курса

### Mentor (Ментор)
Пользователь, ответственный за конкретного студента на конкретном курсе.

Поля:
- Email
- ФИО
- Пол
- Дата рождения
- Информация о себе

Связи:
- появляются в рамках курса

### Course (Курс)
Курс по какой-либо теме.

Поля:
- Тема курса
- Описание
- Дата начала
- Дата окончания
- Ответственный преподователь

Связи:
- Ответственный преподователь ("Teacher")
- Список студентов ("Student")
- Ментор у каждого студента в рамках курса ("Mentor" to "Student")
- Итоговая оценка студенту за курс

### Lesson (Занятие)
Конкретное занятие.

Поля:
- Тема занятия
- Дата и время начала
- Длительность
- Преподователь

Связи:
- Преподователь ("Teacher")
- Оценка от ментора студенту за занятие ("Mentor", "Student")
- Итоговая оценка студенту за занятие ("Student")

## User Stories

В первую очередь начнем с работы "Студента" с системой.
Сложная аутентификация  и работа с токенами пока вне скоупа, предпологается что пользователь будет передавать свой id как header.

### JTEP-1 Как "Студент" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь и получаю JWT токен для работы в системе

Request:

`POST /java-training-app/student/sign-up`
```json
{
  "email" : "vasya@email.com",
  "password" : "qwerty",
  "fio" : "Пупкин Василий Иванович",
  "gender" : "MALE", 
  "birthDate" : "19.01.1995",
  "info" : "Молодой инженер" 
}
```

Response:
`201 CREATED`
```json
{
  "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1Nzk5MDQ2OTksImlhdCI6MTU3OTg2ODY5OX0.8JG6O4U5F3xyOlOTyeSfl3Siim91HiJ-d4Dz5Guse8I"
}
```

Если email либо пароль неверные, то пользователь получит следующий ответ:

Если пользоветель с таким email уже существует, то будет возвращено:

Response:
`403 Forbidden`
```json
{
  "errorMessage" : "Wrong email or password."
}
```

### JTEP-2 Как "Студент", будучи зарегистрированным пользователем, я хочу войти в систему, и, если такой пользователь существует и пароль совпадает, войти в систему

Request:

`POST /java-training-app/student/sign-in`
```json
{
  "email" : "vasya@email.com",
  "password" : "qwerty"
}
```

Response:
`200 OK`
```json
{
  "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1Nzk5MDQ2OTksImlhdCI6MTU3OTg2ODY5OX0.8JG6O4U5F3xyOlOTyeSfl3Siim91HiJ-d4Dz5Guse8I"
}
```

Если пользоветель с таким email уже существует, то будет возвращено:

Response:
`403 Forbidden`


### JTEP-3 Как "Студент" я хочу получить список доступных курсов, и в результате получаю его   

Перед отправкой нужно войти в систему.

Request:

`GET /java-training-app/course`

Response:
`200 OK`
```json
[
  {
    "id" : 1, 
    "title" : "GP Java Training Winter 2019-2020",
    "description" : "Курс по обучению старту проектов на языке Java",
    "startDate" : "04.02.2020", 
    "endDate" : "28.02.2020",
    "teacherName" : "Литвинов Владимир Дмитриевич" 
  }
]
```

### JTEP-4 Как "Студент" я хочу записаться на доступный курс, и в результате записываюсь

Пока что заявка на запись будет приниматься автоматически, но в дальнейшем она должна будет вначале быть согласовано преподавателем.   

Запрос помещен в домен /student/, т к решено что этой операцией меняется состояние студента, а не курса.

Request:

`GET /java-training-app/student/register/course/${courseId}`

`Headers: Authorization=Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1Nzk5MDQ2OTksImlhdCI6MTU3OTg2ODY5OX0.8JG6O4U5F3xyOlOTyeSfl3Siim91HiJ-d4Dz5Guse8I` 

Response:
`200 OK`

В том случае если курс не существует либо уже закончился, то записаться не удасться.

Response:
`400 Bad Request`
```json
{
  "errorMessage" : "Course is already finished"
}
```

`400 Bad Request`
```json
{
  "errorMessage" : "No course with id=${courseId} was found"
}
```

