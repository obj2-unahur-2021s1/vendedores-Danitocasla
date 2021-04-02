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

    fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto }
    fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto }
    fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntaje }
    fun esGenerico() = certificaciones.any { !it.esDeProducto }

}

// En los parámetros, es obligatorio poner el tipo
class VendedorFijo(val ciudadOrigen: Ciudad) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return ciudad == ciudadOrigen
    }
    fun esInfluyente() = false
}

// A este tipo de List no se le pueden agregar elementos una vez definida
class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return provinciasHabilitadas.contains(ciudad.provincia)
    }
    fun esInfluente() : Boolean {
        return sumaDePoblacion() >= 1
    }
    private fun sumaDePoblacion() = provinciasHabilitadas.sumBy { it.poblacion }
}

class ComercioCorresponsal(val ciudades: List<Ciudad>) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return ciudades.contains(ciudad)
    }
    fun esInfluyente(): Boolean {
        return (this.cantidadSucursales() or this.cantidadProvincias())
    }
    private fun cantidadSucursales() = ciudades.size >= 5
    //TODO necesito que la colección que de como resultado el map hacerla conjunto para eliminar los repetidos
    private fun cantidadProvincias() = ciudades.map { it.provincia }.size >= 3

}




