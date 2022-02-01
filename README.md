# Тестовое задание VK

### Решение выполнено Легиным Денисом

Язык реализации: kotlin
* Пример запуска и результат выполнения на тестовых данных: src/TestSplitter.kt
* Решение: src/solution/SplitMessageExtension.kt
* Используемые константы: src/solution/StringConst
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
    var wordIndex = 1
    var messageIndex = 1
    var lengthMessage = 0

    if (this.isEmpty()) throw NullPointerException(solution.StringConst.NULL_POINTER_HANDLER.value)

    var numberOfMessages = ceil((this.length.toDouble() / maxLengthMessage.toDouble())).toInt()
    if (numberOfMessages != 1) numberOfMessages =
        ceil(((this.length + 2 * numberOfMessages).toDouble() / maxLengthMessage.toDouble())).toInt()

    val splitText: List<String> = this.split(" ")

    for (value in splitText) {
        try {
            message.add(value)
            lengthMessage += value.length
            if ((
                     lengthMessage + splitText[wordIndex].length +
                     wordIndex.toString().length + 2 +
                     numberOfMessages.toString().length) > maxLengthMessage
            ) {
                resultList.add(message.joinToString(" ") + " ${messageIndex}/$numberOfMessages")
                messageIndex++
                message.clear()
                lengthMessage = 0
            }
            wordIndex++
        } catch (e: RuntimeException) {
            if (numberOfMessages != 1) resultList.add(message.joinToString(" ") + " ${messageIndex}/$numberOfMessages")
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
   EMPTY_SEPARATOR(" ")
}
```

```kotlin
enum class IntConst(val index: Int){
	SPECIAL_SYMBOLS(2),
	LONELY_MESSAGE(1)
}
```
Пример использования, результат работы на тестовых данных
```kotlin
fun main() {
   val outPutShort = StringConst.SHORT_MESSAGE.value.splitMessageExtension(maxLengthMessage = 14)
   println("result: $outPutShort")
//        result: [Lorem]
   val outPutMiddle = StringConst.MIDDLE_MESSAGE.value.splitMessageExtension(maxLengthMessage = 14)
   println("result: $outPutMiddle")
//        result: [Lorem 1/3, ipsum 2/3, dolor 3/3]
   val outPutLong = StringConst.LONG_MESSAGE.value.splitMessageExtension(maxLengthMessage = 50)
   println("result: $outPutLong")
//        result: [Lorem ipsum dolor sit amet, consectetur 1/10, adipiscing elit, sed do eiusmod tempor 2/10, incididunt ut labore et dolore 3/10, magna aliqua. Ut enim ad 4/10, minim veniam, quis 5/10, nostrud exercitation 6/10, ullamco laborious 7/10, nisi ut aliquip 8/10, ex ea commodo 9/10, consequat. 10/10]

}

```
