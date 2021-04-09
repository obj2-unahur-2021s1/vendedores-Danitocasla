package ar.edu.unahur.obj2.vendedores

class Certificacion(val esDeProducto: Boolean, val puntaje: Int)

abstract class Vendedor {
    // Acá es obligatorio poner el tipo de la lista, porque como está vacía no lo puede inferir.
    // Además, a una MutableList se le pueden agregar elementos
    private val certificaciones = mutableListOf<Certificacion>()

    // Definimos el método abstracto.
    // Como no vamos a implementarlo acá, es necesario explicitar qué devuelve.
    abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean

    // En las funciones declaradas con = no es necesario explicitar el tipo
    fun esVersatil() =
        certificaciones.size >= 3
                && this.certificacionesDeProducto() >= 1
                && this.otrasCertificaciones() >= 1

    // Si el tipo no está declarado y la función no devuelve nada, se asume Unit (es decir, vacío)
    fun agregarCertificacion(certificacion: Certificacion) {
        certificaciones.add(certificacion)
    }

    fun esFirme() = this.puntajeCertificaciones() >= 30

    private fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto }
    private fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto }
    fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntaje }
    fun esGenerico() = certificaciones.any { !it.esDeProducto }

    abstract fun esInfluyente(): Boolean
}

// En los parámetros, es obligatorio poner el tipo
class VendedorFijo(private val ciudadOrigen: Ciudad) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return ciudad == ciudadOrigen
    }
    override fun esInfluyente() = false
}

// A este tipo de List no se le pueden agregar elementos una vez definida
class Viajante(private val provinciasHabilitadas: List<Provincia>) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return provinciasHabilitadas.contains(ciudad.provincia)
    }
    override fun esInfluyente() = sumaDePoblacion() >= 10000000

    private fun sumaDePoblacion() = provinciasHabilitadas.sumBy { it.poblacion }
}

class ComercioCorresponsal(private val ciudades: List<Ciudad>) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad) = ciudades.contains(ciudad)

    override fun esInfluyente(): Boolean {
        return (this.cantidadSucursales() or this.cantidadProvincias())
    }
    private fun cantidadSucursales() = ciudades.size >= 5
    private val listaDeProvincia = ciudades.map { it.provincia }
    private fun cantidadProvincias() = listaDeProvincia.toSet().size >=3

}




