# Подключение OpenCV

1. Скачать OpenCV c [OpenCV Site] (https://opencv.org/releases.html)
2. Распаковать архив
3. Создать новый проект в `Android Studio` c поддержкой C++
4. В верхнем меню выбрать File - New - Import Module
5. Выбрать папку из распакованного архива OpenCV `/sdk/java` и нажать `Next` и `Finish`
6. Затем должна вылезти ошибка Gradle. Для её правки необходимо перейти в вид Project из Android, выбрать моудль OpenCV и изменить в файле build.gradle compileSdkVersion и TargetSdkVersion на 28.
7. В верхнем меню выбрать File - Project Structure - Выбрать модуль `App` снизу, перейти во вкладку `Dependencies`, нажать на зелёный плюсик, выбрать `Module Dependency` и выбрать OpenCV модуль и нажать везде OK
8. Нажать правой кнопкой мыши по модулю `app` и выбрать New - Folder - JNI Folder
9. В появившемся окне поставить галочку на `Change Folder Location` и изменить в пути `jni` на `jniLibs` 
10. Из скачанной библиотеки OpenCV скопировать содержимое папки `sdk\native\libs`
11. Вставить в папку `jniLibs`
12. Ready!

[Видео туториал] (https://www.youtube.com/watch?v=JIHfqzTdOcQ)
