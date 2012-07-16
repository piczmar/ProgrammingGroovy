package ch18

/**
 * Designing DSL
 */


time = getPizza {
    setSize Size.LARGE
    setCrust Crust.THIN
    setTopping "Olives", "Onions", "Bell Pepper"
    setAddress "101 Main St., ..."
    setCard CardType.VISA, "1234-1234-1234-1234"

}
printf "Pizza will arrive in %d minutes\n", time


def getPizza(closure) {
    PizzaShop pizzaShop = new PizzaShop()
    closure.delegate = pizzaShop
    closure()
}



class PizzaShop {
    def size
    def crust
    def topping = []
    def address
    def card = [:]

    def setTopping(String[] args) {
        this.topping = args
    }

    def setCard(cardType, cardNumber) {
        card.type = cardType;
        card.number = cardNumber
        return 20
    }

}
enum Size {
    SMALL, MEDIUM, LARGE
}
enum Crust {
    THINK, THIN
}
enum CardType {
    VISA, MASTERCARD
}
