fun main() {

    println("Bienvenido a tu sistema bancario.")
    println("¿Qué tipo de cuenta te gustaría crear?")
    println("1. Cuenta de débito")
    println("2. Cuenta de crédito")
    println("3. Cuenta corriente")
    var tipoCuenta = ""
    var eleccionUsuario = 0
    while (tipoCuenta == "") {
        println("Elige una opción (1, 2 o 3)")
        eleccionUsuario = (1..5).random()
        println("La opción seleccionada es $eleccionUsuario.")

        when (eleccionUsuario) {
            1 -> tipoCuenta = "débito"
            2 -> tipoCuenta = "crédito"
            3 -> tipoCuenta = "corriente"
            else -> continue
        }
    }
    println("Has creado una cuenta de $tipoCuenta.")

    var saldoCuenta = (0..1000).random()
    println("El saldo actual es $saldoCuenta dólares.")
    val dinero = (0..1000).random()
    println("La cantidad que transferiste es $dinero dólares.")

    fun retirar(cantidad: Int): Int {
        saldoCuenta -= cantidad
        println("Has retirado exitosamente $cantidad dólares. El saldo actual es $saldoCuenta dólares.")
        return cantidad
    }

    fun retirarDebito(cantidad: Int): Int {
        if (saldoCuenta == 0) {
            println("¡No puedes retirar, no hay dinero en esta cuenta!")
            return saldoCuenta
        } else if (cantidad > saldoCuenta) {
            println("¡No hay suficiente dinero en esta cuenta! El saldo actual es $saldoCuenta dólares.")
            return 0
        } else {
            return retirar(cantidad)
        }
    }

    fun depositar(cantidad: Int): Int {
        saldoCuenta += cantidad
        println("Has depositado exitosamente $cantidad dólares. El saldo actual es $saldoCuenta dólares.")
        return cantidad
    }

    fun depositarCredito(cantidad: Int): Int {
        if (saldoCuenta == 0) {
            println("¡Esta cuenta está completamente pagada! ¡No deposites dinero!")
            return saldoCuenta
        } else if (saldoCuenta + cantidad > 0) {
            println("Depósito fallido, intentaste pagar un monto mayor que el saldo de crédito. El saldo corriente es ${saldoCuenta} dólares.")
            return 0
        } else if (cantidad == -saldoCuenta) {
            saldoCuenta = 0
            println("¡Has pagado esta cuenta!")
            return cantidad
        } else {
            return depositar(cantidad)
        }
    }
    fun transferir(tipoTransaccion: String) {
        val cantidadTransferida: Int

        when (tipoTransaccion) {
            "retirar" -> {
                if (tipoCuenta == "débito") {
                    cantidadTransferida = retirarDebito(dinero)
                } else {
                    cantidadTransferida = retirar(dinero)
                }
                println("La cantidad que retiraste es $cantidadTransferida dólares.")
            }
            "depositar" -> {
                if (tipoCuenta == "crédito") {
                    cantidadTransferida = depositarCredito(dinero)
                } else {
                    cantidadTransferida = depositar(dinero)
                }
                println("La cantidad que depositaste es $cantidadTransferida dólares.")
            }
            else -> return
        }
    }
    var sistemaAbierto = true
    var opcion = 0
    while (sistemaAbierto) {
        println("¿Qué te gustaría hacer?")
        println("1. Consultar el saldo de la cuenta bancaria")
        println("2. Retirar dinero")
        println("3. Depositar dinero")
        println("4. Cerrar el sistema")
        println("¿Qué opción eliges? (1, 2, 3 o 4)")

        opcion = (1..5).random()
        println("La opción seleccionada es $opcion.")

        when (opcion) {
            1 -> println("El saldo actual es $saldoCuenta dólares.")
            2 -> transferir("retirar")
            3 -> transferir("depositar")
            4 -> {
                sistemaAbierto = false
                println("El sistema está cerrado")
            }
            else -> continue
        }
    }
}