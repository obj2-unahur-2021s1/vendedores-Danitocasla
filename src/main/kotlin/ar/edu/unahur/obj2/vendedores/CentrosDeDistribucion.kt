package ar.edu.unahur.obj2.vendedores

class CentrosDeDistribucion(ciudad: Ciudad, private val vendedores: List<Vendedor>) {
    fun vendedorEstrella(): Vendedor? {
        return if (vendedores.isEmpty()) { throw Error(message = "No hay vendedores en el centro")
        } else { val maxBy = vendedores.maxBy { it.puntajeCertificaciones() }
            maxBy}
    }
    fun puedeCubrir( unaCiudad: Ciudad) = vendedores.any { it.puedeTrabajarEn(ciudad = unaCiudad) }
    fun vendedoresGenericos() = vendedores.filter { it.esGenerico() }
    fun esRobusto() = (vendedores.filter{it.esFirme()}).size >= 3
}