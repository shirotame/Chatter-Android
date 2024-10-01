# Chatter

![Static Badge](https://img.shields.io/badge/shirotame-Chatter-Android)
![GitHub top language](https://img.shields.io/github/languages/top/shirotame/Chatter-Android)

Chatter - приложение, реализуещее простейший способ связи между удаленными устройствами. Состоит из 3 элементов: [Chatter-Server](https://github.com/shirotame/Chatter-Server), [Chatter-Desktop](https://github.com/shirotame/Chatter-Desktop) и [Chatter-Android](https://github.com/shirotame/Chatter-Android)

## Как реализовано?

Сделано на java.net.Socket и Java for Android,  весь основной код находится в исходниках проекта. В целом, клиент подключается к серверу, отправляет условный заголовок подключения (в данном случае, символ), отправляет свой никнейм и IP.

## Возможности

Общение с пользователями, подключение к серверам и хранение созданных подключений.

## Как запустить?

Необходимо скачать последний [релиз](https://github.com/shirotame/Chatter-Android/releases) и запустить его. Далее, нажимаем на кнопку справа снизу, вводим IP адрес и порт от сервера, к которому хотим подключиться, вводим ник и подключаемся.
