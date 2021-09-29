import stack.*

fun infToPost(stack: Stack, infix: String): String {
    var postfix = ""
    for (i in infix.indices) {
        if ((infix[i] in '0'..'9')) postfix += infix[i]
        else if (infix[i] == '(') stack.push(infix[i])
        else if (infix[i] == '(') {
            while ((stack.peek() != '(') && (!stack.isEmpty()))  {
                var tmp1: Char = stack.peek()
                postfix += tmp1
                stack.pop()
            }
            stack.pop()
        } else if (operations(infix[i])) {
            if (stack.isEmpty()) stack.push(infix[i])
            else if (precedence(infix[i]) > precedence(stack.peek())) stack.push(infix[i])
            else if ((precedence(infix[i]) == precedence(stack.peek())) && (infix[i] == '^')) stack.push(infix[i])
            else {
                while ((!stack.isEmpty()) && (precedence(infix[i]) <= precedence(stack.peek()))) {
                    var tmp2: Char = stack.peek()
                    postfix += tmp2
                    stack.pop()
                }
                stack.push(infix[i])
            }
        }
    }
    while (!stack.isEmpty()) {
        postfix += stack.peek()
        stack.pop()
    }
    return postfix
}


fun main() {
    var stack = Stack()

    print("Введите ваше выражение: ")
    var inf = readLine()!!
    val post = infToPost(stack, inf)

    println()
    println("Исходное выражение: $inf")
    println("Постфиксное выражение: $post")
}

fun operations(c: Char) = (c == '+') or (c == '-') or (c == '*') or (c == '/') or (c == '^')
fun precedence(c: Char): Int {
    return when (c) {
        '^' -> 3
        '*' -> 2
        '/' -> 2
        '+' -> 1
        '-' -> 1
        else -> -1
    }
}