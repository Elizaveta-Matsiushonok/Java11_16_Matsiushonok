# Исправление в исключениях 
Неправильно пробросила исключения: throw new MyException("exception" + e.getMessage), нужно throw new MyException("exception", e)
