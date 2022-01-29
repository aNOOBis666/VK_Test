# Тестовое задание VK

### Решение выполнено Легиным Денисом

Язык реализации: kotlin

___

### Описание задачи:

Задание:
Необходимо разбить входную строку на смс-ки То есть написать метод, который принимает строку и максимальную длину смс, а
возвращает массив строк сформированный по следующим правилам:

1) разбивать текст можно только по пробелам
2) если получилось >1 смс-ки то нужно добавить ко всем суффикс вида " 12/123" где 12 номер текущей смс 123 общее число
   смс
3) длина смс включая суффикс должна быть не больше переданного лимита
4) так как отправка смс платная важно получить минимальное число смс дополнительно гарантируется что переданную строку
   можно по всем правилам разбить на смс-ки (например нет слов длиннее лимита и прочее)

___

### Техническая реализация:

Основной метод решения задачи реализован в качестве функции расширения, для большего удобства при дальнейшем
использовании

```kotlin
fun String.splitMessage(maxLengthMessage: Int): List<String> {
    val resultList = mutableListOf<String>()
    val message = mutableListOf<String>()
    var index = 1
    var counter = 1
    var lengthMessage = 0

    if (this.isEmpty()) throw NullPointerException(StringConst.NULL_POINTER_HANDLER.value)

    var numberOfMessages = ceil((this.length.toDouble() / maxLengthMessage.toDouble())).toInt()
    if (numberOfMessages != 1) numberOfMessages =
        ceil(((this.length + 2 * numberOfMessages).toDouble() / maxLengthMessage.toDouble())).toInt()

    val splitText: List<String> = this.split(" ")

    for (value in splitText) {
        try {
            message.add(value)
            lengthMessage += value.length
            if ((
                    lengthMessage + splitText[index].length + 
                    index.toString().length + 2 +
                    numberOfMessages.toString().length) > maxLengthMessage
            ) {
                resultList.add(message.joinToString(" ") + " ${counter}/$numberOfMessages")
                counter++
                message.clear()
                lengthMessage = 0
            }
            index++
        } catch (e: RuntimeException) {
            if (numberOfMessages != 1) resultList.add(message.joinToString(" ") + " ${counter}/$numberOfMessages")
            else resultList.add(message.joinToString(" "))
            message.clear()
        }
    }
    return resultList.toList()
}
```
Вспомогательные константы
```kotlin
enum class StringConst(val value: String) {
    SHORT_MESSAGE("Lorem"),
    MIDDLE_MESSAGE("Lorem ipsum dolor"),
    LONG_MESSAGE("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laborious nisi ut aliquip ex ea commodo consequat."),
    NULL_POINTER_HANDLER("Current meaning is null"),
}
```