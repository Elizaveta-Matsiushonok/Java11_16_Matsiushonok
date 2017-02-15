# Java11_16_Matsiushonok
Неправильно пробросила исключения: throw new MyException("exception" + e.getMessage), нужно throw new MyException("exception", e)
